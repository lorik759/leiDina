package main.java.leiDina.tec.core.utils;

/**
 * @author vitor.alves
 */
public interface Tuple<H, T> {

    /**
     * @return the head of the tulip.
     */
    H getHead();

    /**
     * @return the tail of the tulip.
     */
    T getTail();

}
