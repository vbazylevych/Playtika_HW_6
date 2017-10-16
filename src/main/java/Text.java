import java.util.*;


public class Text {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public String[] getTopWords(int n) {

        String[] uniqueWords = TextUtils.getUniqueWords(TextUtils.clearText(text));

        Arrays.sort(uniqueWords);

        String[] strings = {};

        if (uniqueWords.length < n || n == 0 || n < 0) {
            System.out.println("Incorrect N: array has only " + uniqueWords.length + " elements");
            return strings;
        } else {
            strings = Arrays.copyOfRange(uniqueWords, 0, n);
        }
        return strings;
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
