import java.io.*;
import java.util.Arrays;

public class TradesAnalyzator {
    public static void main(String[] args) {
        TradesAnalyzator t = new TradesAnalyzator();

        try (
            var reader = new FileReader("data/trades.txt");
            var bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                var parts = line.split("\t");

                System.out.println(Arrays.toString(parts));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}