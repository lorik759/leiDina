package main.java.leiDina.tec.javafx.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.javafx.annotation.TextField;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * @author vitor.alves
 */
public class TextFieldNodeAssociation implements NodeAssociation<TextField> {

    @Override
    public void associate(final Object model, TextField declaredAnnotation, final Method method, Map<String, Object> componants) {
        final String id = declaredAnnotation.id();
        javafx.scene.control.TextField textField = (javafx.scene.control.TextField) componants.get(id);
        if (textField == null) {
            throw new VFXException(FXSystemMessages.NO_COMPONENT_OF_ID.create(id));
        }
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ReflectionUtils.invoke(method, model, newValue);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new VFXException(FXSystemMessages.MODEL_SCENE_ASSOCIATION.create(id, model.getClass()), e);
            }
        });
        final String text = textField.getText();
        if (StringUtils.isNotEmpty(text)) {
            try {
                ReflectionUtils.invoke(method, model, text);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new VFXException(FXSystemMessages.MODEL_SCENE_ASSOCIATION.create(id, model.getClass()), e);
            }
        }
    }

    @Override
    public Class<TextField> type() {
        return TextField.class;
    }
}
