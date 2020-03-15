package main.java.leiDina.tec.javafx.scene;


import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import main.java.leiDina.tec.javafx.controller.BaseController;
import main.java.leiDina.tec.javafx.exception.VFMLLoaderException;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class NodeControllerAssociationListener implements ChangeListener<String> {

    private final Node node;

    private final BaseController currentController;

    public NodeControllerAssociationListener(Node node, BaseController currentController) {
        this.node = node;
        this.currentController = currentController;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        try {
            Map<String, Node> components = (Map<String, Node>) ReflectionUtils.getValueFromFiled(currentController, "components");
            components.put(newValue, node);
            node.idProperty().removeListener(this);
        } catch (Throwable e) {
            throw new VFMLLoaderException(e);
        }
    }
}
