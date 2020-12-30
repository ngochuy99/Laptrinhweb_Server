//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import model.Student;

final class ClientList$3 implements Runnable {
    ClientList$3() {
    }

    public void run() {
        ClientList client = new ClientList();
        client.setVisible(true);
        client.addNewRow((Student)null);
    }
}
