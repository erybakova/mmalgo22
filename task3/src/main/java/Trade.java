public class Trade {
    // 003422595396	090217	SPEQ	GAZP	214.2500000000	6000	0.00	0.00	1285500.00
    private long _tradeNo;     // номер сделки
    private long _tradeTime;   // время сделки
    private String _secBoard;  // площадка
    private String _secCode;   // код ценной бумаги
    private float _price;      // цена
    private long _volume;      // кол-во бумаг
    private float _accruedInt; // НКД (для облигаций)
    private float _yield;      // доходность (для облигаций)
    private float _value;      // сумма сделки

    Trade(String[] parts) {
        this._tradeNo = Long.parseLong(parts[0]);
        this._tradeTime = Long.parseLong(parts[1]);
        this._secBoard = parts[2];
        this._secCode = parts[3];
        this._price = Float.parseFloat(parts[4]);
        this._volume = Long.parseLong(parts[5]);
        this._accruedInt = Float.parseFloat(parts[6]);
        this._yield = Float.parseFloat(parts[7]);
        this._value = Float.parseFloat(parts[8]);
    }

    public long getTradeNo() {
        return _tradeNo;
    }

    public long getTradeTime() {
        return _tradeTime;
    }

    public String getSecBoard() {
        return _secBoard;
    }

    public String getSecCode() {
        return _secCode;
    }

    public float getPrice() {
        return _price;
    }

    public long getVolume() {
        return _volume;
    }

    public float getAccruedInt() {
        return _accruedInt;
    }

    public float getYield() {
        return _yield;
    }

    public float getValue() {
        return _value;
    }
}