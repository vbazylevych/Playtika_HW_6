package com.playtika.hw2;

import com.playtika.hw2.TextUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Text {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public String[] getTopWords(int n) {

        String[] arrayOfUniqueWords = TextUtils.getArrayOfUniqueWords(TextUtils.clearText(text));
        Arrays.sort(arrayOfUniqueWords);
        String[] strings = {};

        if (arrayOfUniqueWords.length < n) {
            System.out.println("Incorrect N: array has only " + arrayOfUniqueWords.length + " elements");
            return strings;
        }

        try {
            strings = Arrays.copyOfRange(arrayOfUniqueWords, 0, n);
        } catch (Exception e) {
            System.out.println("Incorrect N: " + e.getMessage());
        } finally {
            return strings;
        }
    }


    public Map<String, Integer> getWordFrequencies() {
        String[] splitedArray = TextUtils.getArrayOfSplitedWords(TextUtils.clearText(text));

        Map<String, Integer> resultMap = new HashMap<String, Integer>();

        for (String word : splitedArray) {
            if (resultMap.containsKey(word)) {
                resultMap.put(word, resultMap.get(word) + 1);
            } else {
                resultMap.put(word, 1);
            }
            resultMap.remove("");
        }
        return resultMap;
    }

    public int getLengthInChars() {
        String[] splitedArray = TextUtils.getArrayOfSplitedWords(TextUtils.clearText(text));
        int sum = 0;

        for (String s : splitedArray) {
            sum = sum + s.length();
        }
        return sum;
    }

}
