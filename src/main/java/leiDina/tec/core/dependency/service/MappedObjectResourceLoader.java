package main.java.leiDina.tec.core.dependency.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import main.java.leiDina.tec.core.dependency.factory.BaseObjectDefinitionFactory;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;
import main.java.leiDina.tec.core.xml.model.ObjectDefinitionType;
import main.java.leiDina.tec.core.xml.model.ObjectType;
import main.java.leiDina.tec.core.xml.service.JAXBXmlParser;


/**
 * @author vitor.alves
 */
public class MappedObjectResourceLoader {

    private final BaseObjectDefinitionFactory baseObjectDefinitionFactory = new BaseObjectDefinitionFactory();

    private final String resource;

    public MappedObjectResourceLoader(String resource) {
        this.resource = resource;
    }

    public Set<ObjectDefinition> load() {
        return this.createObjectDefinitionFor(this.loadObjectTypesFromResource());
    }

    private Set<ObjectDefinition> createObjectDefinitionFor(List<ObjectType> objectTypes) {
        final Set<ObjectDefinition> objectDefinitions = new HashSet<>();
        objectTypes.forEach(objectType -> objectDefinitions.add(baseObjectDefinitionFactory.createFor(objectType, resource)));
        return objectDefinitions;
    }

    private List<ObjectType> loadObjectTypesFromResource() {
        List<ObjectType> objectTypes = new ArrayList<>();
        for (ObjectDefinitionType objectDefinitionType : getObjectDefinitionTypes()) {
            if (isObjectssNotEmpty(objectDefinitionType)) {
                objectTypes.addAll(objectDefinitionType.getObject());
            }
        }
        return objectTypes;
    }

    private boolean isObjectssNotEmpty(ObjectDefinitionType objectDefinitionType) {
        return objectDefinitionType.getObject() != null && !objectDefinitionType.getObject().isEmpty();
    }

    private List<ObjectDefinitionType> getObjectDefinitionTypes() {
        try {
            return new JAXBXmlParser<ObjectDefinitionType>().parse(resource, ObjectDefinitionType.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
