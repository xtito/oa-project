package com.oa.core.constant;

/**
 * Session 中的常量
 *
 */
public interface SessionConst {

    //当前登录用户
    String CURR_LOGIN_USER = "SESSION_CURR_LOGIN_USER";

    //当前登录企业用户
    String CURR_LOGIN_ENTERPRISE_USER = "CURR_LOGIN_ENTERPRISE_USER";

    //当前企业用户登录方式：易速|网站
    String CURR_LOGIN_TYPE = "CURR_LOGIN_TYPE";

    //当前登录用户ID
    String CURR_LOGIN_USER_ID = "CURR_LOGIN_USER_ID";

    //当前登录用户的用户名
    String CURR_LOGIN_USER_LOGIN_NAME = "CURR_LOGIN_USER_LOGIN_NAME";

    //当前登录用户的昵称
    String CURR_LOGIN_USER_DISPLAY_NAME = "CURR_LOGIN_USER_DISPLAY_NAME";

    //登录验证码
    String LOGIN_USER_CODE = "LOGIN_USER_CODE";

    //密码错误次数
    String LOGIN_COUNT_ERR = "LOGIN_COUNT_ERR";

    //临时用户名
    String TEMP_LOGIN_NAME = "TEMP_LOGIN_NAME";

    //当前方法的平台
    String CURR_LOGIN_WEB = "CURR_LOGIN_WEB";

    //机器人临时对话记录
    String CURR_ROBOT_LOG = "CURR_ROBOT_LOG";

    String LOGIN_TOKEN = "LOGIN_TOKEN";

    String LOGIN_REQUEST_URL = "LOGIN_REQUEST_URL";

    //所有菜单项
    String PERMISSION_ALL = "PERMISSION_ALL";

    //所有菜单项map
    String PERMISSION_ALL_MAP = "PERMISSION_ALL_MAPs";

    //当前用户菜单项
    String PERMISSION_CURRENT_USER = "PERMISSION_CURRENT_USER";

    //当前用户菜单项map
    String PERMISSION_CURRENT_USER_MAP = "PERMISSION_CURRENT_USER_MAP";

    //当前用户一级菜单项
    String PERMISSION_CURRENT_USER_LEVEL_1 = "PERMISSION_CURRENT_USER_LEVEL_1";

    //当前用户二级菜单项
    String PERMISSION_CURRENT_USER_LEVEL_2 = "PERMISSION_CURRENT_USER_LEVEL_2";

    //问卷回答次数
    String SURVEY_LOG="SURVEY";

    //问题浏览量
    String QUESTION_COUNT="QUESTION_COUNT";

}
