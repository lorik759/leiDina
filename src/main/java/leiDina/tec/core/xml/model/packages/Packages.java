package main.java.leiDina.tec.core.xml.model.packages;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "Packages", namespace = "http://www.vaplication.com/definition")
public class Packages {

    private List<String> packageName;

    public List<String> getPackageName() {
        return packageName;
    }

    @XmlElement(name = "packageName", namespace = "http://www.vaplication.com/definition")
    public void setPackageName(List<String> packageName) {
        this.packageName = packageName;
    }
}
