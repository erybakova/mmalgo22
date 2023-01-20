public class Sec {
    private final float _openingPrice; // цена открытия ценной бумаги
    private float _closingPrice;       // цена закрытия ценной бумаги
    private long _totalVolume;         // общее кол-во ценных бумаг
    private float _totalValue;         // общая сумма сделок по ценной бумаге

    Sec() {
        this._openingPrice = 0;
        this._closingPrice = 0;
        this._totalVolume = 0;
        this._totalValue = 0;
    }
    public Sec(Trade a) {
        this._openingPrice = a.getPrice();
        this._closingPrice = a.getPrice();
        this._totalVolume = a.getVolume();
        this._totalValue = a.getValue();
    }
    public Sec merge(Sec b) {
        this._closingPrice = b._closingPrice;
        this._totalVolume += b._totalVolume;
        this._totalValue += b._totalValue;
        return this;
    }
    public float getOpeningPrice() {
        return _openingPrice;
    }
    public float getClosingPrice() {
        return _closingPrice;
    }
    public long getTotalVolume() {
        return _totalVolume;
    }
    public float getTotalValue() {
        return _totalValue;
    }
    public float changePrice() {
        return _closingPrice - _openingPrice;
    }
    public float changePricePercent() {
        return 100 * (_closingPrice / _openingPrice - 1);
    }

    public void printInfo() {
        String st = this.changePrice() > 0 ? "рост" : "падение";
        System.out.println(" (" + st + " цены на " + Math.abs(this.changePrice()) + " руб., или " +
                           Math.abs(this.changePricePercent()) + "%; общее количество акций: " + this.getTotalVolume() +
                           "; общая сумма сделок по акции: " + this.getTotalValue() + " руб.)");
    }
}