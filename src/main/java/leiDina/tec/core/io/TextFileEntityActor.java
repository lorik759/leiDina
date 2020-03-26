package main.java.leiDina.tec.core.io;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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

    private TextFileEntityDigester textFileEntityDigester;

    private EntityToTextDigester entityToTextDigester;

    public TextFileEntityActor(File file) {
        this.file = file;
        this.textFileEntityDigester = new TextFileEntityDigester();
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
            Map<String, String> properties = textFileEntityDigester.digestLine(this.scanner.nextLine());
            String id = properties.get("id");
            if (entityId.equals(id)) {
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
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        this.openToRead();
        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();
            Map<String, String> properties = textFileEntityDigester.digestLine(line);
            if (properties.get("id").equals(String.valueOf(id))) {
                inputBuffer.append(entityLine);
            } else {
                inputBuffer.append(line);
            }
            inputBuffer.append(System.lineSeparator());
        }
        this.closeToRead();
        this.overwriteFile(inputBuffer);
    }

    private void overwriteFile(StringBuffer inputBuffer) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();
    }
}
