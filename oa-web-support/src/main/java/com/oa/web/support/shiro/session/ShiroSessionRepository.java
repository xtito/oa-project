package com.oa.web.support.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Session操作，仓库
 *
 * Created by [张渊]
 * 2017/10/29 21:57
 */
public interface ShiroSessionRepository {

    /**
     * 存储Session
     * @param session 要存储的session
     */
    void saveSession(Session session);

    /**
     * 根据sessionId删除session
     * @param sessionId 要删除的session的id
     */
    void deleteSession(Serializable sessionId);

    /**
     * 根据sessionId获取session
     * @param sessionId 要查询的session的id
     */
    Session getSession(Serializable sessionId);

    /**
     * 获取所有sessoin
     */
    Collection<Session> getAllSessions();

}
