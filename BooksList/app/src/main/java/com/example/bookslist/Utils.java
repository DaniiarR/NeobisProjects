package com.example.bookslist;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Utils {

    public static final String LOG_TAG = Utils.class.getName();

    public static ArrayList<Book> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = makeHttpRequest(url);

        return extractBooksFromJson(jsonResponse);
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            System.out.println("Error creating URL");
        }
        return url;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {

                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error retrieving JSON results");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing the inputStream");
                }
            }
        }
        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            try {
                line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return output.toString();
    }

    public static ArrayList<Book> extractBooksFromJson(String json) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSONArray items = object.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");

                JSONArray authorsJson = volumeInfo.getJSONArray("authors");
                String[] authors = new String[authorsJson.length()];
                for (int p = 0; p < authorsJson.length(); p++) {
                    authors[p] = authorsJson.get(p).toString();
                }

                String publishedDate = volumeInfo.getString("publishedDate");
                String description =  volumeInfo.getString("description");
                int pages = volumeInfo.getInt("pageCount");
                double averageRating = volumeInfo.getDouble("averageRating");
                String previewLink = volumeInfo.getString("previewLink");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String thumbnail = imageLinks.getString("thumbnail");
                books.add(new Book(title, authors, publishedDate, description, pages, thumbnail, averageRating, previewLink));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG,"Could not extract books from JSON");
        }
        return books;
    }
}
