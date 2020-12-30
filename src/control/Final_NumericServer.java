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

public class Final_NumericServer extends Thread {
    Server mainServerThread;
    ServerSocket server = null;

    public Final_NumericServer(ServerSocket ss) {
        this.server = ss;
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

    public Final_NumericServer(Server main) {
        this.mainServerThread = main;
    }

    public void listening() throws IOException {
        while(true) {
            try {
                Socket clientSocket = this.server.accept();
                Final_NumericClientThread clientThread = new Final_NumericClientThread(clientSocket, this);
                clientThread.start();
            } catch (SocketException var3) {
                System.out.println(" ------- Loi client ------- ");
                var3.printStackTrace();
            }
        }
    }

    public void openServer() {
        try {
            this.server = new ServerSocket(11002);
        } catch (IOException var2) {
            System.out.println(var2);
        }

    }

    public void run() {
        super.run();

        try {
            this.openServer();
            this.listening();
        } catch (IOException var2) {
            Logger.getLogger(Final_NumericServer.class.getName()).log(Level.SEVERE, (String)null, var2);
        }

    }
}
