package com.jordy.sorters;

/**
 * Regulates the creation of students and gives studentnumbers to the students.
 */

public class StudentFactory {

    private long highestStudentNumber = 50080000l;

    public Student makeStudent(final double grade)
    {
        this.highestStudentNumber++;
        return new Student(this.highestStudentNumber, grade);
    }
}
