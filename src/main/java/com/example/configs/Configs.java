package com.example.configs;

import java.io.IOException;
import java.util.Properties;

public record Configs() {
	private static final Properties properties = new Properties();

	public static void loadProperties() throws IOException {
		properties.load(Configs.class.getResourceAsStream("/com/example/application.properties"));
	}

	public static String getProperty(ConfigKeys key) {
		return properties.getProperty(key.getValue());
	}

	public enum ConfigKeys {
		NAME("name"),
		LOGO("logo"),
		VERSION("version");

		private final String value;

		ConfigKeys(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}
