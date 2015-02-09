package search;

import java.io.FileInputStream;
import java.util.Properties;

public enum Config {
    INSTANCE;

    public static final String ALGORITHM        = "algorithm";
    public static final String SOURCE_CITY      = "source.city";
    public static final String DESTINATION_CITY = "destination.city";
    public static final String CONFIG_FILE      = "config/app.properties";
    private Properties                 properties;

    private Config() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (Exception e) {
            throw new RuntimeException("Unable to find  property file", e);
        }
    }
    
    public Algorithms getAlgorithm() {
        return Algorithms.valueOf(properties.getProperty(ALGORITHM).toUpperCase());
    }
    
    public Cities getDestinationCity() {
        return Cities.nameToCity.get(properties.getProperty(DESTINATION_CITY).toUpperCase());
    }
    
    public Cities getSourceCity() {
        return Cities.nameToCity.get(properties.getProperty(SOURCE_CITY).toUpperCase());
    }
}
