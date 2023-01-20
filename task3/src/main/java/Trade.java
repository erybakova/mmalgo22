public record Trade(
    long tradeNo,     // номер сделки
    long tradeTime,   // время сделки
    String secBoard,  // площадка
    String secCode,   // код ценной бумаги
    float price,      // цена
    long volume,      // кол-во бумаг
    float accruedInt, // НКД (для облигаций)
    float yield,      // доходность (для облигаций)
    float value      // сумма сделки
) {
    Trade(String[] parts) {
        this(
                Long.parseLong(parts[0]),
                Long.parseLong(parts[1]),
                parts[2],
                parts[3],
                Float.parseFloat(parts[4]),
                Long.parseLong(parts[5]),
                Float.parseFloat(parts[6]),
                Float.parseFloat(parts[7]),
                Float.parseFloat(parts[8])
        );
    }
}