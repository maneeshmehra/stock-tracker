package io.quarkus.stocks;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Stock extends PanacheEntityBase {
	
	@NotNull
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;
    @NotNull
    @Size(min = 3, max = 50)
    public String symbol;
    @NotNull
    @Min(1)
    public int quantity;
    @NotNull
    @Min(0)
    public double price;

    @Override
    public String toString() {
        return "Stock{" +
            "id=" + id +
            ", symbol='" + symbol + '\'' +
            ", quantity='" + quantity + '\'' +
            ", price=" + price +
            '}';
    }

    public static Stock findRandom() {
        long countStocks = Stock.count();
        Random random = new Random();
        int randomStock = random.nextInt((int) countStocks);
        return Stock.findAll().page(randomStock, 1).firstResult();
    }
}
