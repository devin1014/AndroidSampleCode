package com.android.liuwei.myandroidcode.feature.cookie.bean;

import java.util.List;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 16:09
 */
public class LogInBean
{

    /**
     * timestamp : 2018-06-11 15:35:54
     * ticket : 9e963891-8000-443e-b621-b53c4eaf6dc8
     * errType : 0
     * errMsg : 成功
     * sso : []
     * user_seq_id : 19108346
     * usrid : 20120607113248432390
     */

    private String timestamp;
    private String ticket;
    private String errType;
    private String errMsg;
    private String user_seq_id;
    private String usrid;
    private List<?> sso;

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getTicket()
    {
        return ticket;
    }

    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    public String getErrType()
    {
        return errType;
    }

    public void setErrType(String errType)
    {
        this.errType = errType;
    }

    public String getErrMsg()
    {
        return errMsg;
    }

    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    public String getUser_seq_id()
    {
        return user_seq_id;
    }

    public void setUser_seq_id(String user_seq_id)
    {
        this.user_seq_id = user_seq_id;
    }

    public String getUsrid()
    {
        return usrid;
    }

    public void setUsrid(String usrid)
    {
        this.usrid = usrid;
    }

    public List<?> getSso()
    {
        return sso;
    }

    public void setSso(List<?> sso)
    {
        this.sso = sso;
    }
}
