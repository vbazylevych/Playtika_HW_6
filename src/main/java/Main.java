import java.io.*;
import java.nio.file.*;
import static org.apache.commons.io.IOUtils.*;


public class Main {

    public static void main(String[] args) throws IOException {


        Path directory = Paths.get("testfiles");

        if (Files.exists(directory) && Files.isDirectory(directory)) {

            StringBuilder stringBuilder = new StringBuilder();
            String oneLineText = "";
            int generalSum = 0;

            DirectoryStream<Path> files = Files.newDirectoryStream(directory);

            for (Path file : files) {

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file.toString())))) {
                    String line;

                    while ((line = reader.readLine()) != null) {

                        oneLineText = stringBuilder.append(line).append(" ").toString();
                    }

                    TextUtils.printFileAtributes(file);

                    int oneFileSum = TextUtils.getOneFileSum(oneLineText);
                    generalSum = generalSum + oneFileSum;
                    System.out.println("Sum of frequency for file " + file.getFileName() + " -- " + oneFileSum + "\n");

                    stringBuilder = new StringBuilder();

                } catch (IOException e) {
                    System.out.println("I cant find such directory or can't read from it");
                }

            }
            System.out.println("Sum of frequency for ALL files:  " + generalSum + "\n");
        } else {
            System.out.println("It isn't directory");
        }


        try {
            InputStream input = new FileInputStream("input.txt");
            OutputStream output = new FileOutputStream("output.txt");
            copy(input, output, 1024);
            input.close();
            output.close();
            System.out.println("FILE WAS COPIED!");
        } catch (FileNotFoundException e) {
            System.out.println("Cant find file " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Can't copy");
        }

    }


}