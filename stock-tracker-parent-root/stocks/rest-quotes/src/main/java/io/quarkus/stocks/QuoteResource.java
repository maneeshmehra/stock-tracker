package io.quarkus.stocks;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

@Path("/api/quotes")
@Produces(APPLICATION_JSON)
public class QuoteResource {
	
    private static final Logger LOGGER = Logger.getLogger(QuoteResource.class);
	
    @Inject
    QuoteService service;
	
    @GET
    @Path("/{symbol}")
    public Response getQuote(
        @PathParam("symbol") String symbol) {
        Quote quote = service.getQuoteBySymbol(symbol);
        if (quote != null) {
            LOGGER.debug("Found stock " + quote);
            return Response.ok(quote).build();
        } else {
            LOGGER.debug("No stock found with symbol " + symbol);
            return Response.noContent().build();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}