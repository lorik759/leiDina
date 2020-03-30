package main.java.leiDina.tec.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import main.java.leiDina.tec.core.exception.PersistenceException;
import main.java.leiDina.tec.core.model.Entity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class TextFileEntityActorTest {

    private static final String FILE_NAME = "Entity.txt";

    private static final String ORIGINAL_TEXT = "id:1;NOME:vitor";

    private static final String EXPECTED_TEXT_AFTER_UPDATE = "id:1;NOME:test";

    private static final String NEW_SAVE = "id:2;NOME:second test";

    private static File testFile;

    @BeforeAll
    protected static void setUp() throws IOException {
        testFile = new File(FILE_NAME);
        testFile.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(testFile, true);
        fileOut.write(ORIGINAL_TEXT.getBytes());
        fileOut.close();
    }

    @BeforeEach
    protected void resat() throws IOException {
        TextFileEntityActorTest.clean();
        TextFileEntityActorTest.setUp();
    }

    @Test
    public void testEntityWithIdExists() throws IOException {
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(FILE_NAME);
        Assertions.assertFalse(textFileEntityActor.entityWithIdExists(-1L));
        Assertions.assertTrue(textFileEntityActor.entityWithIdExists(1L));
    }

    @Test
    public void testUpdteEntity() throws InvocationTargetException, IOException, IllegalAccessException {
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(FILE_NAME);
        Entity entity = createEntity(1L, "test");
        textFileEntityActor.updateEntity(entity);
        Scanner scanner = new Scanner(testFile);
        String line = scanner.nextLine();
        Assertions.assertEquals(EXPECTED_TEXT_AFTER_UPDATE, line);
        scanner.close();
    }

    @Test
    public void testNewSave() throws InvocationTargetException, IOException, IllegalAccessException {
        validateNewSave(ORIGINAL_TEXT);
    }

    @Test
    public void testNewSaveThanUpdateEntity() throws IOException, IllegalAccessException, InvocationTargetException {
        this.testNewSave();
        this.testUpdteEntity();
        this.validateUpdateAndNewSave();
    }

    @Test
    public void testUpdateThanNewSave() throws IOException, IllegalAccessException, InvocationTargetException {
        this.testUpdteEntity();
        this.validateNewSave(EXPECTED_TEXT_AFTER_UPDATE);
        this.validateUpdateAndNewSave();
    }

    @Test
    public void testUpdateEntityNotFound() {
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(FILE_NAME);
        Entity entity = createEntity(10L, "any");
        Assertions.assertThrows(PersistenceException.class, () -> textFileEntityActor.updateEntity(entity));
    }

    private void validateNewSave(String lineOneText) throws IllegalAccessException, InvocationTargetException, IOException {
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(FILE_NAME);
        Entity entity = createEntity(2L, "second test");
        textFileEntityActor.saveNew(entity);
        Scanner scanner = new Scanner(testFile);
        String lineOne = scanner.nextLine();
        Assertions.assertEquals(lineOneText, lineOne);
        String lineToo = scanner.nextLine();
        Assertions.assertEquals(NEW_SAVE, lineToo);
        scanner.close();
    }

    private void validateUpdateAndNewSave() throws FileNotFoundException {
        Scanner scanner = new Scanner(testFile);
        int cont = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (scanner.hasNextLine()) {
            cont++;
            stringBuffer.append(scanner.nextLine());
            if (cont < 2) {
                stringBuffer.append(System.lineSeparator());
            }
        }
        String string = stringBuffer.toString();
        Assertions.assertEquals(EXPECTED_TEXT_AFTER_UPDATE + System.lineSeparator() + NEW_SAVE, string);
        Assertions.assertEquals(2, cont);
    }

    @Test
    public void testRemove() throws IllegalAccessException, IOException, InvocationTargetException {
        this.testNewSaveThanUpdateEntity();
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(testFile);
        Entity entity = new Entity();
        entity.setId(1L);
        entity.setNome("test");
        textFileEntityActor.removeEntity(entity);
        Scanner scanner = new Scanner(testFile);
        int cont = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (scanner.hasNextLine()) {
            cont++;
            stringBuffer.append(scanner.nextLine());
        }
        String string = stringBuffer.toString();
        Assertions.assertEquals(NEW_SAVE, string);
        Assertions.assertEquals(1, cont);
    }

    @Test
    public void testGet() throws IOException, IllegalAccessException, InvocationTargetException {
        Entity entity = createEntity(25L, "test");
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(testFile);
        textFileEntityActor.saveNew(entity);
        Entity getEntity = textFileEntityActor.get(25L, Entity.class);
        Assertions.assertNotNull(getEntity.getId());
        Assertions.assertEquals(entity.getId(), getEntity.getId());
        Assertions.assertNotNull(getEntity.getNome());
        Assertions.assertEquals(entity.getNome(), getEntity.getNome());
    }

    @Test
    public void testSaveUpdateGet() throws IOException, IllegalAccessException, InvocationTargetException {
        this.testUpdteEntity();
        this.validateNewSave(EXPECTED_TEXT_AFTER_UPDATE);
        this.validateUpdateAndNewSave();
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(testFile);
        Entity entity = textFileEntityActor.get(1L, Entity.class);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertEquals(1L, entity.getId());
        Assertions.assertNotNull(entity.getNome());
        Assertions.assertEquals("test", entity.getNome());
    }

    private Entity createEntity(Long id, String name) {
        Entity entity = new Entity();
        entity.setId(id);
        entity.setNome(name);
        return entity;
    }

    @AfterAll
    public static void clean() {
        testFile.delete();
    }
}
