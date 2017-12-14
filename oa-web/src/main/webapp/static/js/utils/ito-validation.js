/**
 @Name：表单验证
 @Author：张渊
 @License：1.0.0
 */
define(["jquery"], function ($) {
    var ito = {
        util: {
            // 执行回调方法
            call: function (functionName, args) {
                if ('function' === typeof functionName) {
                    return functionName.apply(this, args);
                } else if ('string' === typeof functionName) {
                    if ('()' === functionName.substring(functionName.length - 2)) {
                        functionName = functionName.substring(0, functionName.length - 2);
                    }
                    var ns = functionName.split('.'),
                        func = ns.pop(),
                        context = window;
                    for (var i = 0; i < ns.length; i++) {
                        context = context[ns[i]];
                    }
                    return context[func].apply(this, args);
                }
            },

            format: function (message, parameters) {
                if (!$.isArray(parameters)) {
                    parameters = [parameters];
                }

                for (var i in parameters) {
                    if (parameters.hasOwnProperty(i)) {
                        message = message.replace('%s', parameters[i]);
                    }
                }

                return message;
            }
        },
        Validator: function (form, options) {

            this.$form = $(form);

            this.options = $.extend({
                // 默认元素class
                elementClass: 'ito-form',

                // 默认无效信息
                message: '这个值是无效的',

                // 默认情况下，文本框、下拉框、单选复选框、文本域等是放在该class元素下的，如<div class='ito-group'></div>
                group: '.ito-group',

                // 可以定义错误提示显示的元素
                container: null,

                // 这个字段控制不验证长度小于这个数字的字符
                threshold: null,

                /*
                 * 排除验证字段
                 * 默认情况下以下三种类型的字段不会验证
                 * - disabled
                 * - hidden
                 * - invisible
                 *
                 * 以下三种设置方式是一样的
                 * 1) ':disabled, :hidden, :not(:visible)'
                 * 2) [':disabled', ':hidden', ':not(:visible)']
                 * 3) [':disabled', ':hidden', function($field) {
                 *       return !$field.is(':visible');
                 *    }]
                 */
                excluded: [':disabled', ':hidden', ':not(:visible)'],

                /*
                 * 字体图标样式指定
                 * -valid：验证成功图标
                 * -invalid：验证失败图标
                 * -validating：验证中图标
                 * 示例：
                 * feedbackIcons: {
                 *   valid: 'ito ito-ok',
                 *   invalid: 'ito ito-remove',
                 *   validating: 'ito ito-refresh'
                 * }
                 */
                feedbackIcons: {
                    valid: 'ito ito-ok1',
                    invalid: 'ito ito-remove',
                    validating: 'ito ito-refresh'
                },

                /*
                 * 提交按钮选择器
                 * 这些按钮将被禁用,以防止从多个提交有效的形式
                 */
                submitButtons: '[type="submit"]',

                /*
                 * Live 验证选项，提供以下三个值
                 * - enabled：插件验证字段就改变
                 * - disabled：禁用现场验证。所示的错误消息只有在提交表单之后
                 * - submitted：现场验证表单提交后启用
                 */
                live: 'enabled',

                // 验证规则映射字段名
                fields: null
            }, options);

            // 使用验证
            this.validators = {
                notEmpty: {
                    enableByHtml5: function ($field) {
                        var required = $field.attr('required') + '';
                        return ('required' === required || 'true' === required);
                    },
                    validate: function (validator, $field, options) {
                        var type = $field.attr('type');
                        if ('radio' === type || 'checkbox' === type) {
                            return validator
                                    .getFieldElements($field.attr('data-field'))
                                    .filter(':checked')
                                    .length > 0;
                        }

                        return $.trim($field.val()) !== '';
                    }
                },
                stringLength: {
                    html5Attributes: {
                        message: 'message',
                        min: 'min',
                        max: 'max'
                    },
                    enableByHtml5: function ($field) {
                        var maxLength = $field.attr('maxlength');
                        if (maxLength) {
                            return {
                                max: parseInt(maxLength, 10)
                            };
                        }
                        return false;
                    },
                    validate: function (validator, $field, options) {
                        var value = $field.val();
                        if (value === '') {
                            return true;
                        }

                        var min = $.isNumeric(options.min) ? options.min : validator.getDynamicOption($field, options.min),
                            max = $.isNumeric(options.max) ? options.max : validator.getDynamicOption($field, options.max),
                            length = value.length,
                            isValid = true,
                            message = options.message;

                        if ((min && length < parseInt(min, 10)) || (max && length > parseInt(max, 10))) {
                            isValid = false;
                        }

                        switch (true) {
                            case (!!min && !!max):
                                message = ito.util.format(options.message);
                                break;

                            case (!!min):
                                message = ito.util.format(options.message);
                                break;

                            case (!!max):
                                message = ito.util.format(options.message);
                                break;

                            default:
                                break;
                        }

                        return {valid: isValid, message: message};
                    }
                },
                regexp: {
                    html5Attributes: {
                        message: 'message',
                        regexp: 'regexp'
                    },
                    enableByHtml5: function ($field) {
                        var pattern = $field.attr('pattern');
                        if (pattern) {
                            return {
                                regexp: pattern
                            };
                        }

                        return false;
                    },
                    validate: function (validator, $field, options) {
                        var value = $field.val();
                        if (value === '') {
                            return true;
                        }

                        var regexp = ('string' === typeof options.regexp) ? new RegExp(options.regexp) : options.regexp;
                        return regexp.test(value);
                    }
                },
                identical: {
                    html5Attributes: {
                        message: 'message',
                        field: 'field'
                    },

                    /**
                     * 检查是否输入值等于特定的价值
                     *
                     * @param {Validator} validator 验证插件实例
                     * @param {jQuery} $field field元素
                     * @param {Object} options 选项包括以下键:
                     * - field: 将用于与当前字段比较的字段名
                     * @returns {Boolean}
                     */
                    validate: function (validator, $field, options) {
                        var value = $field.val();
                        if (value === '') {
                            return true;
                        }

                        var compareWith = validator.getFieldElements(options.field);
                        if (compareWith === null) {
                            return true;
                        }

                        if (value === compareWith.val()) {
                            validator.updateStatus(options.field, validator.STATUS_VALID, 'identical');
                            return true;
                        } else {
                            return false;
                        }
                    }
                },
                emailAddress: {
                    enableByHtml5: function ($field) {
                        return ('email' === $field.attr('type'));
                    },

                    /**
                     * 如果当输入值为有效的电子邮件地址  返回true
                     *
                     * @param {Validator} validator 验证插件实例
                     * @param {jQuery} $field field元素
                     * @param {Object} [options]
                     * @returns {Boolean}
                     */
                    validate: function (validator, $field, options) {
                        var value = $field.val();
                        if (value === '') {
                            return true;
                        }

                        // 电子邮件地址的正则表达式
                        var emailRegExp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                        return emailRegExp.test(value);
                    }
                }
            };

            this.$invalidFields = $([]);// 无效的字段数组

            this.$submitButton = null;// 提交按钮，点击提交表单

            // 验证状态
            this.STATUS_NOT_VALIDATED = 'NOT_VALIDATED';
            this.STATUS_VALIDATING = 'VALIDATING';
            this.STATUS_INVALID = 'INVALID';
            this.STATUS_VALID = 'VALID';

            /*
             * 决定用户更改字段值时触发的事件
             * 大多数现代浏览器支持输入事件，除了IE 7和8。
             * ie9支持输入事件，但如果我按下backspace键，事件仍然没有被触发。
             * 得到IE版本
             */
            //var ieVersion = (function () {
            //    var v = 3, div = document.createElement('div'), a = div.all || [];
            //    while (div.innerHTML = '<!--[if gt IE ' + (++v) + ']><br><![endif]-->', a[0]) {
            //    }
            //    return v > 4 ? v : !v;
            //}());
            var ieVersion = (function () {
                var v = 3, div = document.createElement('div'), a = div.all || [];
                do {
                    div.innerHTML = '<!--[if gt IE ' + (++v) + ']><br><![endif]-->';
                } while (a[0]);
                return v > 4 ? v : !v;
            }());

            var el = document.createElement('div');

            this._changeEvent = (ieVersion === 9 || !('oninput' in el)) ? 'keyup' : 'input';

            // 标记表示在远程/回调验证器返回时表单已经准备好提交
            this._submitIfValid = null;

            // 缓存字段元素
            this._cacheFields = {};

            // --
            // 不建议外部调用的方法
            // --

            // 初始化form
            this._init = function () {
                var that = this,
                    options = {
                        excluded: this.$form.attr('data-excluded'),
                        //trigger: this.$form.attr('data-trigger'),
                        message: this.$form.attr('data-message'),
                        //container: this.$form.attr('data-container'),
                        //group: this.$form.attr('data-group'),
                        submitButtons: this.$form.attr('data-submitButtons'),
                        //threshold: this.$form.attr('data-threshold'),
                        live: this.$form.attr('data-live'),
                        onSuccess: this.$form.attr('data-onSuccess'),
                        onError: this.$form.attr('data-onError'),
                        fields: {},
                        feedbackIcons: {
                            valid: this.$form.attr('data-feedbackIcons-valid'),
                            invalid: this.$form.attr('data-feedbackIcons-invalid'),
                            validating: this.$form.attr('data-feedbackIcons-validating')
                        }
                    };

                // 在HTML 5中禁用客户端验证
                this.$form.attr('novalidate', 'novalidate');
                this.$form.addClass(this.options.elementClass);
                // 禁用默认提交
                this.$form.on('submit.iv', function (e) {
                    e.preventDefault();// 取消事件的默认动作
                    that.validate();
                });
                this.$form.on('click.iv', this.options.submitButtons, function () {
                    that.$submitButton = $(this);
                    // 用户点击提交按钮
                    that._submitIfValid = true;
                });
                // 查找所有具有“name”或“data-field”属性的字段
                this.$form.find('[name], [data-field]').each(function () {
                    var $field = $(this);
                    var field = $field.attr('name') || $field.attr('data-field');
                    var opts = that._parseOptions($field);
                    if (opts) {
                        $field.attr('data-field', field);
                        options.fields[field] = $.extend({}, opts, options.fields[field]);
                    }
                });

                this.options = $.extend(true, this.options, options);

                for (var field in this.options.fields) {
                    if (this.options.fields.hasOwnProperty(field)) {
                        this._initField(field);
                    }
                }

                this.$form.trigger($.Event('init.form.iv'), {
                    iv: this,
                    options: this.options
                });

                if (this.options.onSuccess) {
                    this.$form.on('success.form.iv', function (e) {
                        that.helpers.call(that.options.onSuccess, [e]);
                    });
                }
                if (this.options.onError) {
                    this.$form.on('error.form.iv', function (e) {
                        that.helpers.call(that.options.onError, [e]);
                    });
                }
            };

            // 从HTML属性中解析验证器选项
            this._parseOptions = function ($field) {
                var field = $field.attr('name') || $field.attr('data-field'),
                    validators = {},
                    validator,
                    v,          // Validator name
                    enabled,
                    optionName,
                    optionValue,
                    html5AttrName,
                    html5AttrMap;

                for (v in this.validators) {
                    if (this.validators.hasOwnProperty(v)) {
                        validator = this.validators[v];
                        enabled = $field.attr('data-' + v.toLowerCase()) + '';

                        html5AttrMap = ('function' === typeof validator.enableByHtml5) ? validator.enableByHtml5($field) : null;

                        if ((html5AttrMap && enabled !== 'false')
                            || (html5AttrMap !== true && ('' === enabled || 'true' === enabled))) {
                            // 尝试通过属性来解析选项
                            validator.html5Attributes = $.extend({}, {
                                message: 'message',
                                onError: 'onError',
                                onSuccess: 'onSuccess'
                            }, validator.html5Attributes);
                            validators[v] = $.extend({}, html5AttrMap === true ? {} : html5AttrMap, validators[v]);

                            for (html5AttrName in validator.html5Attributes) {
                                if (validator.html5Attributes.hasOwnProperty(html5AttrName)) {
                                    optionName = validator.html5Attributes[html5AttrName];
                                    optionValue = $field.attr('data-' + v.toLowerCase() + '-' + html5AttrName);
                                    if (optionValue) {
                                        if ('true' === optionValue) {
                                            optionValue = true;
                                        } else if ('false' === optionValue) {
                                            optionValue = false;
                                        }
                                        validators[v][optionName] = optionValue;
                                    }
                                }
                            }
                        }
                    }
                }

                var opts = {
                        excluded: $field.attr('data-excluded'),
                        feedbackIcons: $field.attr('data-feedbackIcons'),
                        //trigger: $field.attr('data-trigger'),
                        message: $field.attr('data-message'),
                        //container: $field.attr('data-container'),
                        group: $field.attr('data-group'),
                        selector: $field.attr('data-selector'),
                        //threshold: $field.attr('data-threshold'),
                        onStatus: $field.attr('data-onStatus'),
                        onSuccess: $field.attr('data-onSuccess'),
                        onError: $field.attr('data-onError'),
                        validators: validators
                    },
                    emptyOptions = $.isEmptyObject(opts),           // 检查是否使用HTML属性设置字段选项
                    emptyValidators = $.isEmptyObject(validators);  // 检查字段验证器是否使用HTML属性设置

                if (!emptyValidators || (!emptyOptions && this.options.fields && this.options.fields[field])) {
                    opts.validators = validators;
                    return opts;
                } else {
                    return null;
                }
            };

            /**
             * 可能没用了
             *
             * 检查字段值的字符数是否超过阈值
             *
             * @param {jQuery} $field 字段元素
             * @returns {Boolean}
             */
            this._exceedThreshold = function ($field) {
                var field = $field.attr('data-field'),
                    threshold = this.options.fields[field].threshold || this.options.threshold;
                if (!threshold) {
                    return true;
                }
                var cannotType = $.inArray($field.attr('type'), ['button', 'checkbox', 'file', 'hidden', 'image', 'radio', 'reset', 'submit']) !== -1;
                return (cannotType || $field.val().length >= threshold);
            };

            /**
             * 初始化字段
             *
             * @param {String|jQuery} field 字段 name 属性或字段元素
             */
            this._initField = function (field) {
                var fields = $([]);
                switch (typeof field) {
                    case 'object':
                        fields = field;
                        field = field.attr('data-field');
                        break;
                    case 'string':
                        fields = this.getFieldElements(field);
                        fields.attr('data-field', field);
                        break;
                    default:
                        break;
                }

                if (this.options.fields[field] === null || this.options.fields[field].validators === null) {
                    return;
                }

                // 我们不需要验证非现有字段
                if (fields.length === 0) {
                    delete this.options.fields[field];
                    return;
                }
                var validatorName;
                for (validatorName in this.options.fields[field].validators) {
                    if (this.options.fields[field].validators.hasOwnProperty(validatorName)) {
                        if (!this.validators[validatorName]) {
                            delete this.options.fields[field].validators[validatorName];
                        }
                    }
                }
                if (this.options.fields[field].enabled === null) {
                    this.options.fields[field].enabled = true;
                }

                var that = this,
                    total = fields.length,
                    type = fields.attr('type'),
                    updateAll = (total === 1) || ('radio' === type) || ('checkbox' === type),
                    event = ('radio' === type || 'checkbox' === type || 'file' === type || 'SELECT' === fields.eq(0).get(0).tagName) ? 'change' : this._changeEvent,
                    trigger = (this.options.fields[field].trigger || this.options.trigger || event).split(' '),
                    events = $.map(trigger, function (item) {
                        return item + '.update.iv';
                    }).join(' ');

                for (var i = 0; i < total; i++) {
                    var $field = fields.eq(i),
                        group = this.options.fields[field].group || this.options.group,
                        $parent = $field.parents(group),
                    // 允许用户指示错误消息的显示位置
                        container = this.options.fields[field].container || this.options.container,
                        $message = (container && container !== 'tooltip' && container !== 'popover') ? $(container) : this._getMessageContainer($field, group),
                        fieldValidators = this.options.fields[field].validators;

                    if (container && container !== 'tooltip' && container !== 'popover') {
                        $message.addClass('has-error');
                    }

                    // 删除所有错误消息和反馈图标
                    $message.find('.help-block[data-validator][data-for="' + field + '"]').remove();
                    $parent.find('i[data-icon-for="' + field + '"]').remove();

                    // 当用户更改字段值时，将其标记为未验证
                    $field.off(events).on(events, function () {
                        that.updateStatus($(this), that.STATUS_NOT_VALIDATED);
                    });

                    // 创建帮助块来显示错误消息
                    $field.data('iv.messages', $message);
                    for (validatorName in fieldValidators) {

                        if (fieldValidators.hasOwnProperty(validatorName)) {
                            $field.data('iv.result.' + validatorName, this.STATUS_NOT_VALIDATED);

                            if (!updateAll || i === total - 1) {
                                $('<small/>')
                                    .css('display', 'none')
                                    .addClass('help-block')
                                    .attr('data-validator', validatorName)
                                    .attr('data-for', field)
                                    .attr('data-result', this.STATUS_NOT_VALIDATED)
                                    .html(this._getMessage(field, validatorName))
                                    .appendTo($message);
                            }

                            // 准备验证器事件
                            (function () {
                                var onSuccess = fieldValidators[validatorName].onSuccess;
                                var onError = fieldValidators[validatorName].onError;

                                if (onSuccess) {
                                    $field.on('success.validator', function (e, data) {
                                        ito.util.call(onSuccess, [e, data]);
                                        ito.util.call(onSuccess, [e, data]);
                                    });
                                }

                                if (onError) {
                                    $field.on('error.validator', function (e, data) {
                                        ito.util.call(onError, [e, data]);
                                    });
                                }
                            })();
                        }
                    }

                    // 准备反馈图标
                    if (this.options.fields[field].feedbackIcons !== false && this.options.fields[field].feedbackIcons !== 'false'
                        && this.options.feedbackIcons
                        && this.options.feedbackIcons.validating && this.options.feedbackIcons.invalid && this.options.feedbackIcons.valid
                        && (!updateAll || i === total - 1)) {
                        $parent.removeClass('has-success').removeClass('has-error').addClass('has-feedback');
                        var $icon = $('<i/>')
                            .css('display', 'none')
                            .addClass('form-control-feedback')
                            .attr('data-icon-for', field)
                            // 将其置于包含 checkbox / radio 的标签后
                            // 当点击图标时，它不会影响到 checkbox / radio 元素
                            .insertAfter(('checkbox' === type || 'radio' === type) ? $field.parent() : $field);

                        // 如果没有标签，反馈图标不会正确渲染
                        if ($parent.find('label').length === 0) {
                            $icon.css('top', 0);
                        }

                        // 在输入组中修复反馈图标
                        if ($parent.find('.ito-input-group').length !== 0) {
                            $icon.css({
                                'top': 0,
                                'z-index': 100
                            }).insertAfter($parent.find('.ito-input-group').eq(0));
                        }
                    }
                }

                // 准备事件
                if (this.options.fields[field].onSuccess) {
                    fields.on('success.field.iv', function (e, data) {
                        ito.util.call(that.options.fields[field].onSuccess, [e, data]);
                    });
                }
                if (this.options.fields[field].onError) {
                    fields.on('error.field.iv', function (e, data) {
                        ito.util.call(that.options.fields[field].onError, [e, data]);
                    });
                }
                if (this.options.fields[field].onStatus) {
                    fields.on('status.field.iv', function (e, data) {
                        ito.util.call(that.options.fields[field].onStatus, [e, data]);
                    });
                }

                // 设置 live 模式
                events = $.map(trigger, function (item) {
                    return item + '.live.iv';
                }).join(' ');
                switch (this.options.live) {
                    case 'submitted':
                        break;
                    case 'disabled':
                        fields.off(events);
                        break;
                    case 'enabled':
                    /* 失败 */
                    default:
                        fields.off(events).on(events, function () {
                            if (that._exceedThreshold($(this))) {
                                that.validateField($(this));
                            }
                        });
                        break;
                }

                fields.trigger($.Event('init.field.iv'), {
                    bv: this,
                    field: field,
                    element: fields
                });
            };

            /**
             * 获取给定字段和验证器的错误消息
             *
             * @param {String} field 字段名
             * @param {String} validatorName 验证器名称
             * @returns {String}
             */
            this._getMessage = function (field, validatorName) {
                if (!this.options.fields[field] || !this.validators[validatorName]
                    || !this.options.fields[field].validators || !this.options.fields[field].validators[validatorName]) {
                    return '';
                }

                var options = this.options.fields[field].validators[validatorName];
                switch (true) {
                    case (!!options.message):
                        return options.message;
                    case (!!this.options.fields[field].message):
                        return this.options.fields[field].message;
                    default:
                        return this.options.message;
                }
            };


            /**
             * 当所有验证完成时调用
             */
            this._submit = function () {
                var isValid = this.isValid(),
                    eventType = isValid ? 'success.form.iv' : 'error.form.iv',
                    e = $.Event(eventType);

                this.$form.trigger(e);

                // 调用默认处理方式
                // 检查是否点击提交按钮
                if (this.$submitButton) {
                    isValid ? this._onSuccess(e) : this._onError(e);
                }
            };

            /**
             * 检查该字段是否被排除。
             * 返回 true 意味着该字段将不被验证
             *
             * @param {jQuery} $field 字段元素
             * @returns {Boolean}
             */
            this._isExcluded = function ($field) {
                var excludedAttr = $field.attr('data-excluded'),
                // 在初始化字段时，仍然需要检查 “name” 属性
                    field = $field.attr('data-field') || $field.attr('name');

                switch (true) {
                    case (!!field && this.options.fields && this.options.fields[field] && (this.options.fields[field].excluded === 'true' || this.options.fields[field].excluded === true)):
                    case (excludedAttr === 'true'):
                    case (excludedAttr === ''):
                        return true;

                    case (!!field && this.options.fields && this.options.fields[field] && (this.options.fields[field].excluded === 'false' || this.options.fields[field].excluded === false)):
                    case (excludedAttr === 'false'):
                        return false;

                    default:
                        if (this.options.excluded) {
                            // 先转换为数组
                            if ('string' === typeof this.options.excluded) {
                                this.options.excluded = $.map(this.options.excluded.split(','), function (item) {
                                    // 调整的空间
                                    return $.trim(item);
                                });
                            }

                            var length = this.options.excluded.length;
                            for (var i = 0; i < length; i++) {
                                if (('string' === typeof this.options.excluded[i] && $field.is(this.options.excluded[i]))
                                    || ('function' === typeof this.options.excluded[i] && this.options.excluded[i].call(this, $field, this) === true)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                }
            };


            /**
             * 验证字段元素后调用
             *
             * @param {jQuery} $field 字段元素
             * @param {String} [validatorName] 验证器名称
             */
            this._onFieldValidated = function ($field, validatorName) {
                var field = $field.attr('data-field'),
                    validators = this.options.fields[field].validators,
                    counter = {},
                    numValidators = 0,
                    data = {
                        bv: this,
                        field: field,
                        element: $field,
                        validator: validatorName
                    };

                // 在给定验证器完成后触发事件
                if (validatorName) {
                    switch ($field.data('iv.result.' + validatorName)) {
                        case this.STATUS_INVALID:
                            $field.trigger($.Event('error.validator'), data);
                            break;
                        case this.STATUS_VALID:
                            $field.trigger($.Event('success.validator'), data);
                            break;
                        default:
                            break;
                    }
                }

                counter[this.STATUS_NOT_VALIDATED] = 0;
                counter[this.STATUS_VALIDATING] = 0;
                counter[this.STATUS_INVALID] = 0;
                counter[this.STATUS_VALID] = 0;

                for (var v in validators) {
                    if (validators.hasOwnProperty(v)) {
                        if (validators[v].enabled === false) {
                            continue;
                        }
                    }

                    numValidators++;
                    var result = $field.data('iv.result.' + v);
                    if (result) {
                        counter[result]++;
                    }
                }

                if (counter[this.STATUS_VALID] === numValidators) {
                    // 从无效的字段列表中删除
                    this.$invalidFields = this.$invalidFields.not($field);

                    $field.trigger($.Event('success.field.iv'), data);
                }
                // 如果所有确认器都已完成，且至少有一个验证器不能通过
                else if (counter[this.STATUS_NOT_VALIDATED] === 0 && counter[this.STATUS_VALIDATING] === 0 && counter[this.STATUS_INVALID] > 0) {
                    // 添加到无效字段的列表
                    this.$invalidFields = this.$invalidFields.add($field);

                    $field.trigger($.Event('error.field.iv'), data);
                }
            };


            /**
             * 这是默认的 success.form.iv 事件
             * 当所有字段都有效时，调用它
             *
             * @param {jQuery.Event} e 这是 jQuery 的事件对象
             * @private
             */
            this._onSuccess = function (e) {
                if (e.isDefaultPrevented()) {
                    return;
                }

                // 提交表单
                this.disableSubmitButtons(true);
                this.defaultSubmit();
            };

            /**
             * error.form.iv 事件的默认处理
             * 当有无效字段时调用它
             *
             * @param {jQuery.Event} e 这是 jQuery 的事件对象
             */
            this._onError = function (e) {
                // 检查指定的 event 对象上是否调用了 preventDefault() 方法
                if (e.isDefaultPrevented()) {
                    return;
                }

                if ('submitted' === this.options.live) {
                    // 启用 live 的模式
                    this.options.live = 'enabled';
                    var that = this;
                    for (var field in this.options.fields) {
                        if (this.options.fields.hasOwnProperty(field)) {
                            (function (f) {
                                var fields = that.getFieldElements(f);
                                if (fields.length) {
                                    var type = $(fields[0]).attr('type'),
                                        event = ('radio' === type || 'checkbox' === type || 'file' === type || 'SELECT' === $(fields[0]).get(0).tagName) ? 'change' : that._changeEvent,
                                        trigger = that.options.fields[field].trigger || that.options.trigger || event,
                                        events = $.map(trigger.split(' '), function (item) {
                                            return item + '.live.iv';
                                        }).join(' ');

                                    fields.off(events).on(events, function () {
                                        if (that._exceedThreshold($(this))) {
                                            that.validateField($(this));
                                        }
                                    });
                                }
                            })(field);
                        }
                    }
                }

                var $invalidField = this.$invalidFields.eq(0);
                if ($invalidField) {
                    // 激活包含无效字段的选项卡
                    var $tabPane = $invalidField.parents('.tab-pane'), tabId;
                    if ($tabPane && (tabId = $tabPane.attr('id'))) {
                        $('a[href="#' + tabId + '"][data-toggle="tab"]').tab('show');
                    }

                    // 焦点选中第一个无效字段
                    $invalidField.focus();
                }
            };

            // --
            // 公用方法
            // --

            /**
             * 禁用/启用提交按钮
             *
             * @param {Boolean} disabled 可以是 true 或 false
             * @returns {ito}
             */
            this.disableSubmitButtons = function (disabled) {
                if (!disabled) {
                    this.$form.find(this.options.submitButtons).removeAttr('disabled');
                } else if (this.options.live !== 'disabled') {
                    // 如果live验证模式是禁用的，不要禁用
                    this.$form.find(this.options.submitButtons).attr('disabled', 'disabled');
                }

                return this;
            };

            /**
             * 表单验证
             *
             * @returns {ito}
             */
            this.validate = function () {
                if (!this.options.fields) {
                    return this;
                }
                this.disableSubmitButtons(true);

                for (var field in this.options.fields) {
                    if (this.options.fields.hasOwnProperty(field)) {
                        this.validateField(field);
                    }
                }

                this._submit();

                return this;
            };

            /**
             * 自定义验证方法，验证表单是否通过
             * @returns {*}
             */
            this.validateForm = function () {
                if (!this.options.fields) {
                    return this;
                }
                this.disableSubmitButtons(true);

                for (var field in this.options.fields) {
                    if (this.options.fields.hasOwnProperty(field)) {
                        this.validateField(field);
                    }
                }

                return this.$invalidFields.length <= 0;
            };


            /**
             * 验证指定字段
             *
             * @param {String|jQuery} field 字段名或字段元素
             * @returns {ito}
             */
            this.validateField = function (field) {
                var fields = $([]);
                switch (typeof field) {
                    case 'object':
                        fields = field;
                        field = field.attr('data-field');
                        break;
                    case 'string':
                        fields = this.getFieldElements(field);
                        break;
                    default:
                        break;
                }

                if (this.options.fields[field] && this.options.fields[field].enabled === false) {
                    return this;
                }

                var that = this,
                    type = fields.attr('type'),
                    total = ('radio' === type || 'checkbox' === type) ? 1 : fields.length,
                    updateAll = ('radio' === type || 'checkbox' === type),
                    validators = this.options.fields[field].validators,
                    validatorName,
                    validateResult;

                for (var i = 0; i < total; i++) {
                    var $field = fields.eq(i);
                    if (this._isExcluded($field)) {
                        continue;
                    }

                    for (validatorName in validators) {
                        if (validators.hasOwnProperty(validatorName)) {
                            if ($field.data('iv.dfs.' + validatorName)) {
                                $field.data('iv.dfs.' + validatorName).reject();
                            }

                            // 如果字段已经完成，则不要验证该字段
                            var result = $field.data('iv.result.' + validatorName);
                            if (result === this.STATUS_VALID || result === this.STATUS_INVALID || validators[validatorName].enabled === false) {
                                this._onFieldValidated($field, validatorName);
                                continue;
                            }

                            $field.data('iv.result.' + validatorName, this.STATUS_VALIDATING);
                            validateResult = this.validators[validatorName].validate(this, $field, validators[validatorName]);

                            // validateResult 可以是 $.Deferred 对象 ...
                            if ('object' === typeof validateResult && validateResult.resolve) {
                                this.updateStatus(updateAll ? field : $field, this.STATUS_VALIDATING, validatorName);
                                $field.data('iv.dfs.' + validatorName, validateResult);

                                validateResult.done(function ($f, v, isValid, message) {
                                    // v 是验证器名称
                                    $f.removeData('iv.dfs.' + v);
                                    if (message) {
                                        that.updateMessage($f, v, message);
                                    }

                                    that.updateStatus(updateAll ? $f.attr('data-field') : $f, isValid ? that.STATUS_VALID : that.STATUS_INVALID, v);

                                    if (isValid && that._submitIfValid === true) {
                                        // 如果远程验证器返回true，并且表单已经准备好提交，那么就执行它
                                        that._submit();
                                    }
                                });
                            }
                            // ... or object { valid: true/false, message: 'dynamic message' }
                            else if ('object' === typeof validateResult && validateResult.valid !== undefined && validateResult.message !== undefined) {
                                this.updateMessage(updateAll ? field : $field, validatorName, validateResult.message);
                                this.updateStatus(updateAll ? field : $field, validateResult.valid ? this.STATUS_VALID : this.STATUS_INVALID, validatorName);
                            }
                            // ... or a boolean value
                            else if ('boolean' === typeof validateResult) {
                                this.updateStatus(updateAll ? field : $field, validateResult ? this.STATUS_VALID : this.STATUS_INVALID, validatorName);
                            }
                        }
                    }
                }

                return this;
            };

            /**
             * 更新错误消息
             *
             * @param {String|jQuery} field 字段名或字段元素
             * @param {String} validator 验证 name
             * @param {String} message 提示信息
             * @returns {ito}
             */
            this.updateMessage = function (field, validator, message) {
                var $fields = $([]);
                switch (typeof field) {
                    case 'object':
                        $fields = field;
                        field = field.attr('data-field');
                        break;
                    case 'string':
                        $fields = this.getFieldElements(field);
                        break;
                    default:
                        break;
                }

                $fields.each(function () {
                    $(this).data('iv.messages').find('.help-block[data-validator="' + validator + '"][data-for="' + field + '"]').html(message);
                });
            };

            /**
             * 更新所有字段验证结果
             *
             * @param {String|jQuery} field 字段名或字段元素
             * @param {String} status 状态。'NOT_VALIDATED', 'VALIDATING', 'INVALID' or 'VALID'
             * @param {String} [validatorName] 验证的 name。如果是null，则该方法更新所有验证器的有效性结果
             * @returns {ito}
             */
            this.updateStatus = function (field, status, validatorName) {
                var fields = $([]);
                switch (typeof field) {
                    case 'object':
                        fields = field;
                        field = field.attr('data-field');
                        break;
                    case 'string':
                        fields = this.getFieldElements(field);
                        break;
                    default:
                        break;
                }

                if (status === this.STATUS_NOT_VALIDATED) {
                    // 重置提交标识
                    this._submitIfValid = false;
                }

                var that = this,
                    type = fields.attr('type'),
                    group = this.options.fields[field].group || this.options.group,
                    total = ('radio' === type || 'checkbox' === type) ? 1 : fields.length;

                for (var i = 0; i < total; i++) {
                    var $field = fields.eq(i);
                    if (this._isExcluded($field)) {
                        continue;
                    }

                    var $parent = $field.parents(group),
                        $message = $field.data('iv.messages'),
                        $allErrors = $message.find('.help-block[data-validator][data-for="' + field + '"]'),
                        $errors = validatorName ? $allErrors.filter('[data-validator="' + validatorName + '"]') : $allErrors,
                        $icon = $parent.find('.form-control-feedback[data-icon-for="' + field + '"]'),
                        container = this.options.fields[field].container || this.options.container,
                        isValidField = null;

                    // 更新状态
                    if (validatorName) {
                        $field.data('iv.result.' + validatorName, status);
                    } else {
                        for (var v in this.options.fields[field].validators) {
                            $field.data('iv.result.' + v, status);
                        }
                    }

                    // 显示/隐藏 错误元素和图标
                    $errors.attr('data-result', status);

                    // 确定包含元素的选项卡
                    var $tabPane = $field.parents('.tab-pane'),
                        tabId, $tab;
                    if ($tabPane && (tabId = $tabPane.attr('id'))) {
                        $tab = $('a[href="#' + tabId + '"][data-toggle="tab"]').parent();
                    }

                    switch (status) {
                        case this.STATUS_VALIDATING:
                            isValidField = null;
                            this.disableSubmitButtons(true);
                            $parent.removeClass('has-success').removeClass('has-error');
                            if ($icon) {
                                $icon.removeClass(this.options.feedbackIcons.valid).removeClass(this.options.feedbackIcons.invalid).addClass(this.options.feedbackIcons.validating).show();
                            }
                            if ($tab) {
                                $tab.removeClass('iv-tab-success').removeClass('iv-tab-error');
                            }
                            break;

                        case this.STATUS_INVALID:
                            isValidField = false;
                            this.disableSubmitButtons(true);
                            $parent.removeClass('has-success').addClass('has-error');
                            if ($icon) {
                                $icon.removeClass(this.options.feedbackIcons.valid).removeClass(this.options.feedbackIcons.validating).addClass(this.options.feedbackIcons.invalid).show();
                            }
                            if ($tab) {
                                $tab.removeClass('iv-tab-success').addClass('iv-tab-error');
                            }
                            break;

                        case this.STATUS_VALID:
                            // 如果字段是有效的(通过所有验证)
                            isValidField = ($allErrors.filter('[data-result="' + this.STATUS_NOT_VALIDATED + '"]').length === 0)
                                ? ($allErrors.filter('[data-result="' + this.STATUS_VALID + '"]').length === $allErrors.length)  // 所有验证都已完成
                                : null;                                                                                            // 有些验证还没有完成
                            if (isValidField !== null) {
                                this.disableSubmitButtons(this.$submitButton ? !this.isValid() : !isValidField);
                                if ($icon) {
                                    $icon.removeClass(this.options.feedbackIcons.invalid).removeClass(this.options.feedbackIcons.validating).removeClass(this.options.feedbackIcons.valid)
                                        .addClass(isValidField ? this.options.feedbackIcons.valid : this.options.feedbackIcons.invalid).show();
                                }
                            }

                            $parent.removeClass('has-error has-success').addClass(this.isValidContainer($parent) ? 'has-success' : 'has-error');
                            if ($tab) {
                                $tab.removeClass('iv-tab-success').removeClass('iv-tab-error').addClass(this.isValidContainer($tabPane) ? 'iv-tab-success' : 'iv-tab-error');
                            }
                            break;

                        case this.STATUS_NOT_VALIDATED:
                        /* 失败 */
                        default:
                            isValidField = null;
                            this.disableSubmitButtons(false);
                            $parent.removeClass('has-success').removeClass('has-error');
                            if ($icon) {
                                $icon.removeClass(this.options.feedbackIcons.valid).removeClass(this.options.feedbackIcons.invalid).removeClass(this.options.feedbackIcons.validating).hide();
                            }
                            if ($tab) {
                                $tab.removeClass('iv-tab-success').removeClass('iv-tab-error');
                            }
                            break;
                    }

                    switch (true) {
                        // 只显示第一个错误消息，如果它被放置在工具提示中…
                        case ($icon && 'tooltip' === container):
                            (isValidField === false)
                                ? $icon.css('cursor', 'pointer').tooltip('destroy').tooltip({
                                html: true,
                                placement: 'top',
                                title: $allErrors.filter('[data-result="' + that.STATUS_INVALID + '"]').eq(0).html()
                            })
                                : $icon.css('cursor', '').tooltip('destroy');
                            break;
                        // ... or popover
                        case ($icon && 'popover' === container):
                            (isValidField === false)
                                ? $icon.css('cursor', 'pointer').popover('destroy').popover({
                                content: $allErrors.filter('[data-result="' + that.STATUS_INVALID + '"]').eq(0).html(),
                                html: true,
                                placement: 'top',
                                trigger: 'hover click'
                            })
                                : $icon.css('cursor', '').popover('destroy');
                            break;
                        default:
                            (status === this.STATUS_INVALID) ? $errors.show() : $errors.hide();
                            break;
                    }

                    // Trigger an event
                    $field.trigger($.Event('status.field.iv'), {
                        bv: this,
                        field: field,
                        element: $field,
                        status: status
                    });
                    this._onFieldValidated($field, validatorName);
                }

                return this;
            };

            /**
             * 检查表单有效性
             *
             * @returns {Boolean}
             */
            this.isValid = function () {
                for (var field in this.options.fields) {
                    if (this.options.fields.hasOwnProperty(field)) {
                        if (!this.isValidField(field)) {
                            return false;
                        }
                    }
                }

                return true;
            };

            /**
             * 检查字段是否有效
             *
             * @param {String|jQuery} field 字段名或字段元素
             * @returns {Boolean}
             */
            this.isValidField = function (field) {
                var fields = $([]);
                switch (typeof field) {
                    case 'object':
                        fields = field;
                        field = field.attr('data-field');
                        break;
                    case 'string':
                        fields = this.getFieldElements(field);
                        break;
                    default:
                        break;
                }
                if (fields.length === 0 || this.options.fields[field] === null || this.options.fields[field].enabled === false) {
                    return true;
                }

                var type = fields.attr('type'),
                    total = ('radio' === type || 'checkbox' === type) ? 1 : fields.length,
                    $field, validatorName, status;
                for (var i = 0; i < total; i++) {
                    $field = fields.eq(i);
                    if (this._isExcluded($field)) {
                        continue;
                    }

                    for (validatorName in this.options.fields[field].validators) {
                        if (this.options.fields[field].validators.hasOwnProperty(validatorName)) {
                            if (this.options.fields[field].validators[validatorName].enabled === false) {
                                continue;
                            }
                        }

                        status = $field.data('iv.result.' + validatorName);
                        if (status !== this.STATUS_VALID) {
                            return false;
                        }
                    }
                }

                return true;
            };


            this.defaultSubmit = function () {
                if (this.$submitButton) {
                    // 创建一个隐藏的提交按钮
                    $('<input/>').attr('type', 'hidden')
                        .attr('data-submit-hidden', '')
                        .attr('name', this.$submitButton.attr('name'))
                        .val(this.$submitButton.val())
                        .appendTo(this.$form);
                }

                // 提交表单
                this.$form.off('submit.iv').submit();
            };

            /**
             * 以给定的名称检索字段元素
             *
             * @param {String} field 字段名
             * @returns {null|jQuery[]}
             */
            this.getFieldElements = function (field) {
                if (!this._cacheFields[field]) {
                    this._cacheFields[field] = (this.options.fields[field] && this.options.fields[field].selector)
                        ? $(this.options.fields[field].selector)
                        : this.$form.find('[name="' + field + '"]');
                }

                return this._cacheFields[field];
            };

            /**
             * 可能没用了
             *
             *
             * 检查给定容器内的所有字段是否有效。
             * 当使用像tab这样的向导时，它很有用
             *
             * @param {String|jQuery} container 容器选择器或元素
             * @returns {Boolean}
             */
            this.isValidContainer = function (container) {
                var that = this,
                    map = {},
                    $container = ('string' === typeof container) ? $(container) : container;
                if ($container.length === 0) {
                    return true;
                }

                $container.find('[data-field]').each(function () {
                    var $field = $(this),
                        field = $field.attr('data-field');
                    if (!that._isExcluded($field) && !map[field]) {
                        map[field] = $field;
                    }
                });

                for (var field in map) {
                    if (map.hasOwnProperty(field)) {
                        var $f = map[field];
                        if ($f.data('iv.messages')
                                .find('.help-block[data-validator][data-for="' + field + '"]')
                                .filter(function () {
                                    var v = $(this).attr('data-validator'),
                                        f = $(this).attr('data-for');
                                    return (that.options.fields[f].validators[v].enabled !== false
                                    && $f.data('iv.result.' + v) && $f.data('iv.result.' + v) !== that.STATUS_VALID);
                                })
                                .length !== 0) {
                            // 这个字段是无效的
                            return false;
                        }
                    }
                }

                return true;
            };

            this.getDynamicOption = function (field, option) {
                var $field = ('string' === typeof field) ? this.getFieldElements(field) : field,
                    value = $field.val();

                if ('function' === typeof option) {
                    return ito.util.call(option, [value, this, $field]);
                }

                else if ('string' === typeof option) {
                    var $f = this.getFieldElements(option);
                    if ($f.length) {
                        return $f.val();
                    }
                    else {
                        return ito.util.call(option, [value, this, $field]);
                    }
                }

                return null;
            };


            /**
             * 可能没用了
             *
             *
             * 获取元素以放置错误消息
             *
             * @param {jQuery} $field field元素
             * @param {String} group 组
             * @returns {jQuery}
             */
            this._getMessageContainer = function ($field, group) {
                var $parent = $field.parent();
                if ($parent.is(group)) {
                    return $parent;
                }

                var cssClasses = $parent.attr('class');
                if (!cssClasses) {
                    return this._getMessageContainer($parent, group);
                }

                cssClasses = cssClasses.split(' ');
                var n = cssClasses.length;
                for (var i = 0; i < n; i++) {
                    if (/^col-(xs|sm|md|lg)-\d+$/.test(cssClasses[i]) || /^col-(xs|sm|md|lg)-offset-\d+$/.test(cssClasses[i])
                        || /ito-in-block/.test(cssClasses[i]) || /con-layout/.test(cssClasses[i]) || /layui-input-block/.test(cssClasses[i])) {
                        return $parent;
                    }
                }

                return this._getMessageContainer($parent, group);
            };

            this._init();
        }
    };

    return ito;
});