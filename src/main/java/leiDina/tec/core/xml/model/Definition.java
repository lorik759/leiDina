package main.java.leiDina.tec.core.xml.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import main.java.leiDina.tec.core.xml.model.bean.Beans;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "Definition", namespace = "http://www.vaplication.com/definition")
@XmlType(propOrder = {"packages", "beans"})
public class Definition {

    private List<String> packages;

    private Beans beans;

    public List<String> getPackages() {
        return packages;
    }

    @XmlElement(name = "package", namespace = "http://www.vaplication.com/definition")
    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public Beans getBeans() {
        return beans;
    }

    @XmlElement(name = "beans", namespace = "http://www.vaplication.com/definition")
    public void setBeans(Beans beans) {
        this.beans = beans;
    }
}
