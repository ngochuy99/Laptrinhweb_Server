//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.io.Serializable;

public class Answer implements Serializable {
    static final long serialVersionUID = 2L;
    Student student;
    Object[] answers = null;
    boolean[] isRights;
    String[] viewString;
    boolean alreadyRegistration = false;
    StringExam[] questions = null;

    public boolean isAlreadyRegistration() {
        return this.alreadyRegistration;
    }

    public void setAlreadyRegistration(boolean alreadyRegistration) {
        this.alreadyRegistration = alreadyRegistration;
    }

    public String[] getViewString() {
        return this.viewString;
    }

    public void setViewString(String[] result) {
        this.viewString = result;
    }

    public StringExam[] getQuestions() {
        return this.questions;
    }

    public Object[] getAnswers() {
        return this.answers;
    }

    public boolean[] getIsRights() {
        return this.isRights;
    }

    public Answer() {
        this.answers = new Object[5];
        this.questions = new StringExam[5];
        this.isRights = new boolean[5];
        this.viewString = new String[5];
    }

    public void setQuestions(StringExam[] questions) {
        this.questions = questions;
    }

    public void setAnswers(Object[] answers) {
        this.answers = answers;
    }

    public void setIsRights(boolean[] isRights) {
        this.isRights = isRights;
    }

    public Answer(Student student) {
        this.student = student;
        this.answers = new Object[5];
        this.questions = new StringExam[5];
        this.isRights = new boolean[5];
        this.viewString = new String[5];
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean[] getIsRight() {
        return this.isRights;
    }

    public boolean updateAnswer(int code, Object answer, boolean isRight) {
        this.answers[code] = answer;
        this.isRights[code] = isRight;
        if (this.viewString == null) {
            this.viewString = new String[5];
        }

        if (isRight) {
            this.viewString[code] = "Đúng";
        } else {
            this.viewString[code] = "Sai";
        }

        return this.isRights[code];
    }

    public boolean isMyAnswer(String maSV) {
        if (this.student != null && this.student.getMaSV() != null) {
            return this.student.getMaSV().equalsIgnoreCase(maSV);
        } else {
            System.err.println("Chua co sinh vien nay !");
            return false;
        }
    }
}
