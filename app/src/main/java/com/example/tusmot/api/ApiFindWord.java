package com.example.tusmot.api;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tusmot.GameTusmot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;

public class ApiFindWord extends AsyncTask<String, Void, String>{

    private String searchType;

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        searchType = params[0];

        try{
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                if ("dayWord".equals(searchType)) {
                    url = new URL("https://trouve-mot.fr/api/daily");
                } else {
                    url = new URL("https://trouve-mot.fr/api/sizemax/8");
                }

                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1){
                    result += (char) data;
                    data = isw.read();
                }

                return result;
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String wordJsonString) {
        super.onPostExecute(wordJsonString);
        if (wordJsonString != null) {
            try {
                if ("randomWord".equals(searchType)) {
                    JSONArray jsonArray = new JSONArray(wordJsonString);
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String originalName = jsonObject.getString("name");
                        String normalizedName = normalizeString(originalName);
                        GameTusmot.setWord(normalizedName);
                    }
                } else if ("dayWord".equals(searchType)) {
                    JSONObject jsonObject = new JSONObject(wordJsonString);
                    String originalName = jsonObject.getString("name");
                    String normalizedName = normalizeString(originalName);
                    GameTusmot.setWord(normalizedName);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        } else {
            Log.e(TAG, "No data received from API");
        }
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase();
    }
}
