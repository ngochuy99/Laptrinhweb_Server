//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.ServerConfiguration;
import model.Student;

public interface IRMIServer extends Remote {
    ServerConfiguration getStringServerDes(Student var1, ServerConfiguration var2) throws RemoteException;

    ServerConfiguration getNumericServerDes(Student var1, ServerConfiguration var2) throws RemoteException;
}
