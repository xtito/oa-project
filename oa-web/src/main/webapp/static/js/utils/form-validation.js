/**
 @Name：表单验证
 @Author：张渊
 @License：1.0.0
*/
define(["jquery"], function ($) {



    function randomNumber() {
        return randomFromInterval(1, 1e6)
    }

    function randomFromInterval(e, t) {
        return Math.floor(Math.random() * (t - e + 1) + e)
    }

    return {
        inputAndTip: [],
        checkForm: function (form, isModal) {
            var errorCount = 0;
            if (form == null)
                return false;
            var inputs = $(form).find("textArea,input,select");

            errorCount = this.checkInput(inputs, errorCount);

            return errorCount == 0;
        }, checkPassWord: function (p1, p2) {
            var errorCount = 0;
            if (p1 == null || p2 == null)
                return false;

            var pv1 = $(p1).val();
            var pv2 = $(p2).val();
            $(p1).parent().find(".errorInfo").html('');
            $(p2).parent().find(".errorInfo").html('');
            if (pv1 != pv2) {
                var infoId = ("infoId-" + randomNumber());
                $(p1).after("<div class='errorInfo' id='" + infoId + "'>两次输入的密码不一致</div>");
                infoId = ("infoId-" + randomNumber());
                $(p2).after("<div class='errorInfo' id='" + infoId + "'>两次输入的密码不一致</div>");
                errorCount++;
            }
            return errorCount == 0;
        }, clear: function (inputs) {
            $(inputs).removeClass("has_error");

            for (var i = 0; i < inputs.length; i++) {
                for (var j = this.inputAndTip.length - 1; j >= 0; j--) {
                    if (inputs[i].id == this.inputAndTip[j].inputId) {
                        $("#" + this.inputAndTip[j].infoId).remove();
                        this.inputAndTip.splice(j, 1);
                    }
                }
            }
        },
        checkInput: function (inputs, errorCount) {
            this.clear(inputs);
            if (!errorCount) errorCount = 0;
            for (var i = 0; i < inputs.length; i++) {

                var $input = $(inputs[i]);
                if ($input.is("input[type=checkbox], input[type=radio], input:hidden")) {
                    continue;
                }

                $(inputs[i]).closest(".in-group").removeClass("has-error");

                var flag = true;

                if ($(inputs[i]).attr("data-repetition")) {
                    flag = $("#" + $(inputs[i]).attr("data-repetition")).val() == $(inputs[i]).val();
                }

                if (($(inputs[i]).attr("data-reg") != null
                    && !eval("/" + $(inputs[i]).attr("data-reg") + "/").test(inputs[i].value)) || !flag) {

                    console.log("name=" + inputs[i].name + ",data-reg=" + $(inputs[i]).attr("data-reg") + ", data-error=" + $(inputs[i]).attr("data-error"));
                    $(inputs[i]).closest(".in-group").addClass("has-error");

                    var data_error = $(inputs[i]).attr("data-error");

                    var infoId = ("infoId-" + randomNumber());
                    var inputId = inputs[i].id ? inputs[i].id : "inputId-" + randomNumber();

                    //$(inputs[i]).after("<div class='errorInfo' id='" + infoId + "'>" + $(inputs[i]).attr("data-error") + "</div>");

                    if(data_error == null) {
                        $(inputs[i]).closest(".in-group").addClass(".has-error");
                    } else {
                        $(inputs[i]).closest(".in-group").addClass(".has-error").find("small").text(data_error);
                    }

                    $(inputs[i]).attr("id", inputId);

                    this.inputAndTip.push({
                        infoId: infoId,
                        inputId: inputId
                    });

                    errorCount++;
                }
            }
            return errorCount;
        }
    };
});