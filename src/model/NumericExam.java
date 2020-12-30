//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public class NumericExam {
    public NumericExam() {
    }

    public static int USCLN(int a, int b) {
        return b == 0 ? a : USCLN(b, a % b);
    }

    public static int BSCNN(int a, int b) {
        return a * b / USCLN(a, b);
    }

    public static int count(String string, String substring) {
        int count = 0;

        for(int idx = 0; (idx = string.indexOf(substring, idx)) != -1; ++count) {
            ++idx;
        }

        return count;
    }
}
