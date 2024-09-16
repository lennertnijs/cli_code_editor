package com.vip;

import java.util.Objects;

public final class Node {

    private final char character;
    private Node next;

    public Node(char c){
        this.character = c;
    }

    public char getCharacter(){
        return character;
    }

    public boolean hasNext(){
        return next != null;
    }

    public Node next(){
        return next;
    }

    public void setNext(Node next){
        this.next = Objects.requireNonNull(next);
    }
}
