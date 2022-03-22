package app;

import java.io.Serial;
import java.io.Serializable;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int number;
    private String content;

    public Message(int number, String content) {
        this.number = number;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Number: " + number + " Content: " + content;
    }
}