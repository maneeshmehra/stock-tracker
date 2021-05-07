package io.quarkus.stocks;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)

public class StockService {

	   @Transactional(SUPPORTS)
	    public List<Stock> findAllStocks() {
	        return Stock.listAll();
	    }

	    @Transactional(SUPPORTS)
	    public Stock findStockById(Long id) {
	        return Stock.findById(id);
	    }

	    @Transactional(SUPPORTS)
	    public Stock findRandomStock() {
	        Stock randomStock = null;
	        while (randomStock == null) {
	            randomStock = Stock.findRandom();
	        }
	        return randomStock;
	    }
	    
	    public Stock persistStock(@Valid Stock stock) {
	        Stock.persist(stock);
	        return stock;
	    }

	    public Stock updateStock(@Valid Stock stock) {
	        Stock entity = Stock.findById(stock.id);
	        entity.symbol = stock.symbol;
	        entity.quantity = stock.quantity;
	        entity.price = stock.price;
	        return entity;
	    }

	    public void deleteStock(Long id) {
	        Stock stock = Stock.findById(id);
	        stock.delete();
	    }
}
