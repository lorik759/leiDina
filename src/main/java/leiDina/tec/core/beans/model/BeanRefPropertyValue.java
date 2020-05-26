package main.java.leiDina.tec.core.beans.model;

import java.util.Objects;

/**
 * @author vitor.alves
 */
public class BeanRefPropertyValue {

    private final String beanName;

    public BeanRefPropertyValue(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BeanRefPropertyValue that = (BeanRefPropertyValue) o;
        return Objects.equals(getBeanName(), that.getBeanName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBeanName());
    }
}
