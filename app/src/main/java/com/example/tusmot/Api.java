package com.example.tusmot;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;

public class Api extends AsyncTask<Void, Void, String>{

    private TextView textView;

    public Api(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = "";

        try{
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL("https://trouve-mot.fr/api/sizemax/9");

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
                JSONArray jsonArray = new JSONArray(wordJsonString);

                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String originalName = jsonObject.getString("name");
                    String normalizedName = normalizeString(originalName);

                    textView.setText(normalizedName);
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
