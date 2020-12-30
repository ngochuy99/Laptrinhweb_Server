//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

public class ObjectServer extends Thread {
    Server mainServerThread;
    ServerSocket myServer = null;
    Student input;

    public ObjectServer(ServerSocket ss) {
        this.myServer = ss;
    }

    public void updateView(Student student) {
        this.mainServerThread.updateView(student);
    }

    public synchronized void updateAnswerList(Answer answer) {
        this.mainServerThread.updateAnswerList(answer);
    }

    public Answer getAnswer(Student student) {
        return this.mainServerThread.getAnswer(student);
    }

    public void addStudent(Student s) {
        this.mainServerThread.addStudent(s);
    }

    public ObjectServer(Server main) {
        this.mainServerThread = main;
    }

    public void listening() throws IOException {
        while(true) {
            try {
                Socket clientSocket = this.myServer.accept();
                ObjectClientThread clientThread = new ObjectClientThread(clientSocket, this);
                clientThread.start();
            } catch (SocketException var3) {
                System.out.println(" ------- Loi client ------- ");
                var3.printStackTrace();
            }
        }
    }

    public void openServer() {
        try {
            this.myServer = new ServerSocket(11050);
        } catch (IOException var2) {
            System.out.println(var2);
        }

    }

    public void run() {
        super.run();
        this.openServer();

        try {
            this.listening();
        } catch (IOException var2) {
            Logger.getLogger(ObjectServer.class.getName()).log(Level.SEVERE, (String)null, var2);
        }

    }
}
