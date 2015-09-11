package com.sem.loomoon;

import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UserInfoTask extends AsyncTask<Void, Void, String> {

    static final  String FIRSTNAME = "FirstName";
    static final  String MOBILE_PHONE_NUMBER = "MobilePhoneNumber";
    static final  String BIRTHDAY = "Birthday";
    static final  String TITLE = "Title";
    static final  String DATA = "Data";
    static final  String MARIAGE_STATUS = "MariageStatus";
    static final  String ID = "ID";
    static final  String SEX = "Sex";
    static final  String DATE_REGISTERED = "DateRegistered";
    static final  String DOCUMENT_TYPE = "DocumentType";
    static String str = null;

    @Override
    protected String doInBackground(Void... params) {

        String requestString = "http://testme.dev.amict.ru/datapoint.php";

        String XMLUserInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<root><Function>UserInfo</Function>" +
                "<RequestType>XML</RequestType>" +
                "<ResponseType>XML</ResponseType>" +
                "<Data></Data></root>";

        try {
            URL url = new URL(requestString);
            URLConnection connection = url.openConnection();

            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            httpConnection.setRequestProperty("Content-Type", "XML");
            httpConnection.setRequestProperty("Host", "testme.dev.amict.ru");
            httpConnection.setRequestProperty("Cookie", MainActivity.logpass.getCookie());
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);

            httpConnection.connect();

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(XMLUserInfo.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");

                StringBuffer data = new StringBuffer();
                int ch;
                while ((ch = isr.read()) != -1) {
                    data.append((char) ch);
                }
                str = null;
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                XmlPullParser parser = factory.newPullParser();

                parser.setInput(new StringReader(data.toString()));
                int eventType = parser.getEventType();
                DataUser dataUser = null;
                boolean done = false;
                while (eventType != XmlPullParser.END_DOCUMENT && !done) {
                    String name = null;
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            if (name.equalsIgnoreCase(DATA)){
                                dataUser = new DataUser();
                            } else if (dataUser != null){
                                if (name.equalsIgnoreCase(FIRSTNAME)){
                                    dataUser.setFirstName(parser.nextText());
                                } else if (name.equalsIgnoreCase(MOBILE_PHONE_NUMBER)){
                                    dataUser.setMobilePhoneNumber(parser.nextText());
                                } else if (name.equalsIgnoreCase(BIRTHDAY)){
                                    dataUser.setBirthday(parser.nextText());
                                } else if (name.equalsIgnoreCase(TITLE)){
                                    dataUser.setTitle(parser.nextText());
                                } else if (name.equalsIgnoreCase(ID)){
                                    dataUser.setID(parser.nextText());
                                } else if (name.equalsIgnoreCase(MARIAGE_STATUS)){
                                    dataUser.setMariageStatus(parser.nextText());
                                }else if (name.equalsIgnoreCase(SEX)){
                                    dataUser.setSex(parser.nextText());
                                }else if (name.equalsIgnoreCase(DATE_REGISTERED)){
                                    dataUser.setDateRegistered(parser.nextText());
                                }else if (name.equalsIgnoreCase(DOCUMENT_TYPE)){
                                    dataUser.setDocumentType(parser.nextText());
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            name = parser.getName();
                            if (name.equalsIgnoreCase("DocumentData")){
                                done = true;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
                str = dataUser.toString();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(str == null) str = "1";
        return str;
    }

        @Override
    protected void onPostExecute(String str) {
            MainActivity.logpass.setStr(str);
    }
}

