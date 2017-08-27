package com.manifestsoftwarelab.demoapp.Helper;



import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by s5 on 17/12/15.
 */
public class ServerDataManager {
    private static ServerDataManager instance = null;
    private static OkHttpClient client;

    public static ServerDataManager getInstant() {
        if (instance == null) {
            try {
                instance = new ServerDataManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private ServerDataManager() throws Exception {
        client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(30, TimeUnit.SECONDS);    // socket timeout
    }
    public String getPostData(ArrayList<NameValuePair> keyValue, String URL, String Header) throws Exception {
        try {
            FormEncodingBuilder b = new FormEncodingBuilder();
            for (NameValuePair nameValuePair : keyValue) {
                if (nameValuePair.getValue() != null && !"".equalsIgnoreCase(nameValuePair.getValue())) {
                    b.add(nameValuePair.getName(), nameValuePair.getValue());
                }
            }
            Request request = new Request.Builder().url(URL).post(b.build()).addHeader("authorization",Header).build();
            System.out.println("request url : "+ request);
            Response response = client.newCall(request).execute();
            InputStreamReader in = new InputStreamReader(response.body().byteStream());
            int statusCode = response.code();
            switch (statusCode) {
                case 400:
                    in.read();
                    in.close();
                    throw new Exception("NoHttpResponseException");
                case 404:
                    in.read();
                    in.close();
                    throw new Exception("PageNotFoundException");
                case 503:
                    in.read();
                    in.close();
                    //				in = new InputStreamReader(response.cacheResponse().body().byteStream());
                    throw new Exception("ServerNotAvailableException");
                default:
                    //				System.out.println("Failed To Load Data");
                    break;
            }
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            in.close();
            return builder.toString();
        } catch(ConnectException e) {
            e.printStackTrace();

        } catch (Exception e) {
            //          e.printStackTrace();
            throw e;
        }
        return null;
    }

    public String getPostData(ArrayList<NameValuePair> keyValue, String URL)throws Exception {
        try {
            FormEncodingBuilder b = new FormEncodingBuilder();
            for (NameValuePair nameValuePair : keyValue) {
                if (nameValuePair.getValue() != null && !"".equalsIgnoreCase(nameValuePair.getValue())) {
                    b.add(nameValuePair.getName(), nameValuePair.getValue());
                }
            }

            Request request = new Request.Builder().url(URL).post(b.build()).build();
            //request.
            Response response = client.newCall(request).execute();
            InputStreamReader in = new InputStreamReader(response.body().byteStream());
            int statusCode = response.code();
            switch (statusCode) {
                case 400:
                    in.read();
                    in.close();
                    throw new Exception("NoHttpResponseException");
                case 404:
                    in.read();
                    in.close();
                    throw new Exception("PageNotFoundException");
                case 503:
                    in.read();
                    in.close();
                    //				in = new InputStreamReader(response.cacheResponse().body().byteStream());
                    throw new Exception("ServerNotAvailableException");
                default:
                    //				System.out.println("Failed To Load Data");
                    break;
            }
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            in.close();
            return builder.toString();
        } catch(ConnectException e) {
            e.printStackTrace();
           throw new NoHttpResponseException("NoHttpResponseException");
        } catch (Exception e) {
  //          e.printStackTrace();
            throw e;
        }

    }
    public String getGetData(ArrayList<NameValuePair> keyValue, String URL)throws Exception {
        try {
            FormEncodingBuilder b = new FormEncodingBuilder();
            for (NameValuePair nameValuePair : keyValue) {
                if (nameValuePair.getValue() != null && !"".equalsIgnoreCase(nameValuePair.getValue())) {
                    b.add(nameValuePair.getName(), nameValuePair.getValue());
                }
            }

            // Request request = new Request.Builder().url(URL).post(b.build()).build();

            Request request = new Request.Builder().url(URL).get().build();
            //request.
            Response response = client.newCall(request).execute();
            InputStreamReader in = new InputStreamReader(response.body().byteStream());
            int statusCode = response.code();
            switch (statusCode) {
                case 400:
                    in.read();
                    in.close();
                    throw new Exception("NoHttpResponseException");
                case 404:
                    in.read();
                    in.close();
                    throw new Exception("PageNotFoundException");
                case 503:
                    in.read();
                    in.close();
                    //				in = new InputStreamReader(response.cacheResponse().body().byteStream());
                    throw new Exception("ServerNotAvailableException");
                default:
                    //				System.out.println("Failed To Load Data");
                    break;
            }
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            in.close();
            return builder.toString();
        } catch(ConnectException e) {
            e.printStackTrace();
            throw new NoHttpResponseException("NoHttpResponseException");
        } catch (Exception e) {
            //          e.printStackTrace();
            throw e;
        }
        /*try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));
            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();

            switch (statusCode) {
                case 400:
                    throw new Exception("NoHttpResponseException");
                case 404:
                    throw new Exception("PageNotFoundException");
                case 503:
                    throw new Exception("ServerNotAvailableException");
                default:
                    //				System.out.println("Failed To Load Data");
                    break;
            }

            String responseBody = EntityUtils.toString(response.getEntity());
            return responseBody;
        } catch(ConnectException e) {
            e.printStackTrace();
            throw new NoHttpResponseException("NoHttpResponseException");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }*/
    }
    public String getPostImageData(RequestBody requestBody, String URL)throws Exception {
        try {

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
            SimpleDateFormat date = new SimpleDateFormat("Z", Locale.getDefault());
            String localTime = "GMT" + date.format(calendar.getTime());
            //b.add("tmzone", localTime);
            System.setProperty("http.keepAlive", "false");

            Request request = new Request.Builder().url(URL).post(requestBody).build();
            Response response = client.newCall(request).execute();
            InputStreamReader in = new InputStreamReader(response.body().byteStream());
            int statusCode = response.code();
            switch (statusCode) {
                case 400:
                    in.read();
                    in.close();
                    throw new Exception("NoHttpResponseException");
                case 404:
                    in.read();
                    in.close();
                    throw new Exception("PageNotFoundException");
                case 503:
                    in.read();
                    in.close();
                    //				in = new InputStreamReader(response.cacheResponse().body().byteStream());
                    throw new Exception("ServerNotAvailableException");
                default:
                    //				System.out.println("Failed To Load Data");
                    break;
            }
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            in.close();
            return builder.toString();
        } catch(ConnectException e) {
            e.printStackTrace();
            throw new NoHttpResponseException("NoHttpResponseException");
        } catch (Exception e) {
            //          e.printStackTrace();
            throw e;
        }

    }
    public String getPostImageData(RequestBody requestBody, String URL, ArrayList<NameValuePair> keyValue)throws Exception {
        try {
            FormEncodingBuilder b = new FormEncodingBuilder();
            for (NameValuePair nameValuePair : keyValue) {
                if (nameValuePair.getValue() != null && !"".equalsIgnoreCase(nameValuePair.getValue())) {
                    b.add(nameValuePair.getName(), nameValuePair.getValue());
                }
            }

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
            SimpleDateFormat date = new SimpleDateFormat("Z", Locale.getDefault());
            String localTime = "GMT" + date.format(calendar.getTime());
            //b.add("tmzone", localTime);
            System.setProperty("http.keepAlive", "false");

            Request request = new Request.Builder().url(URL).post(requestBody).build();
            Response response = client.newCall(request).execute();
            InputStreamReader in = new InputStreamReader(response.body().byteStream());
            int statusCode = response.code();
            switch (statusCode) {
                case 400:
                    in.read();
                    in.close();
                    throw new Exception("NoHttpResponseException");
                case 404:
                    in.read();
                    in.close();
                    throw new Exception("PageNotFoundException");
                case 503:
                    in.read();
                    in.close();
                    //				in = new InputStreamReader(response.cacheResponse().body().byteStream());
                    throw new Exception("ServerNotAvailableException");
                default:
                    //				System.out.println("Failed To Load Data");
                    break;
            }
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            in.close();
            return builder.toString();
        } catch(ConnectException e) {
            e.printStackTrace();
            throw new NoHttpResponseException("NoHttpResponseException");
        } catch (Exception e) {
            //          e.printStackTrace();
            throw e;
        }

    }
}
