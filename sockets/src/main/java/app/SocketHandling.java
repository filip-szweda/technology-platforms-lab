package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandling implements Runnable {
    Socket socket;
    public SocketHandling(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("ready");

            int messagesNumber = (Integer) in.readObject();

            out.writeObject("ready for messages");

            for(int i = 0; i < messagesNumber; i++) {
                Message message = (Message) in.readObject();
                System.out.println(message);
            }

            out.writeObject("finished");
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if(!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}