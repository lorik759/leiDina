package main.java.leiDina.tec.vinjection.enums;

/**
 * @author vitor.alves
 */
public enum DefinitionProperty {

    BEANS("beans"), PACKAGES("packages");

    private final String name;

    DefinitionProperty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
