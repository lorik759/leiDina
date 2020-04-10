package main.java.leiDina.tec.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * A utility class that has several methods for resource manipulation.
 *
 * @author vitor.alves
 */
public class ResourceUtils {

    /**
     * Gets a {@link Properties} through the {@link URL} of an .xml file.
     *
     * @param url the {@link URL} of an .xml file.
     * @return a {@link Properties} of the .xml file.
     * @throws IOException an {@link IOException}.
     */
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
