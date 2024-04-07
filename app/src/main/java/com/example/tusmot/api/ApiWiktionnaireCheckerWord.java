package com.example.tusmot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiWiktionnaireCheckerWord {

    public static boolean motExiste(String mot) {
        String url = "https://fr.wiktionary.org/wiki/" + mot;

        try {
            URL obj = new URL(url);

            // Ouverture de la connexion HTTP
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configuration de la requête
            con.setRequestMethod("GET");

            // Récupération de la réponse
            int responseCode = con.getResponseCode();

            // Vérification de la réponse
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lecture de la réponse
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Vérification si le mot est présent dans la réponse
                String pageContent = response.toString();
                return pageContent.contains("Le mot « " + mot + " » n’existe pas dans le dictionnaire.");
            } else {
                // En cas d'erreur de requête
                System.out.println("Erreur lors de la requête HTTP : " + responseCode);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}