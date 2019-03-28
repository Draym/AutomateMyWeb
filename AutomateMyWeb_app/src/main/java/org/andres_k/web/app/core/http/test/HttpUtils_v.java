package org.andres_k.web.app.core.http.test;

import org.andres_k.web.app.core.http.HttpResponse;
import org.andres_k.web.app.utils.data.Configs;
import org.andres_k.web.app.utils.tools.Console;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils_v {


    /**
     * GET
     **/
    public static HttpResponse GET(String api, Map<String, String> params) throws IOException {
        HttpURLConnection con = createCon(api);
        con.setRequestMethod("GET");

        addUrlParams(con, params);
        HttpResponse response = getResponse(con);

        con.disconnect();
        return response;
    }

    /**
     * POST
     **/
    public static HttpResponse POST(String api, Object data) throws IOException {
        HttpURLConnection con = createCon(api);
        con.setRequestMethod("POST");

        addBodyParams(con, data);
        HttpResponse response = getResponse(con);

        con.disconnect();
        return response;
    }


    /**
     * TOOLS
     **/
    private static HttpURLConnection createCon(String api) throws IOException {
        URL url = new URL(Configs.url + api);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setDoOutput(true);

        return con;
    }

    private static void addUrlParams(HttpURLConnection con, Map<String, String> params) throws IOException {
        if (params.size() == 0)
            return;
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(createParamsString(params));
        out.flush();
        out.close();
    }

    private static void addBodyParams(HttpURLConnection con, Object data) {

    }

    private static HttpResponse getResponse(HttpURLConnection con) throws IOException {
        HttpResponse response = new HttpResponse();

        int status = con.getResponseCode();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        Console.log("content: " + content);
        Console.log("response: " + con.getResponseMessage());

        in.close();
        return response;
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
