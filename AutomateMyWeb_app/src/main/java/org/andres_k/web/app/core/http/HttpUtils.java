package org.andres_k.web.app.core.http;


import org.andres_k.web.app.utils.data.Configs;
import org.andres_k.web.app.utils.tools.Console;
import org.andres_k.web.app.utils.tools.TJson;
import org.andres_k.web.app.utils.tools.TString;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils {

    /**
     * GET
     **/
    public static HttpResponse GET(String api, Map<String, String> params) throws IOException {
        String url = createUrl(api, params);

        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);


        org.apache.http.HttpResponse response = client.execute(request);

        Console.log("Sending 'GET' request to URL : " + url);
        Console.log("Response Code : " + response.getStatusLine().getStatusCode());

        return createResponse(response.getEntity());
    }

    /**
     * POST
     **/
    public static HttpResponse POST(String api, String data) throws IOException {
        return POST(api, data, null);
    }

    public static HttpResponse POST(String api, String data, Map<String, String> params) throws IOException {
        String url = createUrl(api, params);

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        // Body parameters
        if (data != null) {
            StringEntity requestEntity = new StringEntity(data, ContentType.APPLICATION_JSON);

            httppost.setEntity(requestEntity);
        }

        //Execute and get the response.
        org.apache.http.HttpResponse response = httpclient.execute(httppost);

        Console.log("Sending 'POST' request to URL : " + url);
        Console.log("Response Code : " + response.getStatusLine().getStatusCode());

        return createResponse(response.getEntity());
    }


    /**
     * TOOLS
     **/
    private static String createUrl(String api, Map<String, String> params) throws UnsupportedEncodingException {
        String parameters = "";

        if (params != null && params.size() != 0) {
            parameters += "?" + createParamsString(params);
        }

        if (!TString.getLast(Configs.url).equals("/") && !TString.getFirst(api).equals("/"))
            api = "/" + api;

        return Configs.url + api + parameters;
    }


    private static HttpResponse createResponse(HttpEntity entity) throws IOException {
        StringBuilder result = null;

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                result = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(instream));

                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                Console.log("response: " + result.toString());
            }
        }

        if (result == null)
            throw new NullPointerException("HttpUtils.createResponse : there is no response");

        String response = result.toString().replaceAll("org.andres_k.web.backend", "org.andres_k.web.app.core.http");
        return TJson.toObject(response, HttpResponse.class);
    }

    private static String createParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
