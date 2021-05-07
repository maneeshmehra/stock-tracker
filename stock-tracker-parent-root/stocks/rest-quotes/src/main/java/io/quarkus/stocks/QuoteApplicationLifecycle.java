package io.quarkus.stocks;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class QuoteApplicationLifecycle {

	private static final Logger LOGGER = Logger.getLogger(QuoteApplicationLifecycle.class);

	void onStart(@Observes StartupEvent ev) {
		LOGGER.info("QUOTE API - Powered by Quarkus");
		LOGGER.infof("The application QUOTE API is starting with profile `%s`", ProfileManager.getActiveProfile());
	}

	void onStop(@Observes ShutdownEvent ev) {
		LOGGER.info("The application QUOTE API is stopping...");
	}
}
