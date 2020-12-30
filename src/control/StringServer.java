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

public class StringServer extends Thread {
    Server mainServerThread;
    ServerSocket myServer = null;
    Student input;

    public void run() {
        try {
            super.run();
            this.openServer();
            this.listening();
        } catch (IOException var2) {
            Logger.getLogger(StringServer.class.getName()).log(Level.SEVERE, (String)null, var2);
        }

    }

    public StringServer(ServerSocket ss) {
        this.myServer = ss;
    }

    public StringServer(Server main) {
        this.mainServerThread = main;
    }

    public void openServer() {
        try {
            this.myServer = new ServerSocket(11001);
        } catch (IOException var2) {
            System.out.println(var2);
        }

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

    public void listening() throws IOException {
        while(true) {
            try {
                Socket clientSocket = this.myServer.accept();
                StringClientThread clientThread = new StringClientThread(clientSocket, this);
                clientThread.start();
            } catch (SocketException var3) {
                System.out.println(" ------- Loi client ------- ");
                var3.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            this.myServer.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}
