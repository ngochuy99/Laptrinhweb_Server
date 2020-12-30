//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package control;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

public class ObjectClientThread extends Thread {
    Socket clientSocket;
    ObjectServer serverControl;
    ObjectInputStream is;
    ObjectOutputStream os;
    DataInputStream dis;
    Answer answer = null;
    Student student = null;

    public ObjectClientThread(Socket s, ObjectServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
    }

    public boolean checkStudentAnswer() {
        try {
            this.student = (Student)this.is.readObject();
            if (this.student.getMaSV() == null) {
                return false;
            }

            this.serverControl.addStudent(this.student);
            this.answer = this.serverControl.getAnswer(this.student);
            if (this.answer == null) {
                System.out.println("Got a new registration, initiate answer");
                this.answer = new Answer(this.student);
                this.answer.setAlreadyRegistration(true);
            } else {
                this.answer.setStudent(this.student);
                this.answer.setAlreadyRegistration(true);
            }

            this.serverControl.updateAnswerList(this.answer);
            this.serverControl.updateView(this.student);
            return true;
        } catch (IOException var7) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var7.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var6) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var6);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var5) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var5);
                }
            }
        } catch (ClassNotFoundException var8) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var8.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var4) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var4);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var3) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var3);
                }
            }
        }

        return false;
    }

    public void run() {
        super.run();

        try {
            this.os = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.is = new ObjectInputStream(this.clientSocket.getInputStream());
            this.dis = new DataInputStream(this.clientSocket.getInputStream());
            this.checkStudentAnswer();
            this.os.writeObject(this.answer);
        } catch (SocketException var31) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var31.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var30) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var30);
                }
            }
        } catch (IOException var32) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var32.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var29) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var28) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var28);
                }
            }
        } catch (Exception var33) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var33.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var27) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var27);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var26) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var26);
                }
            }
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var25) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var25);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var24) {
                    Logger.getLogger(ObjectClientThread.class.getName()).log(Level.SEVERE, (String)null, var24);
                }
            }

        }

    }
}
