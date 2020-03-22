package main.java.leiDina.tec.javafx.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.core.service.Wire;
import main.java.leiDina.tec.javafx.controller.BaseModelController;

/**
 * @author vitor.alves
 */
public class ModelSceneWire implements Wire<BaseModelController<?>> {

    private final Map<Class<? extends Annotation>, NodeAssociation> associationMap = new HashMap<>();

    private FXMLLoader fxmlLoader;

    @Override
    public void wire(BaseModelController<?> controller) {
        Object model = controller.getModel();
        Method[] methods = model.getClass().getMethods();
        for (Method method : methods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                NodeAssociation nodeAssociation = associationMap.get(declaredAnnotation.annotationType());
                if (nodeAssociation != null) {
                    nodeAssociation.associate(model, method, fxmlLoader.getNamespace());
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
