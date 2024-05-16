package com.example.animal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static String getRequest(String urlStr) throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setRequestMethod("GET");

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + urlConnection.getResponseCode());
            }

            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String readLine;
            while ((readLine = br.readLine()) != null) {
                result.append(readLine + "\n\r");
            }
            br.close();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result.toString();
    }
}
