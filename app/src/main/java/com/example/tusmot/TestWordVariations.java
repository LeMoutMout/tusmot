package com.example.tusmot;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.tusmot.api.ApiWiktionnaireCheckerWord;

import java.util.ArrayList;
import java.util.List;

public class TestWordVariations {

    public static boolean testVariations(String word) {
        List<String> variations = generateVariations(word);
        for (String variation : variations) {
            if (testWord(variation)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> generateVariations(String word) {

        List<String> variations = new ArrayList<>();
        variations.add(word);

        for (char c : word.toCharArray()) {
            String baseChar = String.valueOf(c);
            String[] specialChars = getSpecialCharacters(baseChar);
            for (String specialChar : specialChars) {
                String variation = word.replace(baseChar, specialChar);
                variations.add(variation);
            }
        }

        return variations;
    }

    private static boolean testWord(String word){
        try {
            boolean exist;
            ApiWiktionnaireCheckerWord apiWiktionnaireCheckerWord = new ApiWiktionnaireCheckerWord();
            exist = apiWiktionnaireCheckerWord.execute(word).get();
            return exist;
        } catch (Exception e) {
            Log.e(TAG, "Une erreur s'est produite : ", e);
            e.printStackTrace();
        }
        return false;
    }

    private static String[] getSpecialCharacters(String baseChar) {
        switch (baseChar) {
            case "e":
                return new String[]{"é", "ê", "è", "ë"};
            case "a":
                return new String[]{"â"};
            case "i":
                return new String[]{"î"};
            case "u":
                return new String[]{"ù"};
            default:
                return new String[0];
        }
    }
}
