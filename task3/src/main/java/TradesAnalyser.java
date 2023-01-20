import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TradesAnalyser {
    public static void process(List<Trade> trades) {
        Map<String, Sec> mapSecCodeTradesTQBR = trades.stream()
                .filter(trade -> trade.getSecBoard().equals("TQBR"))
                .collect(Collectors.toMap(Trade::getSecCode, Sec::new, Sec::merge));
        Map<String, Sec> mapSecCodeTradesFQBR = trades.stream()
                .filter(trade -> trade.getSecBoard().equals("FQBR"))
                .collect(Collectors.toMap(Trade::getSecCode, Sec::new, Sec::merge));

        List<Map.Entry<String, Sec>> listSortedSecCodeTradesTQBR =
                mapSecCodeTradesTQBR.entrySet().stream()
                .sorted((a, b) -> Float.compare(a.getValue().changePrice(), b.getValue().changePrice()))
                .toList();
        List<Map.Entry<String, Sec>> listSortedSecCodeTradesFQBR =
                mapSecCodeTradesFQBR.entrySet().stream()
                .sorted((a, b) -> Float.compare(a.getValue().changePrice(), b.getValue().changePrice()))
                .toList();

        System.out.println("10 самых неудачливых акций дня, торгуемых на площадке TQBR:");
        listSortedSecCodeTradesTQBR
                .stream()
                .limit(10)
                .forEach(l -> {
                    System.out.print(l.getKey());
                    l.getValue().printInfo();
                });

        System.out.println("\n10 самых удачливых акций дня, торгуемых на площадке TQBR:");
        listSortedSecCodeTradesTQBR
                .stream()
                .skip(Math.max(0, listSortedSecCodeTradesTQBR.size() - 10))
                .forEach(l -> {
                    System.out.print(l.getKey());
                    l.getValue().printInfo();
                });

        System.out.println("\n10 самых неудачливых акций дня, торгуемых на площадке FQBR:");
        listSortedSecCodeTradesFQBR
                .stream()
                .limit(10)
                .forEach(l -> {
                    System.out.print(l.getKey());
                    l.getValue().printInfo();
                });

        System.out.println("\n10 самых удачливых акций дня, торгуемых на площадке FQBR:");
        listSortedSecCodeTradesFQBR
                .stream()
                .skip(Math.max(0, listSortedSecCodeTradesFQBR.size() - 10))
                .forEach(l -> {
                    System.out.print(l.getKey());
                    l.getValue().printInfo();
                });
    }

    public static void main(String[] args) {
        List<Trade> trades = new ArrayList<Trade>();
        try (
                var reader = new FileReader("data/trades.txt");
                var bufferedReader = new BufferedReader(reader)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                var parts = line.split("\t");
                trades.add(new Trade(parts));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        process(trades);
    }
}