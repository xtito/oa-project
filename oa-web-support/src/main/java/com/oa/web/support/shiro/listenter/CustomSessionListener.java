package com.oa.web.support.shiro.listenter;

import com.oa.web.support.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * shiro 会话 监听
 *
 * Created by [张渊]
 * 2017/11/4 12:29
 */
public class CustomSessionListener implements SessionListener {

    private ShiroSessionRepository shiroSessionRepository;

    /**
     * 一个会话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        // TODO 一个会话的生命周期开始
        System.out.println("CustomSessionListener--->onStart =================== on start");
    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        // TODO 一个会话的生命周期结束
        System.out.println("CustomSessionListener--->onSop ===================== on stop");
    }

    @Override
    public void onExpiration(Session session) {
        this.getShiroSessionRepository().deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }
}
