package io.quarkus.stocks;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@ApplicationScoped
public class QuoteService {
	
	public Quote getQuoteBySymbol(String symbol) {
		Quote quote = new Quote(symbol, 0.00);
		
		try {
			Stock stock = YahooFinance.get(symbol);
			StockQuote stockQuote = stock.getQuote();
			quote.setPrice(stockQuote.getPrice().doubleValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return quote;
	}

}
