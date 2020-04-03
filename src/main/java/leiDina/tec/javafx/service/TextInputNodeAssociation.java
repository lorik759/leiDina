package main.java.leiDina.tec.javafx.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.scene.control.TextInputControl;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.javafx.annotation.TextInput;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * An implantation of a {@link NodeAssociation}, that associates fields that are annotated with {@link TextInput} to a {@link TextInputControl}.
 *
 * @author vitor.alves
 */
public class TextInputNodeAssociation implements NodeAssociation<TextInput, TextInputControl> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void associate(final Object model, final TextInput declaredAnnotation, final Method method, final TextInputControl node) {
        node.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ReflectionUtils.invoke(method, model, newValue);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new VFXException(FXSystemMessages.MODEL_SCENE_ASSOCIATION.create(node.getId(), model.getClass()), e);
            }
        });
        String text = node.getText();
        if (StringUtils.isNotEmpty(text)) {
            try {
                ReflectionUtils.invoke(method, model, text);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new VFXException(FXSystemMessages.MODEL_SCENE_ASSOCIATION.create(node.getId(), model.getClass()), e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<TextInput> type() {
        return TextInput.class;
    }
}
