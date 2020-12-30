//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.io.Serializable;

public class ServerConfiguration implements Serializable {
    static final long serialVersionUID = 3L;
    String rmiServerName = "";
    int rmiPort = 0;
    int stringServerPort = 0;
    int numericServerPort = 0;
    int code = 0;

    public ServerConfiguration() {
    }

    public String getRmiServerName() {
        return this.rmiServerName;
    }

    public void setRmiServerName(String rmiServerName) {
        this.rmiServerName = rmiServerName;
    }

    public int getRmiPort() {
        return this.rmiPort;
    }

    public void setRmiPort(int rmiPort) {
        this.rmiPort = rmiPort;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStringServerPort() {
        return this.stringServerPort;
    }

    public void setStringServerPort(int stringServerPort) {
        this.stringServerPort = stringServerPort;
    }

    public int getNumericServerPort() {
        return this.numericServerPort;
    }

    public void setNumericServerPort(int numericServerPort) {
        this.numericServerPort = numericServerPort;
    }
}
