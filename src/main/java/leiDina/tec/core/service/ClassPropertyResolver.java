package main.java.leiDina.tec.core.service;

import java.util.Properties;
import main.java.leiDina.tec.core.model.ClassSystemProperty;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
@Deprecated
public class ClassPropertyResolver implements PropertyResolver<ClassSystemProperty> {

    @Override
    public ClassSystemProperty resolve(String key, String values) {
        ClassSystemProperty classSystemProperties = new ClassSystemProperty(key);
        try {
            for (String classes : StringUtils.replaceNewLineAndSplitComma(values)) {
                if (StringUtils.isNotEmpty(classes)) {
                    Class<?> aClass = Class.forName(StringUtils.removeSpaces(classes));
                    classSystemProperties.addProperty(aClass);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classSystemProperties;
    }
}
