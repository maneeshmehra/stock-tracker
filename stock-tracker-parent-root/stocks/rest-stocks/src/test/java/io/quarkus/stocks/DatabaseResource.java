package io.quarkus.stocks;

import java.util.Collections;
import java.util.Map;

import org.testcontainers.containers.MySQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DatabaseResource implements QuarkusTestResourceLifecycleManager {

	private static final MySQLContainer<?> DATABASE = new MySQLContainer<>("mysql:latest")
			.withDatabaseName("stocks_database").withUsername("stocktracker").withPassword("stocktracker")
			.withExposedPorts(3306);

	@Override
	public Map<String, String> start() {
		DATABASE.start();
		return Collections.singletonMap("%test.quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl());
	}

	@Override
	public void stop() {
		DATABASE.stop();
	}
}	