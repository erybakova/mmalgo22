import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TradesAnalyser {
    private static void _processImpl(List<Trade> trades, String secBoard) {
        Map<String, Sec> mapSecCodeTradesSecBoard = trades.stream()
                .filter(trade -> trade.secBoard().equals(secBoard))
                .collect(Collectors.toMap(Trade::secCode, Sec::new, Sec::merge));

        List<Map.Entry<String, Sec>> listSortedSecCodeTrades =
                mapSecCodeTradesSecBoard.entrySet().stream()
                .sorted((a, b) -> Float.compare(a.getValue().changePrice(), b.getValue().changePrice()))
                .toList();

        System.out.println("\n10 самых неудачливых акций дня, торгуемых на площадке " + secBoard + ":");
        listSortedSecCodeTrades
                .stream()
                .limit(10)
                .forEach(l -> {
                    System.out.print(l.getKey());
                    l.getValue().printInfo();
                });

        System.out.println("\n10 самых удачливых акций дня, торгуемых на площадке " + secBoard + ":");
        listSortedSecCodeTrades
                .stream()
                .skip(Math.max(0, listSortedSecCodeTrades.size() - 10))
                .forEach(l -> {
                    System.out.print(l.getKey());
                    l.getValue().printInfo();
                });
    }

    public static void process(List<Trade> trades) {
        _processImpl(trades, "TQBR");
        _processImpl(trades, "FQBR");
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