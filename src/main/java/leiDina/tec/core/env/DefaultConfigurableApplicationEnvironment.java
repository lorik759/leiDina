package main.java.leiDina.tec.core.env;

import java.util.ArrayList;
import java.util.List;
import main.java.leiDina.tec.core.xml.model.AppDefinitionType;
import main.java.leiDina.tec.core.xml.service.JAXBXmlParser;


/**
 * @author vitor.alves
 */
public class DefaultConfigurableApplicationEnvironment implements ConfigurableApplicationEnvironment {

    private static final String APPLICATION_PROPERTIES = "application-properties.xml";

    @Override
    public List<ApplicationProperty> loadApplicationProperties() {
        List<String> imports = this.findAllImports();
        return this.creatApplicationPropertyFor(imports);
    }

    private List<ApplicationProperty> creatApplicationPropertyFor(List<String> imports) {
        final List<ApplicationProperty> applicationProperties = new ArrayList<>();
        imports.forEach(aImport -> applicationProperties.add(new DefaultApplicationProperty(aImport)));
        return applicationProperties;
    }

    private List<String> findAllImports() {
        List<String> imports = new ArrayList<>();
        List<AppDefinitionType> appDefinitions = this.getAppDefinitions();
        for (AppDefinitionType appDefinition : appDefinitions) {
            if (appDefinition.getImport() != null) {
                imports.addAll(appDefinition.getImport());
            }
        }
        return imports;
    }

    private List<AppDefinitionType> getAppDefinitions() {
        JAXBXmlParser<AppDefinitionType> xmlParser = new JAXBXmlParser<>();
        try {
            return xmlParser.parse(APPLICATION_PROPERTIES, AppDefinitionType.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
