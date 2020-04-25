package main.java.leiDina.tec.core.xml.model.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "beans")
public class Beans {

    private List<Bean> beans;

    public List<Bean> getBeans() {
        return beans;
    }

    @XmlElement(name = "bean", namespace = "http://www.vaplication.com/definition")
    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }
}
