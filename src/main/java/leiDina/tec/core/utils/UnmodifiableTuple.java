package main.java.leiDina.tec.core.utils;

/**
 * An unmodifiable implementation of {@link Tuple}.
 *
 * @author vitor.alves
 */
public class UnmodifiableTuple<H, T> implements Tuple<H, T> {

    private final H head;

    private final T tail;

    public UnmodifiableTuple(H head, T tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public H getHead() {
        return this.head;
    }

    @Override
    public T getTail() {
        return this.tail;
    }
}
