package main.java.leiDina.tec.javafx.service;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * @author vitor.alves
 */
public class ModelSceneWire {

    private Map<Class<? extends Annotation>, NodeAssociation> associationMap = new HashMap<>();

    private FXMLLoader fxmlLoader;

    public void wire(BaseModelController<?> controller) throws Exception {
        Object model = controller.getModel();
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), model.getClass());
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null) {
                for (Annotation declaredAnnotation : writeMethod.getDeclaredAnnotations()) {
                    NodeAssociation nodeAssociation = associationMap.get(declaredAnnotation.annotationType());
                    if (nodeAssociation != null) {
                        String name = propertyDescriptor.getName();
                        Node node = (Node) fxmlLoader.getNamespace().get(name);
                        if (node == null) {
                            throw new VFXException(FXSystemMessages.NO_COMPONENT_OF_ID.create(name));
                        }
                        nodeAssociation.associate(model, declaredAnnotation, propertyDescriptor, node);
                    }
                }
            }
        }
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void setAssociationMap(Map<Class<? extends Annotation>, NodeAssociation> associationMap) {
        this.associationMap = associationMap;
    }
}
