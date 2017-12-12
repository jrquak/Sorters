package com.jordy.sorters;

/**
 * This class creates a student object.
 */

public class Student implements Comparable <Student>{

    private String group = "-";
    private final long studentNumber;
    private double grade;

    public Student(long studentNumber) {
        this.studentNumber = studentNumber;
    }
    public Student(final long studentNumber, final double grade)
    {
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public void setGroup(String newGroup){
        this.group = newGroup;
    }

    public String getGroup(){
        return this.group;
    }

    public double getGrade() {
        return this.grade;
    }

    /**
     * Compares this student with another.
     *
     * @param other other student
     * @return < 0 if it is smaller, 0 of they are both the same, 0 > it is bigger
     */
    @Override
    public int compareTo(Student other){
        if(this.grade != other.grade){
            return (((Double) this.grade).compareTo(other.grade));
        }
        else{
            return (((Long) this.studentNumber).compareTo(other.studentNumber));
        }
    }

    @Override
    public String toString(){
        return this.group + ";" + this.studentNumber + ";" + this.grade;
    }
}
