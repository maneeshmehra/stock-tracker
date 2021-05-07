package io.quarkus.stocks;

public class Quote {

	private String symbol;
	private double price;
	
	Quote(String symbol, double price) {
		this.symbol = symbol;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
    @Override
    public String toString() {
        return "Quote{" +
            "symbol='" + symbol + '\'' +
            ", price=" + price +
            '}';
    }
}
