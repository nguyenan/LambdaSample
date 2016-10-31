package ag.tend.awslambda.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by annguyen on 31/10/16.
 */
public class ConfigReader {
    InputStream inputStream;
    public static Properties prop = new Properties();

    public static ConfigReader getInstance() {
        ConfigReader configReader = new ConfigReader();
        configReader.loadConfig();
        return configReader;
    }

    public void loadConfig() {
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            if (inputStream != null) {
                prop.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropValues(String key) {
        return prop.getProperty(key);
    }
}
