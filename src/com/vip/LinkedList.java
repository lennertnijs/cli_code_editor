package com.vip;

import java.util.Objects;

public final class LinkedList {

    private Node head = null;
    private Node tail = null;

    public LinkedList(){

    }

    public LinkedList(Node head){
        this.head = head;
        this.tail = head;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public int length(){
        int count = 0;
        if(head == null){
            return count;
        }
        Node current  = head;
        while(current.hasNext()){
            current = current.next();
            count++;
        }
        return count;

    }

    public Node getHead(){
        return head;
    }

    public void setHead(Node head){
        this.head = head;
    }

    public void add(Node n){
        if(head == null){
            this.head = n;
            this.tail = n;
            return;
        }
        this.tail.setNext(n);
        this.tail = n;
    }

    public void insertAtIndex(int index, char c){
        Objects.requireNonNull(head);
        Node current = head;
        while(current.hasNext()){
            index = index - 1;
            current = current.next();
            if(index == 1){
                Node next = current.next();
                Node newNode = new Node(c);
                current.setNext(newNode);
                newNode.setNext(next);
            }
        }
    }
}
