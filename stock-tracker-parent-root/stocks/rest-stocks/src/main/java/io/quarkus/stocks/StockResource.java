package io.quarkus.stocks;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

@Path("/api/stocks")
@Produces(APPLICATION_JSON)
public class StockResource {
	
    private static final Logger LOGGER = Logger.getLogger(StockResource.class);

    @Inject
    StockService service;

    @GET
    @Path("/random")
    public Response getRandomStock() {
        Stock stock = service.findRandomStock();
        LOGGER.debug("Found random stock " + stock);
        return Response.ok(stock).build();
    }

    @GET
    public Response getAllStocks() {
        List<Stock> stocks = service.findAllStocks();
        LOGGER.debug("Total number of stocks " + stocks);
        return Response.ok(stocks).build();
    }

    @GET
    @Path("/{id}")
    public Response getStock(
        @PathParam("id") Long id) {
        Stock stock = service.findStockById(id);
        if (stock != null) {
            LOGGER.debug("Found stock " + stock);
            return Response.ok(stock).build();
        } else {
            LOGGER.debug("No stock found with id " + id);
            return Response.noContent().build();
        }
    }

    @POST
    public Response createStock(
        @Valid Stock stock, @Context UriInfo uriInfo) {
        stock = service.persistStock(stock);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(stock.id));
        LOGGER.debug("New stock created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @PUT
    public Response updateStock(
        @Valid Stock stock) {
        stock = service.updateStock(stock);
        LOGGER.debug("Stock updated with new valued " + stock);
        return Response.ok(stock).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStock(
        @PathParam("id") Long id) {
        service.deleteStock(id);
        LOGGER.debug("Stock deleted with " + id);
        return Response.noContent().build();
    }

    @GET
    @Produces(TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "Hello RESTEasy";
    }
}