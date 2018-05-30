package com.oa.web.test.d_processVariables;

import java.io.Serializable;

/**
 * Created by [张渊]
 * 2018/5/15 22:00
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 477145303085981384L;

    private Integer id;// 编号
    private String name;// 姓名

    private String coeducation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoeducation() {
        return coeducation;
    }

    public void setCoeducation(String coeducation) {
        this.coeducation = coeducation;
    }
}
