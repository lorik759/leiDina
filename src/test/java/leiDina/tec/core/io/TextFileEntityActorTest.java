package java.leiDina.tec.core.io;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.leiDina.tec.core.model.Entity;
import main.java.leiDina.tec.core.io.TextFileEntityActor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class TextFileEntityActorTest {

    @BeforeAll
    protected static void setUp() {

    }

    @Test
    public void testEntityWithIdExists() throws IOException {
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(getClass().getResource("/Entity.txt").getFile());
        Assertions.assertFalse(textFileEntityActor.entityWithIdExists(-1L));
        Assertions.assertTrue(textFileEntityActor.entityWithIdExists(1L));
    }

    @Test
    public void testUpDateEntity() throws InvocationTargetException, IOException, IntrospectionException, IllegalAccessException {
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(getClass().getResource("/Entity.txt").getFile());
        Entity entity = new Entity();
        entity.setId(1L);
        entity.setNome("teste");
        textFileEntityActor.upDateEntity(entity);
    }
}
