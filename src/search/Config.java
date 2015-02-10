package search;

import java.io.FileInputStream;
import java.util.Properties;

import com.google.common.base.Optional;

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
    
    public Optional<Algorithms> getAlgorithm() {
        Algorithms algorithm = Algorithms.valueOf(properties.getProperty(ALGORITHM).toUpperCase());
        if (algorithm == null) {
            System.out.println("The algorithm specified is not reconginzed, "
                    + "using BFS for the deafule algorithm");
            return Optional.absent();
        } else {
            return Optional.of(algorithm);
        }
    }
    
    public Optional<Cities> getDestinationCity() {
        Cities destination = Cities.nameToCity
                .get(properties.getProperty(DESTINATION_CITY).toUpperCase());
        if (destination == null) {
            System.out.println("The destination city specified is not in the graph, "
                    + "using default destination city: Eforie instead");
            return Optional.absent();
        } else {
            return Optional.of(destination);
        }
    }
    
    public Optional<Cities> getSourceCity() {
        Cities source = Cities.nameToCity.get(properties.getProperty(SOURCE_CITY).toUpperCase());
        if (source == null) {
            System.out.println("The source city specified is not in the graph, "
                    + "using default source city: Oradea instead");
            return Optional.absent();
        } else {
            return Optional.of(source);
        }
    }
}
