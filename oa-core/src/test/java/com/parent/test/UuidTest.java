package com.parent.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oa.core.bean.PageBean;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.algorithm.AlgorithmsUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.core.utils.generate.GenerateIdUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

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

        String a = StringUtil.parseDouble(2.2333);
        String b = StringUtil.parseDouble(2.2333, 1);
        String c = StringUtil.parseDouble(2.2336, 3);
        String d = StringUtil.parseDouble(2.0, 3);

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

    @Test
    public void jsonTest() throws IOException {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("success", true);
//        map.put("info", "添加成功");
//        PageBean page = new PageBean();
//        page.setCode(200);
//        page.setMsg("请求成功");
//        List<PageBean> list = new ArrayList<>();
//        list.add(page);
//
//        PageBean page2 = new PageBean();
//        page2.setCode(200);
//        page2.setMsg("请求成功");
//        page2.setList(list);

        String str = "{\"pageNum\":0,\"pageSize\":0,\"currentSize\":0,\"total\":0,\"pages\":0,\"list\":[{\"pageNum\":0,\"pageSize\":0,\"currentSize\":0,\"total\":0,\"pages\":0,\"list\":null,\"orderBy\":null,\"code\":200,\"msg\":\"请求成功\",\"params\":{}}],\"orderBy\":null,\"code\":200,\"msg\":\"请求成功\",\"params\":{}}";

        ObjectMapper mapper = new ObjectMapper();
        PageBean page = mapper.readValue(str, PageBean.class);
        System.out.println(mapper.readValue(str, PageBean.class));
        ;
    }


    @Test
    public void md5Test() {
        String pwd = "admin";
        String email = "mr_z2014@126.com";
        String newPwd = String.format("%s#%s", email, pwd);
        System.out.println(newPwd);
        System.out.println(AlgorithmsUtil.encryptMd5(pwd));
        System.out.println(AlgorithmsUtil.encrypt(pwd));
        System.out.println(AlgorithmsUtil.encrypt("admin", "admin"));
    }

}
