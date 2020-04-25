package main.java.leiDina.tec.core.xml.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import main.java.leiDina.tec.core.xml.model.bean.Beans;
import main.java.leiDina.tec.core.xml.model.packages.Packages;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "Definition", namespace = "http://www.vaplication.com/definition")
@XmlType(propOrder = {"packages", "beans"})
public class Definition {

    private Packages packages;

    private Beans beans;

    public Packages getPackages() {
        return packages;
    }

    @XmlElement(name = "Packages", namespace = "http://www.vaplication.com/definition")
    public void setPackages(Packages packages) {
        this.packages = packages;
    }

    public Beans getBeans() {
        return beans;
    }

    @XmlElement(name = "Beans", namespace = "http://www.vaplication.com/definition")
    public void setBeans(Beans beans) {
        this.beans = beans;
    }
}
