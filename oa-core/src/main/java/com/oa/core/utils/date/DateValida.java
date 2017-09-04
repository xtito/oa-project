package com.oa.core.utils.date;

import com.oa.core.constant.RegexConstant;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;

import java.util.Date;

/**
 * 日期格式验证
 *
 * Created by [Zy]
 * 2017/8/10 11:28
 */
public class DateValida {

    private DateValida() {}

    /**
     * 判断起始日期和结束日期是否合法
     * @param start 起始日期
     * @param end 结束日期
     * @throws ValidateException 验证日期是否合法
     */
    public static void startDateOrEndDateValida(String start, String end) throws ValidateException {

        String dateFormat = "yyyy-MM-dd";

        if (StringUtil.isNotNull(start) || StringUtil.isNotNull(end)) {

            dateValidate(start, "起始");

            dateValidate(end, "截止");

            // 如果验证都通过，说明日期格式也正确则判断起始日期是否小于截止日期
            Date startDate = DateUtil.parse(start, dateFormat);
            Date endDate = DateUtil.parse(end, dateFormat);
            if (startDate != null && endDate != null && startDate.after(endDate)) {
                throw new ValidateException("起始日期不能大于截止日期");
            }
        }
    }


    /**
     * 判断日期是否合法
     * @param date 需要验证的日期
     * @param before 日期描述前缀
     * @throws ValidateException 验证失败异常
     */
    public static void dateValidate(String date, String...before) throws ValidateException {

        String defaultBefore = "";

        if (before != null && before.length > 0 && StringUtil.isNotNull(before[0])) {
            defaultBefore = before[0];
        }

        if (StringUtil.isEmpty(date)) {
            throw new ValidateException(defaultBefore + "日期不能为空");
        } else if (!date.matches(RegexConstant.DATE_YYYY_MM_DD)){
            throw new ValidateException(defaultBefore + "日期不合法");
        }
    }

}
