package main.java.leiDina.tec.javafx.service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import javafx.scene.control.TextInputControl;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.javafx.annotation.NumberInput;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * @author vitor.alves
 */
public class NumberInputNodeAssociation implements NodeAssociation<NumberInput, TextInputControl> {

    @Override
    public void associate(Object model, NumberInput declaredAnnotation, PropertyDescriptor propertyDescriptor, TextInputControl node) {
        try {
            Class<? extends Number> type = declaredAnnotation.type();
            Method method = propertyDescriptor.getWriteMethod();
            node.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    if (!newValue.matches("\\d*\\.?\\d*")) {
                        node.setText(newValue.replaceAll("[^\\d*\\.?\\d*]", ""));
                    } else if (StringUtils.isNotEmpty(newValue)) {
                        ReflectionUtils.invoke(method, model, ReflectionUtils.newInstance(type, newValue));
                    }
                } catch (ReflectiveOperationException e) {
                    throw new VFXException(FXSystemMessages.MODEL_SCENE_ASSOCIATION.create(node.getId(), model.getClass()), e);
                }
            });
            Method readMethod = propertyDescriptor.getReadMethod();
            Number number = (Number) ReflectionUtils.invoke(readMethod, model);
            if (number != null) {
                node.setText(number.toString());
            }
        } catch (ReflectiveOperationException e) {
            throw new VFXException(FXSystemMessages.MODEL_SCENE_ASSOCIATION.create(node.getId(), model.getClass()), e);
        }
    }

    @Override
    public Class<NumberInput> type() {
        return NumberInput.class;
    }
}
