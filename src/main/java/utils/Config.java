package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find application.properties");
            } else {
                // Load the properties file from the resources folder
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Consider using proper logging here in a real application
        }
    }

    // Method to get the value of a property by key
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
