package main.java.leiDina.tec.javafx.scene;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.util.Builder;
import main.java.leiDina.tec.core.ApplicationThreadContext;
import main.java.leiDina.tec.javafx.controller.BaseController;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class GenericNodeBuilder implements Builder<Object> {

    private final Class<?> type;

    private Pos alignment;

    private double hgap;

    private double vgap;

    public GenericNodeBuilder(Class<?> type) {
        this.type = type;
    }

    @Override
    public Object build() {
//        BaseController currentController = ApplicationThreadContext.getApplicationContext().getSceneManager().getCurrentController();
        Node node = (Node) ReflectionUtils.newInstance(type);
//        node.idProperty().addListener(new NodeControllerAssociationListener(node, currentController));
        return node;
    }

    public void setAlignment(Pos alignment) {
        this.alignment = alignment;
    }

    public Pos getAlignment() {
        return alignment;
    }

    public double getHgap() {
        return hgap;
    }

    public void setHgap(double hgap) {
        this.hgap = hgap;
    }

    public double getVgap() {
        return vgap;
    }

    public void setVgap(double vgap) {
        this.vgap = vgap;
    }
}
