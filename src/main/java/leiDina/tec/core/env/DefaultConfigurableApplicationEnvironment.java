package main.java.leiDina.tec.core.env;

import java.util.ArrayList;
import java.util.List;
import main.java.leiDina.tec.core.xml.model.AppDefinitionType;
import main.java.leiDina.tec.core.xml.service.JAXBXmlParser;


/**
 * This default implementation of {@link ConfigurableApplicationEnvironment} that represents an "application-environment.xml" that is used to import
 * other xml files, where bean are mapped. An example of an "application-environment.xml" is:
 *
 * <code>
 * <definition xmlns="http://www.vaplication.com/definition">
 * <import>fx-beans.xml</import>
 * <import>persister-beans.xml</import>
 * ...
 * </definition>
 * </code>
 *
 * This permits that each individual module can have their own bean definition xml. The default environment also looks for any xml file named
 * "application-environment.xml", this means that each individual module can also have their own application-environment.xml file. For how to map
 * beans in a xml file look at {@link main.java.leiDina.tec.core.env.DefaultApplicationProperty} for an example.
 *
 * @author vitor.alves
 */
public class DefaultConfigurableApplicationEnvironment implements ConfigurableApplicationEnvironment {

    private static final String APPLICATION_ENVIRONMENT_XML = "application-environment.xml";

    /**
     * Loads all "application-environment.xml", and creates a {@link DefaultApplicationProperty} for each individual import.
     *
     * @return a list of {@link DefaultApplicationProperty}.
     */
    @Override
    public List<ApplicationProperty> loadApplicationProperties() {
        List<String> imports = this.findAllImports();
        return this.creatApplicationPropertyFor(imports);
    }

    /**
     * Creates a {@link DefaultApplicationProperty} for each individual imported file.
     *
     * @param imports a list of imported files.
     * @return a list of {@link DefaultApplicationProperty}.
     */
    private List<ApplicationProperty> creatApplicationPropertyFor(List<String> imports) {
        final List<ApplicationProperty> applicationProperties = new ArrayList<>();
        imports.forEach(aImport -> applicationProperties.add(new DefaultApplicationProperty(aImport)));
        return applicationProperties;
    }

    /**
     * Finds all imported files inside all "application-environment.xml".
     *
     * @return a list of all imported files that are contained inside the "application-environment.xml"
     */
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

    /**
     * Reads and converts the "application-environment.xml" to {@link AppDefinitionType}.
     *
     * @return a list of {@link AppDefinitionType}.
     */
    private List<AppDefinitionType> getAppDefinitions() {
        JAXBXmlParser<AppDefinitionType> xmlParser = new JAXBXmlParser<>();
        try {
            return xmlParser.parse(APPLICATION_ENVIRONMENT_XML, AppDefinitionType.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
