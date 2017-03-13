package com.local.test.cookie;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by meisei on 2017/3/11.
 */
@RestController
public class CookieController {

    @RequestMapping("/setCookie")
    @ResponseBody
    public String setCookie(HttpServletRequest request, HttpServletResponse response, String domain) {
        Cookie cookie = new Cookie("myCookie", "hello-world");
        cookie.setDomain(domain);
        response.addCookie(cookie);
        return "set cookie completely. domain: " + domain;
    }

    @RequestMapping("/readCookie")
    public String readCookie(HttpServletRequest request, @CookieValue(value = "myCookie", required = false) String cookieValue) {
        String serverName = request.getServerName();
        return "serverName: " + serverName + " cookieValue: " + cookieValue;
    }
}
