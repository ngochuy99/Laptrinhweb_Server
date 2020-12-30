//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.BitSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.ExamCode;
import model.Student;

public class Final_NumericClientThread extends Thread {
    Socket clientSocket;
    Final_NumericServer serverControl;
    DataInputStream is;
    DataOutputStream os;
    ObjectOutputStream ooS;
    ObjectInputStream ioS;
    int numOperator = 0;
    int operatorCode = -1;
    int insertNumeric = 0;
    String operatorType = null;
    int[] intAnswers = null;
    long[] longAnswers = null;
    Answer answer = null;
    Student student = null;
    int intAnswer = 0;
    long longAnswer = 0L;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    int A = 0;

    public Final_NumericClientThread(Socket s, Final_NumericServer serverControl) {
        this.clientSocket = s;
        this.serverControl = serverControl;
        this.answer = new Answer();
    }

    private boolean initiateStudentAnswer() throws IOException {
        this.student = new Student(this.is.readUTF(), this.is.readUTF(), this.clientSocket.getInetAddress().getHostAddress(), this.is.readInt());
        if (this.student.getMaSV().trim().equalsIgnoreCase("")) {
            System.out.println("SV may " + this.student.getIP() + " Nhap khong dung ma sv");
            return false;
        } else {
            this.answer = this.serverControl.getAnswer(this.student);
            if (this.answer == null) {
                System.err.println(this.student.getIP() + " chua qua buoc 1");
                return false;
            } else {
                this.answer.setStudent(this.student);
                return true;
            }
        }
    }

    private void checkResponse(int code) throws IOException {
        switch(code) {
            case 1:
                int uscln = this.is.readInt();
                if (this.USCLN(this.A, this.convertString2Num_Ca2(this.str3)) == uscln) {
                    System.out.println("Ket qua: " + uscln + " Dung");
                    this.answer.updateAnswer(3, uscln, true);
                } else {
                    System.err.println("Ket qua: " + uscln + " Sai");
                    this.answer.updateAnswer(3, uscln, false);
                }
                break;
            case 2:
                int bscnn = this.is.readInt();
                if (this.BSCNN(this.intAnswers[0], this.intAnswers[1]) == bscnn) {
                    System.out.println("Ket qua: " + bscnn + " Dung");
                    this.answer.updateAnswer(4, bscnn, true);
                } else {
                    System.err.println("Ket qua: " + bscnn + " Sai");
                    this.answer.updateAnswer(4, bscnn, false);
                }
                break;
            case 3:
                boolean isRight = true;
                this.sortASC(this.intAnswers);
                int[] results = this.insert(this.intAnswers, this.insertNumeric);
                int i;
                if (this.operatorCode == 1) {
                    for(i = 0; i < results.length; ++i) {
                        if (this.is.readInt() != results[i]) {
                            isRight = false;
                            break;
                        }
                    }
                } else {
                    for(i = results.length - 1; i >= 0; --i) {
                        if (this.is.readInt() != results[i]) {
                            isRight = false;
                            break;
                        }
                    }
                }

                if (isRight) {
                    this.answer.updateAnswer(2, -1, true);
                } else {
                    this.answer.updateAnswer(2, -1, false);
                }
                break;
            case 4:
                boolean isCoPrime = this.is.readBoolean();
                this.answer.updateAnswer(4, isCoPrime, isCoPrime & this.coprime(this.sumOfTheNumbers(this.str1), this.sumOfTheNumbers(this.str2)));
                break;
            case 5:
                int harshad = this.is.readInt();
                int num = this.convertString2Num_Ca1(this.str3);
                int x = num;

                int sum;
                for(sum = 0; x > 0; x /= 10) {
                    int y = x % 10;
                    sum += y;
                }

                if ((num % sum != 0 || harshad != 1) && (num % sum == 0 || harshad != 0)) {
                    this.answer.updateAnswer(4, harshad, false);
                } else {
                    this.answer.updateAnswer(4, harshad, true);
                }
                break;
            case 6:
                int countPrime = this.is.readInt();
                if (countPrime == this.countPrimes(this.convertString2Num_Ca2(this.str3))) {
                    this.answer.updateAnswer(4, countPrime, true);
                } else {
                    this.answer.updateAnswer(4, countPrime, false);
                }
            case 7:
            default:
                break;
            case 8:
                boolean isCoPrime_kip23 = this.is.readBoolean();
                this.answer.updateAnswer(4, isCoPrime_kip23, isCoPrime_kip23 & this.coprime(this.convertString2Num_Ca2(this.str1), this.convertString2Num_Ca2(this.str2)));
        }

    }

    private void sendQuestion(int numOperator) throws IOException {
        this.intAnswers = new int[numOperator];

        for(int i = 0; i < numOperator; ++i) {
            int tmp = (new Random()).nextInt(100) + 100;
            this.os.writeInt(tmp);
            this.intAnswers[i] = tmp;
        }

    }

    public void run() {
        try {
            super.run();
            this.ooS = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.os = new DataOutputStream(this.clientSocket.getOutputStream());
            this.is = new DataInputStream(this.clientSocket.getInputStream());
            this.ioS = new ObjectInputStream(this.clientSocket.getInputStream());
            if (this.initiateStudentAnswer()) {
                int code = this.is.readInt();
                if (code == 0) {
                    this.str1 = this.randomSpaceNumericString_Ca2(3);
                    this.str2 = this.randomSpaceNumericString_Ca2(3);
                    this.os.writeUTF(this.str1);
                    this.os.writeUTF(this.str2);
                    this.checkResponse(8);
                    this.A = (new Random()).nextInt(10) + 100;
                    this.os.writeInt(this.A);
                    this.str3 = this.randomSpaceNumericString_Ca2(3);
                    this.os.writeUTF(this.str3);
                    this.checkResponse(1);
                } else if (code == 1) {
                    this.A = (new Random()).nextInt(10) + 100;
                    this.os.writeInt(this.A);
                    this.str3 = this.randomSpaceNumericString_Ca2(3);
                    this.os.writeUTF(this.str3);
                    this.checkResponse(1);
                    this.str1 = this.randomSpaceNumericString_Ca2(3);
                    this.str2 = this.randomSpaceNumericString_Ca2(3);
                    this.os.writeUTF(this.str1);
                    this.os.writeUTF(this.str2);
                    this.checkResponse(8);
                }

                this.ooS.writeObject(this.answer);
                String ack = this.is.readUTF();
                if (ack.equalsIgnoreCase("OK")) {
                    this.serverControl.updateAnswerList(this.answer);
                    this.serverControl.updateView(this.student);
                }

                return;
            }
        } catch (SocketException var33) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var33.printStackTrace();
            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var32) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var32);
                }

                return;
            }

            return;
        } catch (IOException var34) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var34.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var31) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var31);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var30) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var30);
                }

                return;
            }

            return;
        } catch (Exception var35) {
            System.out.println(" ------- Loi client ------- ");
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            var35.printStackTrace();
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var29) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var29);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var28) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var28);
                }

                return;
            }

            return;
        } finally {
            System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
            if (this.is != null && this.os != null) {
                try {
                    this.is.close();
                    this.os.close();
                } catch (IOException var27) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var27);
                }
            }

            if (this.clientSocket != null) {
                try {
                    this.clientSocket.close();
                } catch (IOException var26) {
                    Logger.getLogger(Final_NumericClientThread.class.getName()).log(Level.SEVERE, (String)null, var26);
                }
            }

        }

    }

    public int sumOfTheNumbers(String stng) {
        int l = stng.length();
        int sum = 0;
        String temp = "";

        for(int i = 0; i < l; ++i) {
            if (Character.isDigit(stng.charAt(i))) {
                if (i < l - 1 && Character.isDigit(stng.charAt(i + 1))) {
                    temp = temp + stng.charAt(i);
                } else {
                    temp = temp + stng.charAt(i);
                    sum += Integer.parseInt(temp);
                    temp = "";
                }
            }
        }

        return sum;
    }

    private String randomNumericString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            for(int i = 0; i < (new Random()).nextInt(7) + 1; ++i) {
                int character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                builder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
                if ((new Random()).nextInt(2) == 1) {
                    int numCharacter = (int)(Math.random() * (double)"0123456789".length());
                    builder.append("0123456789".charAt(numCharacter));
                }
            }

            if (count != 0) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    private int gcd(int a, int b) {
        if (a != 0 && b != 0) {
            if (a == b) {
                return a;
            } else {
                return a > b ? this.gcd(a - b, b) : this.gcd(a, b - a);
            }
        } else {
            return 0;
        }
    }

    private boolean coprime(int a, int b) {
        return this.gcd(a, b) == 1;
    }

    private String randomSpaceNumericString_Ca1(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            builder.append(ExamCode.STRING_NUMERIC[(int)(Math.random() * (double)ExamCode.STRING_NUMERIC.length)]);
            builder.append(" ");
        }

        return builder.toString().trim();
    }

    private int convertString2Num_Ca1(String str) {
        String[] nums = str.split(" ");
        String strNum = "";

        for(int i = 0; i < nums.length; ++i) {
            String num = nums[i];
            if (num.equalsIgnoreCase("khong")) {
                strNum = strNum + "0";
            } else if (num.equalsIgnoreCase("mot")) {
                strNum = strNum + "1";
            } else if (num.equalsIgnoreCase("hai")) {
                strNum = strNum + "2";
            } else if (num.equalsIgnoreCase("ba")) {
                strNum = strNum + "3";
            } else if (num.equalsIgnoreCase("bon")) {
                strNum = strNum + "4";
            } else if (num.equalsIgnoreCase("nam")) {
                strNum = strNum + "5";
            } else if (num.equalsIgnoreCase("sau")) {
                strNum = strNum + "6";
            } else if (num.equalsIgnoreCase("bay")) {
                strNum = strNum + "7";
            } else if (num.equalsIgnoreCase("tam")) {
                strNum = strNum + "8";
            } else if (num.equalsIgnoreCase("chin")) {
                strNum = strNum + "9";
            }
        }

        return Integer.valueOf(strNum);
    }

    private int convertString2Num_Ca2(String str) {
        String strNum = "";
        int nextUpper = 0;

        for(int i = 0; i < str.length(); ++i) {
            if (Character.isUpperCase(str.charAt(i)) && i > nextUpper || i == str.length() - 1) {
                String word = "";
                if (i == str.length() - 1) {
                    word = str.substring(nextUpper, i + 1);
                } else {
                    word = str.substring(nextUpper, i);
                }

                if (word.equalsIgnoreCase("khong")) {
                    strNum = strNum + "0";
                } else if (word.equalsIgnoreCase("mot")) {
                    strNum = strNum + "1";
                } else if (word.equalsIgnoreCase("hai")) {
                    strNum = strNum + "2";
                } else if (word.equalsIgnoreCase("ba")) {
                    strNum = strNum + "3";
                } else if (word.equalsIgnoreCase("bon")) {
                    strNum = strNum + "4";
                } else if (word.equalsIgnoreCase("nam")) {
                    strNum = strNum + "5";
                } else if (word.equalsIgnoreCase("sau")) {
                    strNum = strNum + "6";
                } else if (word.equalsIgnoreCase("bay")) {
                    strNum = strNum + "7";
                } else if (word.equalsIgnoreCase("tam")) {
                    strNum = strNum + "8";
                } else if (word.equalsIgnoreCase("chin")) {
                    strNum = strNum + "9";
                }

                nextUpper = i;
            }
        }

        return Integer.valueOf(strNum);
    }

    private String randomSpaceNumericString_Ca2(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            builder.append(ExamCode.STRING_NUMERIC[(int)(Math.random() * (double)ExamCode.STRING_NUMERIC.length)]);
        }

        return builder.toString().trim();
    }

    public int countPrimes(int n) {
        if (n > 0 && n != 1) {
            if (n != 3 && n != 2) {
                BitSet set = new BitSet();
                --n;
                int s = (int)Math.sqrt((double)n);
                int ctr = n;

                for(int p = 2; p <= s; ++p) {
                    if (!set.get(p)) {
                        for(int q = 2; p * q <= n; ++q) {
                            if (!set.get(p * q)) {
                                --ctr;
                                set.set(p * q);
                            }
                        }
                    }
                }

                return ctr - 1;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    private int USCLN(int a, int b) {
        return b == 0 ? a : this.USCLN(b, a % b);
    }

    private long longUSCLN(long a, long b) {
        return b == 0L ? a : this.longUSCLN(b, a % b);
    }

    private int BSCNN(int a, int b) {
        return a * b / this.USCLN(a, b);
    }

    private long longBSCNN(long a, long b) {
        return a * b / this.longBSCNN(a, b);
    }

    private void sortASC(int[] arr) {
        int temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private void sortLongASC(long[] arr) {
        long temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private void sortDES(int[] arr) {
        int temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private void sortLongDES(long[] arr) {
        long temp = arr[0];

        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }

    private boolean compare(Object[] arr, Object[] arr1) {
        if (arr != null && arr1 != null && arr.length == arr1.length) {
            for(int i = 0; i < arr.length; ++i) {
                Object o1 = arr[i];
                Object o2 = arr1[i];
                if (o1 != o2) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private int[] insert(int[] arr, int k) {
        int arrIndex = arr.length - 1;
        int tempIndex = arr.length;
        int[] tempArr = new int[tempIndex + 1];
        boolean inserted = false;

        for(int i = tempIndex; i >= 0; --i) {
            if (arrIndex > -1 && arr[arrIndex] > k) {
                tempArr[i] = arr[arrIndex--];
            } else if (!inserted) {
                tempArr[i] = k;
                inserted = true;
            } else {
                tempArr[i] = arr[arrIndex--];
            }
        }

        return tempArr;
    }
}
