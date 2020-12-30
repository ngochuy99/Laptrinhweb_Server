//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.util.BitSet;
import java.util.Random;
import java.util.StringTokenizer;

public class StringExam {
    private String stringOrg = null;
    private int code;
    private int numericAnswer = 0;
    private String stringAnswer = null;
    private char charAnswer;

    public char getCharAnswer() {
        return this.charAnswer;
    }

    public void setCharAnswer(char charAnswer) {
        this.charAnswer = charAnswer;
    }

    public StringExam() {
    }

    public StringExam(int code, String strOrg) {
        this.code = code;
        this.stringOrg = strOrg;
    }

    public void setNumericAnswer(int numericAnswer) {
        this.numericAnswer = numericAnswer;
    }

    public void setStringAnswer(String stringAnswer) {
        this.stringAnswer = stringAnswer;
    }

    public int getNumericAnswer() {
        return this.numericAnswer;
    }

    public String getStringAnswer() {
        return this.stringAnswer;
    }

    public String getStringOrg() {
        return this.stringOrg;
    }

    public int getCode() {
        return this.code;
    }

    public void setStringOrg(String stringOrg) {
        this.stringOrg = stringOrg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isRightAnswer() {
        if (this.stringOrg == null) {
            System.err.println("Issue with original String");
            return false;
        } else {
            switch(this.code) {
                case 1:
                    return this.stringOrg.length() == this.numericAnswer;
                case 2:
                    return this.reverse().equals(this.stringAnswer);
                case 3:
                    return this.stringOrg.equals(this.stringAnswer);
                case 4:
                    return this.countSpecialChar() == this.numericAnswer;
                case 5:
                    return this.string2numeric() == this.numericAnswer;
                case 6:
                    String tmp = "";
                    int i = 0;

                    for(; i < this.stringOrg.length(); ++i) {
                        if (tmp.indexOf(this.stringOrg.charAt(i)) == -1) {
                            tmp = tmp + this.stringOrg.charAt(i);
                        }
                    }

                    return this.numericAnswer == tmp.length() && this.stringAnswer.equalsIgnoreCase(tmp);
                case 7:
                case 10:
                default:
                    return false;
                case 8:
                    return this.stringAnswer.equals(this.chuanHoa(this.stringOrg));
                case 9:
                    return this.stringOrg.split(" ").length == this.numericAnswer && this.stringAnswer.equals(this.reverseEachWordInString(this.stringOrg));
                case 11:
                    return this.charAnswer == this.get2ndMostFreq(this.stringOrg);
            }
        }
    }

    public void stringGenerate(int code) {
        this.code = code;
        switch(this.code) {
            case 1:
                this.stringOrg = this.randomAlphaString((new Random()).nextInt(10) + 10);
                break;
            case 2:
                this.stringOrg = this.randomAlphaString((new Random()).nextInt(10) + 10);
                break;
            case 3:
                this.stringOrg = this.randomAlphaString((new Random()).nextInt(10) + 10);
                break;
            case 4:
                this.stringOrg = this.randomSpecialString((new Random()).nextInt(10) + 10);
                break;
            case 5:
                this.stringOrg = this.randomNumericString((new Random()).nextInt(9) + 1);
                break;
            case 6:
                this.stringOrg = this.randomAlphaString((new Random()).nextInt(50) + 52);
            case 7:
            default:
                break;
            case 8:
                this.stringOrg = this.randomAlphaSpaceString((new Random()).nextInt(10) + 10);
                break;
            case 9:
                this.stringOrg = this.randomAlphaSpaceString((new Random()).nextInt(10) + 10);
                break;
            case 10:
                this.stringOrg = this.randomAlphaSpaceString((new Random()).nextInt(10) + 20);
                break;
            case 11:
                this.stringOrg = this.randomAlphaString((new Random()).nextInt(10) + 20);
                break;
            case 12:
                this.stringOrg = this.randomAlphaSpaceString((new Random()).nextInt(10) + 10);
        }

    }

    private String randomAlphaString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            int character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
            builder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
        }

        return builder.toString();
    }

    private String randomAlphaSpaceString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            for(int i = 0; i < (new Random()).nextInt(7) + 1; ++i) {
                int character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                builder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
            }

            if (count != 0) {
                builder.append(" ");
            }
        }

        return builder.toString().trim();
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

        System.out.println(builder);
        return builder.toString();
    }

    private String randomSpecialString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            int choice = (new Random()).nextInt(5) + 5;
            int character;
            if (choice % 2 == 0) {
                character = (int)(Math.random() * (double)"~!@#$%^&*()><-`/".length());
                builder.append("~!@#$%^&*()><-`/".charAt(character));
            } else {
                character = (int)(Math.random() * (double)"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".length());
                builder.append("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZ".charAt(character));
            }
        }

        return builder.toString();
    }

    private String randomSpaceNumericString(int count) {
        StringBuilder builder = new StringBuilder();

        while(count-- != 0) {
            builder.append(ExamCode.STRING_NUMERIC[(int)(Math.random() * (double)ExamCode.STRING_NUMERIC.length)]);
        }

        System.out.println(builder);
        return builder.toString().trim();
    }

    public int countSubstring(String string, String substring) {
        int count = 0;

        for(int idx = 0; (idx = string.indexOf(substring, idx)) != -1; ++count) {
            ++idx;
        }

        return count;
    }

    private int countSpecialChar() {
        return 0;
    }

    private String reverse() {
        String tmp = "";

        for(int i = this.stringOrg.length() - 1; i >= 0; --i) {
            tmp = tmp + this.stringOrg.substring(i, i + 1);
        }

        return tmp;
    }

    private String upperLower() {
        return null;
    }

    private int string2numeric() {
        return 0;
    }

    public String chuyenInHoa(String str) {
        String s = str.substring(0, 1);
        String strOutput = str.replaceFirst(s, s.toUpperCase());
        return strOutput;
    }

    public String chuanHoa(String strInput) {
        String strOutput = "";
        StringTokenizer strToken = new StringTokenizer(strInput, " ,\t,\r");

        for(strOutput = strOutput + "" + this.chuyenInHoa(strToken.nextToken()); strToken.hasMoreTokens(); strOutput = strOutput + " " + this.chuyenInHoa(strToken.nextToken())) {
        }

        return strOutput;
    }

    public String reverseEachWordInString(String str1) {
        String[] each_words = str1.split(" ");
        String revString = "";

        for(int i = 0; i < each_words.length; ++i) {
            String word = each_words[i];
            String reverseWord = "";

            for(int j = word.length() - 1; j >= 0; --j) {
                reverseWord = reverseWord + word.charAt(j);
            }

            revString = revString + reverseWord + " ";
        }

        return revString.trim();
    }

    public char get2ndMostFreq(String str1) {
        int[] ctr = new int[256];

        int i;
        for(i = 0; i < str1.length(); ++i) {
            ++ctr[str1.charAt(i)];
        }

        int ctr_first = 0;
        int ctr_second = 0;

        for(i = 0; i < 256; ++i) {
            if (ctr[i] > ctr[ctr_first]) {
                ctr_second = ctr_first;
                ctr_first = i;
            } else if (ctr[i] > ctr[ctr_second] && ctr[i] != ctr[ctr_first]) {
                ctr_second = i;
            }
        }

        return (char)ctr_second;
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

    public static void main(String[] args) {
        StringExam test = new StringExam();
        System.out.println(test.countPrimes(9999));
        System.out.println("11000;" + "dsjfkhs14".charAt(8) % 2);
    }
}
