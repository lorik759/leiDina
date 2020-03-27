package main.java.leiDina.tec.core.io;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Scanner;
import main.java.leiDina.tec.core.exception.PersistenceException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
public class TextFileEntityActor {

    private File file;

    private Scanner scanner;

    private TextToEntityDigester textToEntityDigester;

    private EntityToTextDigester entityToTextDigester;

    public TextFileEntityActor(File file) {
        this.file = file;
        this.textToEntityDigester = new TextToEntityDigester();
        this.entityToTextDigester = new EntityToTextDigester();
    }

    public TextFileEntityActor(String name) {
        this(name.endsWith(".txt") ? new File(name) : new File(name + ".txt"));
    }

    private void openToRead() throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    private void closeToRead() {
        this.scanner.close();
    }

    public boolean entityWithIdExists(Serializable entityId) throws IOException {
        if (file.createNewFile()) {
            return false;
        }
        this.openToRead();
        while (this.scanner.hasNextLine()) {
            Map<String, String> properties = textToEntityDigester.digest(this.scanner.nextLine());
            String id = properties.get("id");
            if (entityId.toString().equals(id)) {
                this.closeToRead();
                return true;
            }
        }
        this.closeToRead();
        return false;
    }

    public void updateEntity(Persistable entity) throws IllegalAccessException, IntrospectionException, InvocationTargetException, IOException {
        String entityLine = entityToTextDigester.digest(entity);
        Serializable id = entity.getId();
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        boolean found = false;

        this.openToRead();
        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();
            Map<String, String> properties = textToEntityDigester.digest(line);
            String idFound = properties.get("id");
            boolean sameId = idFound.equals(String.valueOf(id));
            if (sameId && !found) {
                stringBuffer.append(entityLine);
                found = true;
            } else if (!sameId) {
                stringBuffer.append(line);
            } else {
                throw new PersistenceException(BaseSystemMessages.TOO_MANY_ENTITY_FOUND.create(entity.getClass(), entity.getId()));
            }
            stringBuffer.append(System.lineSeparator());
        }
        if (!found) {
            throw new PersistenceException(BaseSystemMessages.ENTITY_NOT_FOUND.create(entity.getClass(), entity.getId()));
        }
        this.closeToRead();
        this.writeToFile(stringBuffer);
    }

    public void saveNew(Persistable entity) throws IllegalAccessException, IntrospectionException, InvocationTargetException, IOException {
        String entityLine = entityToTextDigester.digest(entity);
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        if (this.entityWithIdExists(entity)) {
            throw new PersistenceException(BaseSystemMessages.ENTITY_ALREADY_EXISTS.create(entity.getClass(), entity.getId()));
        }
        this.openToRead();
        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();
            stringBuffer.append(line).append(System.lineSeparator());
        }
        this.writeToFile(stringBuffer.append(entityLine));
    }

    private void writeToFile(StringBuffer stringBuffer) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        fileOut.write(stringBuffer.toString().getBytes());
        fileOut.close();
    }
}
