package main.java.leiDina.tec.vinjection;

import main.java.leiDina.tec.vinjection.service.BeanWire;

/**
 * @author vitor.alves
 */
public class BeanWireThreadContext {

    private static ThreadLocal<BeanWire> beanWireThreadLocal = new ThreadLocal<>();

    /**
     * Initialize the thread context.
     *
     * @param beanWire the {@link BeanWire} of the thread.
     */
    public static void init(BeanWire beanWire) {
        beanWireThreadLocal.set(beanWire);
    }

    /**
     * @return the {@link BeanWire} of the thread.
     */
    public static BeanWire getBeanWire() {
        BeanWire beanWire = beanWireThreadLocal.get();
        return beanWire;
    }

}
