package com.playtika.hw2;

import com.google.common.collect.Sets;

import java.util.HashSet;

public class TextUtils {

    public static String[] getArrayOfSplitedWords(String string) {
        return string.split(" ");
    }

   public static String clearText(String dirtyText) {
       return dirtyText.toLowerCase().replaceAll("[^A-Za-z]", " ");
    }

   public static  String[] getArrayOfUniqueWords(String string) {
        String[] arrayOfSptiledStrings = getArrayOfSplitedWords(string);

        HashSet<String> setOfStrings = Sets.newHashSet(arrayOfSptiledStrings);
        setOfStrings.remove("");

       return setOfStrings.toArray(new String[setOfStrings.size()]);
    }
}


