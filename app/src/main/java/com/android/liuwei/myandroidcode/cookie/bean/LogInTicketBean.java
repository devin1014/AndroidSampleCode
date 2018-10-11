package com.android.liuwei.myandroidcode.cookie.bean;

import java.util.List;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 16:34
 */
public class LogInTicketBean
{

    /**
     * code : 200
     * error : false
     * messages : {"data":{"session":{"id":"uv38bm4ufwj0nec41pciivmudqtbieir","expires":-1},"user":{"id":"passport_63828","userName":"会写程序的猪~","nickName":"会写程序的猪~","status":0,"profile":"","logins":[{"type":"cntv","loginId":"liuwei10074180@163.com","loginName":"19108346","status":1,"current":true}],"lastLoginTime":"2013-06-21T08:54:56.000+08:00","userId":"passport_63828"}}}
     * ok : true
     * redirect : false
     * verify : false
     */

    private int code;
    private boolean error;
    private MessagesBean messages;
    private boolean ok;
    private boolean redirect;
    private boolean verify;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public MessagesBean getMessages()
    {
        return messages;
    }

    public void setMessages(MessagesBean messages)
    {
        this.messages = messages;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setOk(boolean ok)
    {
        this.ok = ok;
    }

    public boolean isRedirect()
    {
        return redirect;
    }

    public void setRedirect(boolean redirect)
    {
        this.redirect = redirect;
    }

    public boolean isVerify()
    {
        return verify;
    }

    public void setVerify(boolean verify)
    {
        this.verify = verify;
    }

    public static class MessagesBean
    {
        /**
         * data : {"session":{"id":"uv38bm4ufwj0nec41pciivmudqtbieir","expires":-1},"user":{"id":"passport_63828","userName":"会写程序的猪~","nickName":"会写程序的猪~","status":0,"profile":"","logins":[{"type":"cntv","loginId":"liuwei10074180@163.com","loginName":"19108346","status":1,"current":true}],"lastLoginTime":"2013-06-21T08:54:56.000+08:00","userId":"passport_63828"}}
         */

        private DataBean data;

        public DataBean getData()
        {
            return data;
        }

        public void setData(DataBean data)
        {
            this.data = data;
        }

        public static class DataBean
        {
            /**
             * session : {"id":"uv38bm4ufwj0nec41pciivmudqtbieir","expires":-1}
             * user : {"id":"passport_63828","userName":"会写程序的猪~","nickName":"会写程序的猪~","status":0,"profile":"","logins":[{"type":"cntv","loginId":"liuwei10074180@163.com","loginName":"19108346","status":1,"current":true}],"lastLoginTime":"2013-06-21T08:54:56.000+08:00","userId":"passport_63828"}
             */

            private SessionBean session;
            private UserBean user;

            public SessionBean getSession()
            {
                return session;
            }

            public void setSession(SessionBean session)
            {
                this.session = session;
            }

            public UserBean getUser()
            {
                return user;
            }

            public void setUser(UserBean user)
            {
                this.user = user;
            }

            public static class SessionBean
            {
                /**
                 * id : uv38bm4ufwj0nec41pciivmudqtbieir
                 * expires : -1
                 */

                private String id;
                private int expires;

                public String getId()
                {
                    return id;
                }

                public void setId(String id)
                {
                    this.id = id;
                }

                public int getExpires()
                {
                    return expires;
                }

                public void setExpires(int expires)
                {
                    this.expires = expires;
                }
            }

            public static class UserBean
            {
                /**
                 * id : passport_63828
                 * userName : 会写程序的猪~
                 * nickName : 会写程序的猪~
                 * status : 0
                 * profile :
                 * logins : [{"type":"cntv","loginId":"liuwei10074180@163.com","loginName":"19108346","status":1,"current":true}]
                 * lastLoginTime : 2013-06-21T08:54:56.000+08:00
                 * userId : passport_63828
                 */

                private String id;
                private String userName;
                private String nickName;
                private int status;
                private String profile;
                private String lastLoginTime;
                private String userId;
                private List<LoginsBean> logins;

                public String getId()
                {
                    return id;
                }

                public void setId(String id)
                {
                    this.id = id;
                }

                public String getUserName()
                {
                    return userName;
                }

                public void setUserName(String userName)
                {
                    this.userName = userName;
                }

                public String getNickName()
                {
                    return nickName;
                }

                public void setNickName(String nickName)
                {
                    this.nickName = nickName;
                }

                public int getStatus()
                {
                    return status;
                }

                public void setStatus(int status)
                {
                    this.status = status;
                }

                public String getProfile()
                {
                    return profile;
                }

                public void setProfile(String profile)
                {
                    this.profile = profile;
                }

                public String getLastLoginTime()
                {
                    return lastLoginTime;
                }

                public void setLastLoginTime(String lastLoginTime)
                {
                    this.lastLoginTime = lastLoginTime;
                }

                public String getUserId()
                {
                    return userId;
                }

                public void setUserId(String userId)
                {
                    this.userId = userId;
                }

                public List<LoginsBean> getLogins()
                {
                    return logins;
                }

                public void setLogins(List<LoginsBean> logins)
                {
                    this.logins = logins;
                }

                public static class LoginsBean
                {
                    /**
                     * type : cntv
                     * loginId : liuwei10074180@163.com
                     * loginName : 19108346
                     * status : 1
                     * current : true
                     */

                    private String type;
                    private String loginId;
                    private String loginName;
                    private int status;
                    private boolean current;

                    public String getType()
                    {
                        return type;
                    }

                    public void setType(String type)
                    {
                        this.type = type;
                    }

                    public String getLoginId()
                    {
                        return loginId;
                    }

                    public void setLoginId(String loginId)
                    {
                        this.loginId = loginId;
                    }

                    public String getLoginName()
                    {
                        return loginName;
                    }

                    public void setLoginName(String loginName)
                    {
                        this.loginName = loginName;
                    }

                    public int getStatus()
                    {
                        return status;
                    }

                    public void setStatus(int status)
                    {
                        this.status = status;
                    }

                    public boolean isCurrent()
                    {
                        return current;
                    }

                    public void setCurrent(boolean current)
                    {
                        this.current = current;
                    }
                }
            }
        }
    }
}
