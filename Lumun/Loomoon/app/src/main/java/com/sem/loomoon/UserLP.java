package com.sem.loomoon;

public class UserLP {

    private String login;
    private String pass;
    private String cookie;
    private String str;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str.trim();
    }
}
