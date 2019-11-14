package ro.sci.digitalCookBook.domain;

import java.util.Base64;

/**
 * Used to define the information needed for a Recipe Photos.
 *
 * @author Andrei Bu
 */

public class RecipePhoto extends AbstractModel {

    private byte[] content;
    private String fileName;


    public byte[] getContent() {
        return this.content;
    }

    public String getEncodedContent() {
        byte[] encode = Base64.getEncoder().encode(this.content);
        return new String(encode);
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "RecipePhoto{" +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
