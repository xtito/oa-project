package com.oa.bean.sys;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * Created by [张渊]
 * 2017/11/4 13:16
 */
public class UserOnline extends SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sessionId;// Session Id
    private String host;// Session Host
    private Date startTime;// Session创建时间
    private Date lastAccess;// Session最后交互时间
    private long timeout;// Session timeout
    private boolean sessionStatus = Boolean.TRUE;// session 是否踢出

    public UserOnline() {
    }

    public UserOnline(SysUser user) {
        super(user);
    }
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
