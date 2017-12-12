package com.jordy.sorters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A holder for the students
 */

public class StudentHolder {

    private Node<Group> firstGroup = null;

    public void add(final Student student) {
        if (this.firstGroup == null) {
            this.firstGroup = makeHeadGroupNode(student);
        } else {
            int comparison = this.firstGroup.element.name.compareTo(student.getGroup());
            if (comparison > 0) {
                this.firstGroup = addBetween(null, this.firstGroup, student);
            } else if (comparison == 0) {
                addStudent(this.firstGroup, student);
            } else {
                if (this.firstGroup.next == null) {
                    this.firstGroup.next = addBetween(this.firstGroup, null, student);
                    return;
                }
                Node<Group> prevGroup = this.firstGroup;
                Node<Group> nextGroup = this.firstGroup.next;
                while (nextGroup != null) {
                    comparison = nextGroup.element.name.compareTo(student.getGroup());
                    if (comparison == 0) {
                        addStudent(nextGroup, student);
                        return;
                    } else if (comparison > 0) {
                        addBetween(prevGroup, nextGroup, student);
                        return;
                    }
                    prevGroup = nextGroup;
                    nextGroup = nextGroup.next;
                }
                prevGroup.next = makeHeadGroupNode(student);
            }
        }
    }

    /**
     * Adds all the students to an Collection of students.
     *
     * @param students
     */
    public void addAll(Collection<Student> students) {
        for (Student student : students) {
            add(student);
        }
    }

    /**
     * Adds all the student to a Student array.
     *
     * @param students
     */
    public void addAll(Student[] students) {
        for (Student student : students) {
            add(student);
        }
    }

    public List<Student> getStudents() {
        final List<Student> list = new ArrayList<>();
        Node<Group> nextGroup = this.firstGroup;
        while (nextGroup != null) {
            Node<Student> nextStudent = nextGroup.element.firstStudent;
            while (nextStudent != null) {
                list.add(nextStudent.element);
                nextStudent = nextStudent.next;
            }
            nextGroup = nextGroup.next;
        }
        return list;
    }

    private Node<Group> makeHeadGroupNode(final Student student) {
        return new Node<>(new Group(new Node<>(student, null), student.getGroup()), null);
    }

    private void addStudent(final Node<Group> group, final Student student) {
        if (0 <= group.element.firstStudent.element.compareTo(student)) {
            final Node<Student> secondStudent = group.element.firstStudent;
            group.element.firstStudent = new Node<>(student, secondStudent);
        } else {
            Node<Student> prevStudent = group.element.firstStudent;
            Node<Student> nextStudent = group.element.firstStudent.next;
            while (nextStudent != null) {
                if (0 <= nextStudent.element.compareTo(student)) {
                    prevStudent.next = new Node<>(student, nextStudent);
                    return;// escape the while loop
                }
                prevStudent = prevStudent.next;
                nextStudent = nextStudent.next;
            }
            //element is larger than the others.
            prevStudent.next = new Node<>(student, null);
        }
    }

    /**
     * Adds a student between the other students
     *
     * @param leftNode
     * @param rightNode
     * @param student
     * @return
     */
    private Node<Group> addBetween(final Node<Group> leftNode, final Node<Group> rightNode, final Student student) {
        final Node<Group> newNode = addBefore(rightNode, student);
        if (leftNode != null) {
            leftNode.next = newNode;
        }
        return newNode;
    }

    /**
     * Add a student before all the other students
     *
     * @param node
     * @param student
     * @return
     */
    private Node<Group> addBefore(final Node<Group> node, final Student student) {
        return new Node<>(new Group(new Node<>(student, null), student.getGroup()), node);
    }

    private static class Node<T extends Object> {

        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    private static class Group {

        Node<Student> firstStudent;
        String name;

        public Group(Node<Student> firstStudent, String name) {
            this.firstStudent = firstStudent;
            this.name = name;
        }
    }
}
