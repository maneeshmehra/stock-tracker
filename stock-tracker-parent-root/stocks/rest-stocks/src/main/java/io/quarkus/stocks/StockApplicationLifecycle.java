package io.quarkus.stocks;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class StockApplicationLifecycle {

	private static final Logger LOGGER = Logger.getLogger(StockApplicationLifecycle.class);

	void onStart(@Observes StartupEvent ev) {
		LOGGER.info("STOCK API - Powered by Quarkus");
		LOGGER.infof("The application STOCK API is starting with profile `%s`", ProfileManager.getActiveProfile());
	}

	void onStop(@Observes ShutdownEvent ev) {
		LOGGER.info("The application STOCK API is stopping...");
	}
}
