package main.java.leiDina.tec.core.io;

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
import main.java.leiDina.tec.core.model.EntityDescriptor;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * An actor that knows how to open e write in a text file that contains a persistable entity.
 *
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

    /**
     * Opens the file of the entity to be read.
     *
     * @throws FileNotFoundException {@link FileNotFoundException}.
     */
    private void openToRead() throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    /**
     * Closes the file of the entity to be read.
     */
    private void closeToRead() {
        this.scanner.close();
    }

    /**
     * Opens and reads the text file of an entity. If the entity with the same id exists in the text file than the return value is <>true</> and <>false</> otherwise.
     *
     * @param entityId entity to be checked if exists.
     * @return A boolean.
     * @throws IOException {@link IOException}.
     */
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

    /**
     * Updates the line that represents the specified entity (By the id only).
     *
     * @param entity the entity that will be updated.
     * @throws IllegalAccessException {@link IllegalAccessException}.
     * @throws InvocationTargetException {@link InvocationTargetException}.
     * @throws IOException {@link IOException}.
     */
    public void updateEntity(Persistable entity) throws IllegalAccessException, InvocationTargetException, IOException {
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

    /**
     * Saves a new entity within the text file. If the entity already exists, then a {@link PersistenceException} is thrown.
     *
     * @param entity the entity that is to be saved.
     * @throws IllegalAccessException {@link IllegalAccessException}.
     * @throws InvocationTargetException {@link InvocationTargetException}.
     * @throws IOException {@link IOException}.
     */
    public void saveNew(Persistable entity) throws IllegalAccessException, InvocationTargetException, IOException {
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

    /**
     * Removes the entity from the text file. If the entity dose not exist, then a {@link PersistenceException} is thrown.
     *
     * @param entity the entity to be removed.
     * @throws IOException {@link IOException}.
     */
    public void removeEntity(Persistable entity) throws IOException {
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
                found = true;
            } else if (!sameId) {
                stringBuffer.append(line);
                if (this.scanner.hasNextLine()) {
                    stringBuffer.append(System.lineSeparator());
                }
            } else {
                throw new PersistenceException(BaseSystemMessages.TOO_MANY_ENTITY_FOUND.create(entity.getClass(), entity.getId()));
            }
        }
        if (!found) {
            throw new PersistenceException(BaseSystemMessages.ENTITY_NOT_FOUND.create(entity.getClass(), entity.getId()));
        }
        this.closeToRead();
        this.writeToFile(stringBuffer);
    }

    /**
     * Rewrites the text file with the new string from within the {@link StringBuffer}.
     *
     * @param stringBuffer a {@link StringBuffer} that contains the information fo the new text file.
     * @throws IOException {@link IOException}.
     */
    private void writeToFile(StringBuffer stringBuffer) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        fileOut.write(stringBuffer.toString().getBytes());
        fileOut.close();
    }

    public <T extends Persistable> T get(Serializable id, Class<T> type) throws FileNotFoundException {
        String line;
        this.openToRead();
        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();
            Map<String, String> properties = textToEntityDigester.digest(line);
            String idFound = properties.get("id");
            if (idFound.equals(String.valueOf(id))) {
                return (T) new EntityDescriptor(type).createInstanceFromLine(line);
            }
        }
        throw new PersistenceException(BaseSystemMessages.ENTITY_NOT_FOUND.create(type, id));
    }
}
