import com.google.common.collect.Sets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class TextUtils {

    public static String[] getArrayOfSplitedWords(String string) {
        return string.split(" ");
    }

   public static String clearText(String dirtyText) {
       return dirtyText.toLowerCase().replaceAll("[^A-Za-z]", " ");
    }

   public static  String[] getUniqueWords(String string) {
        String[] words = getArrayOfSplitedWords(string);

        Set<String> setOfwords = Sets.newHashSet(words);
        setOfwords.remove("");

       return setOfwords.toArray(new String[setOfwords.size()]);
    }

    public static int getOneFileSum(String oneLineText) {
        Text text = new Text(oneLineText);
        Map<String, Integer> map = text.getWordFrequencies();
        int oneFileSum = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            oneFileSum = oneFileSum + entry.getValue();
        }

        return oneFileSum;
    }

    public static void printFileAtributes(Path file) throws IOException {
        System.out.println("Current File is " + file.toAbsolutePath());
        System.out.println("Cteation time for current file is " + (Files.getAttribute(file, "creationTime").toString()));
        System.out.println("Size of current file is "+ Files.getAttribute(file, "size"));
    }
}


