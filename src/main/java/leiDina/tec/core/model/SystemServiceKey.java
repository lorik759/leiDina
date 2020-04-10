package main.java.leiDina.tec.core.model;

/**
 * A key that indacates a {@link main.java.leiDina.tec.core.service.SystemService}.
 *
 * @author vitor.alves
 */
public abstract class SystemServiceKey {

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass().equals(obj.getClass());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + this.getClass().hashCode();
        return result;
    }
}
