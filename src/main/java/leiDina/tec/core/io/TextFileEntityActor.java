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

    public void upDateEntity(Persistable entity)
        throws IllegalAccessException, IntrospectionException, InvocationTargetException, IOException {
        String entityLine = entityToTextDigester.digest(entity);
        Serializable id = entity.getId();
        StringBuffer stringBuffer = new StringBuffer();
        String line;

        this.openToRead();
        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();
            Map<String, String> properties = textToEntityDigester.digest(line);
            if (properties.get("id").equals(String.valueOf(id))) {
                stringBuffer.append(entityLine);
            } else {
                stringBuffer.append(line);
            }
            stringBuffer.append(System.lineSeparator());
        }
        this.closeToRead();
        this.writeToFile(stringBuffer, false);
    }

    public void saveNew(Persistable entity) throws IllegalAccessException, IntrospectionException, InvocationTargetException, IOException {
        String entityLine = entityToTextDigester.digest(entity);
        StringBuffer stringBuffer = new StringBuffer();
        this.writeToFile(stringBuffer.append(entityLine), true);
    }

    private void writeToFile(StringBuffer inputBuffer, boolean append) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file, append);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();
    }
}
