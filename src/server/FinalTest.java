//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server;

import control.Server;
import java.io.IOException;
import view.ClientList;

public class FinalTest {
    public FinalTest() {
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ClientList view = new ClientList();
        Server multiServer = new Server(view);
        multiServer.openServer();
    }
}
