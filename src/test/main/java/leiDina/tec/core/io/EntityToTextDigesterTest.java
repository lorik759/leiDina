package main.java.leiDina.tec.core.io;

import java.lang.reflect.InvocationTargetException;
import main.java.leiDina.tec.core.exception.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.leiDina.tec.core.model.EntityOne;
import main.java.leiDina.tec.core.model.EntityToo;

/**
 * @author vitor.alves
 */
public class EntityToTextDigesterTest {

    private static final String ENTITY_ONE = "id:1;INTEGER:-5;STRING:test;BOOLEAN:false";

    @Test
    public void testEntityToLine() throws IllegalAccessException, InvocationTargetException {
        EntityOne entityOne = new EntityOne();
        entityOne.setId(1L);
        entityOne.setABoolean(false);
        entityOne.setString("test");
        entityOne.setInteger(-5);
        EntityToTextDigester entityToTextDigester = new EntityToTextDigester();
        String digest = entityToTextDigester.digest(entityOne);
        Assertions.assertEquals(ENTITY_ONE, digest);
    }

    @Test
    public void testNoGeterMethodException() {
        EntityToo entityToo = new EntityToo();
        entityToo.setId(6L);
        entityToo.setInteger(5);
        EntityToTextDigester entityToTextDigester = new EntityToTextDigester();
        Assertions.assertThrows(PersistenceException.class, () -> entityToTextDigester.digest(entityToo));
    }

}
