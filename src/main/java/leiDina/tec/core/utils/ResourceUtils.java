package main.java.leiDina.tec.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * @author vitor.alves
 */
public class ResourceUtils {

    public static Properties getXMLPropertiesFromURL(URL url) throws IOException {
        URLConnection urlConnection = null;
        Properties properties = new Properties();
        urlConnection = url.openConnection();
        try (InputStream inputStream = urlConnection.getInputStream()) {
            properties.loadFromXML(inputStream);
            return properties;
        }
    }
}