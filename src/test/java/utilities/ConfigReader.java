package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    private ConfigReader(){}

    static {
        String path = "configuration.properties";
        try {
            //opening the configuration.properties file
            FileInputStream file = new FileInputStream(path);
            //load and read the file
            properties = new Properties();
            properties.load(file);
            //close the file
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
