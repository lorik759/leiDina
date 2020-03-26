package main.java.leiDina.tec.javafx.service;

import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.core.service.Wire;
import main.java.leiDina.tec.javafx.controller.BaseModelController;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vitor.alves
 */
public class ModelSceneWire implements Wire<BaseModelController<?>> {

    private final Map<Class<? extends Annotation>, NodeAssociation> associationMap = new HashMap<>();

    private FXMLLoader fxmlLoader;

    @Override
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
                        nodeAssociation.associate(model, declaredAnnotation, writeMethod, fxmlLoader.getNamespace());
                    }
                }
            }
        }
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void addModelComponantAssociation(Class<? extends Annotation> clazz, NodeAssociation nodeAssociation) {
        this.associationMap.put(clazz, nodeAssociation);
    }
}
