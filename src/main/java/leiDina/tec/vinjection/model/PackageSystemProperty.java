package main.java.leiDina.tec.vinjection.model;

import java.util.List;
import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * @author vitor.alves
 */
public class PackageSystemProperty  implements SystemProperty<String> {

    private List<String> packages;

    @Override
    public Class<?> getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Packages";
    }

    @Override
    public List<String> getProperties() {
        return this.packages;
    }

    @Override
    public String getProperty() {
        return this.packages.get(0);
    }

    @Override
    public void addProperty(String property) {
        this.packages.add(property);
    }

    @Override
    public void addProperties(List<?> properties) {
        this.packages = (List<String>) properties;
    }
}
