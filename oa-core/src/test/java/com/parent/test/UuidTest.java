package com.parent.test;

import com.oa.core.utils.StringUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.core.utils.generate.GenerateIdUtil;
import org.junit.Test;

import java.util.Date;

/**
 * Created by [张渊]
 * 2017/8/10 9:59
 */
public class UuidTest {

    @Test
    public void uuid() {
        String id = GenerateIdUtil.createId();
        String uuid = GenerateIdUtil.createUUID();

        System.out.println(id);
        System.out.println(uuid);
    }

    @Test
    public void date() {
        long a = DateUtil.getTimeIntervalSecond(DateUtil.parse("2017-8-10 13:42:55"), new Date());
        System.out.println(a);

        long b = DateUtil.getTimeIntervalMinute(DateUtil.parse("2017-8-10 13:42:55"), new Date());
        System.out.println(b);

        long c = DateUtil.getTimeIntervalMinute("2017-8-10 13:42:55", DateUtil.format(new Date()));
        System.out.println(c);

        String d = DateUtil.getTimeIntervalMinuteStr(DateUtil.parse("2017-8-8 13:42:55"), DateUtil.parse("2017-8-10 13:42:20"));
        System.out.println(d);
    }


    @Test
    public void string() {

        String a = StringUtil.parsDouble(2.2333);
        String b = StringUtil.parsDouble(2.2333, 1);
        String c = StringUtil.parsDouble(2.2336, 3);
        String d = StringUtil.parsDouble(2.0, 3);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }


    @Test
    public void consStr() {
        System.out.println(StringUtil.contains("fdsaf:mysql:dfsafdasfdasfasf:mys1ql:", ":mysql:"));
        System.out.println(StringUtil.contains("fdsaf:oracle:dfsafdasfdasfasf:oracle:", ":oracle:"));
        System.out.println(StringUtil.contains("fdsaf:db2:dfsafdasfdasfasf:db2:", ":db2:"));
        System.out.println(StringUtil.contains("fdsaf:sqlserverdfsafdasfdasfasf:sqlserver", ":sqlserver"));
    }

}
