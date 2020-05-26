package main.java.leiDina.tec.core.service;

/**
 * @author vitor.alves
 */
public class ClassValueResolver implements ValueResolver<Class<?>> {

    @Override
    public Class<?> resolve(String value) throws Exception {
        return Class.forName(value);
    }
}
