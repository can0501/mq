package com.example;

/**
 * @author 钟金灿
 * @since 2021/7/5
 */
public class Test2 {
    public static class Node {
        public Node next;
        public Integer value;

        public Node(Node node, Integer value) {
            this.next = node;
            this.value = value;
        }

        public Node() {

        }


    }

    public static Node ff(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = null;
        Node fast = head;
        while (fast != null) {
            Node t = fast.next;
            fast.next = slow;
            slow = fast;
            fast = t;

        }


        return slow;

    }


    public static void main(String[] args) {

        Node head = new Node();

        Node node1 = new Node(null, 1);
        Node node2 = new Node(null, 2);

        head = node1;
        node1.next = node2;


        Node tt = head;

        while (tt != null) {
            System.out.println(tt.value);
            tt = tt.next;
        }

//
        head = ff(head);
        Node tt2 = head;
        while (tt2 != null) {
            System.out.println(tt2.value);
            tt2 = tt2.next;
        }
//

    }

}
