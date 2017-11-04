package com.oa.web.support.shiro;

import com.oa.core.LoggerUtil;
import com.oa.web.support.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * Session 操作
 *
 * Created by [张渊]
 * 2017/10/29 16:23
 */
public class CustomShiroSessionDao extends AbstractSessionDAO {

    private ShiroSessionRepository shiroSessionRepository;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.getShiroSessionRepository().saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return this.getShiroSessionRepository().getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.getShiroSessionRepository().saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null) {
            LoggerUtil.error(getClass(), "Session 不能为null");
            return;
        }
        Serializable id = session.getId();
        if (id != null) {
            this.getShiroSessionRepository().deleteSession(id);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return this.getShiroSessionRepository().getAllSessions();
    }


    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }
}
