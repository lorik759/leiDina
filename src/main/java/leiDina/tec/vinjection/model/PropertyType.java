package main.java.leiDina.tec.vinjection.model;

/**
 * @author vitor.alves
 */
public enum PropertyType {

    BEAN, MAP, LIST;

    public boolean isBean() {
        return this.equals(BEAN);
    }

    public boolean isMap() {
        return this.equals(MAP);
    }
}
