package com.sem.loomoon;

import android.os.AsyncTask;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class LoginTask extends AsyncTask<Void, Void, String> {

    static List<HttpCookie> cookies;

    @Override
    protected String doInBackground(Void... params) {

        String XMLogin="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<root><Function>Login</Function>" +
                "<RequestType>XML</RequestType>" +
                "<ResponseType>XML</ResponseType>" +
                "<Data>" +
                "<login>"+MainActivity.logpass.getLogin()+"</login>" +
                "<password>"+MainActivity.logpass.getPass()+"</password>" +
                "</Data></root>";

        String requestString = "http://testme.dev.amict.ru/datapoint.php";

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

    try {
        URL url = new URL(requestString);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) connection;

        httpConnection.setRequestProperty("Content-Type", "XML");
        httpConnection.setRequestProperty("Host", "testme.dev.amict.ru");
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);

        httpConnection.connect();

        OutputStream outputStream = httpConnection.getOutputStream();
        outputStream.write(XMLogin.getBytes());
        outputStream.flush();
        outputStream.close();

        InputStream inputStream = httpConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");

        CookieStore cookieStore = cookieManager.getCookieStore();
        cookies = cookieStore.getCookies();

    } catch (Exception e) {
        e.printStackTrace();
    }
        return cookies.toString();
    }

    @Override
    protected void onPostExecute(String cookies) {
        MainActivity.logpass.setCookie(cookies);
    }
}
