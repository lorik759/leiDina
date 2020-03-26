package main.java.leiDina.tec.core.io;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class TextFileEntityDigester {

    public Map<String, String> digestLine(String line) {
        Map<String, String> propertiesMap = new HashMap<>();
        String[] propertiesKeysAndValues = StringUtils.split(line, ":");
        for (String property : propertiesKeysAndValues) {
            String[] keyAndValue = StringUtils.split(property, ":");
            propertiesMap.put(keyAndValue[0], keyAndValue[0]);
        }
        return propertiesMap;
    }
}
