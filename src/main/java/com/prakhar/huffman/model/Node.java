package com.prakhar.huffman.model;

public class Node implements Comparable<Node>{
    private final Character ch;
    private final int frequency;
    private final Node left;
    private final Node right;

    public Node(Character ch, int frequency){
        this.ch = ch;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    public Node(int frequency, Node left, Node right) {
        this.ch = null;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    public Character getCharacter(){
        return this.ch;
    }
    public int getFrequency(){
        return this.frequency;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.frequency);
    }

    public boolean isLeaf(){
        if(this.left == null && this.right == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "Node{" +
                "ch=" + ch +
                ", frequency=" + frequency +
                '}';
    }
}
