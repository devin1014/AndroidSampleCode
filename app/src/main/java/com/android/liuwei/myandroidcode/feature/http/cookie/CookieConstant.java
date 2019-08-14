package com.android.liuwei.myandroidcode.feature.http.cookie;

import java.net.URLEncoder;

/**
 * User: liuwei
 * Date: 2018-06-11
 * Time: 16:27
 */
public class CookieConstant
{
    private static final String URL_FROM = "http://vipsports-android.regclientuser.cntv.cn";

    public static final String URL_VIP_SPORTS = "http://vip.sports.cctv.com/";
    public static final String URL_CNTV = "https://reg.cntv.cn/";
    public static final String URL_CHECK_SESSION = URL_VIP_SPORTS + "passport/checkSSO.do";
    public static final String URL_CHECK_DO = URL_VIP_SPORTS + "check.do";
    public static final String URL_SIGNIN_USERNAME = URL_CNTV + "login/login.action?" +
            "username=${username}&password=${password}&service=client_transaction&from=" + URLEncoder.encode(URL_FROM);
    public static final String URL_SIGNIN_TICKET = URL_VIP_SPORTS + "passport/loginCNTV.do?ticket=${ticket}&from=" + URLEncoder.encode(URL_FROM);
    public static final String URL_ACCOUNT_THUMBNAIL = "http://my.cntv.cn/intf/napi/api.php?client=vipsports&method=user.getNickNameAndFace&userid=%E4%BC%9A%E5%86%99%E7%A8%8B%E5%BA%8F%E7%9A%84%E7%8C%AA~&snap=200x200&format=json&cb=abc";

    public static final String SPORTS_VIP_USERNAME = "liuwei10074180@163.com";
    public static final String SPORTS_VIP_PASSWORD = "liuwei00";
}
