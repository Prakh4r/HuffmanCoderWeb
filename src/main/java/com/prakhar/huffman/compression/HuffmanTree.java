package com.prakhar.huffman.compression;

import com.prakhar.huffman.model.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private final Map<Character,Integer> frequencyMap;
    private Node root;

    public HuffmanTree(Map<Character,Integer> frequencyMap){
        this.frequencyMap = frequencyMap;
    }
    public Node buildTree(){
        if (frequencyMap.isEmpty()) {
            return null;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        if (pq.size() == 1) {
            /*root == null
                  ↓
            generateCodes() returns empty map
                  ↓
            encoder appends "null"
                  ↓
            'n' reaches BitOutputStream
                  ↓
            Exception
            */
            //change to handle the edge case of same character string
            Node onlyNode = pq.poll();
            root = new Node(onlyNode.getFrequency(), onlyNode, null);
            return root;
        }
        while(pq.size() > 1){
            Node leftChild = pq.poll();
            Node rightChild = pq.poll();
            pq.add(new Node(leftChild.getFrequency() + rightChild.getFrequency(), leftChild, rightChild));
        }
        root = pq.poll();
        return root;
    }
    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node node) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            System.out.println(node.getCharacter() + " : " + node.getFrequency());
        } else {
            System.out.println("* : " + node.getFrequency());
        }
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }

    private final Map<Character, String> huffmanCodes = new HashMap<>();

    public Map<Character, String> generateCodes() {
        huffmanCodes.clear();
        generateCodes(root, new StringBuilder());
        return huffmanCodes;
    }

    private void generateCodes(Node node, StringBuilder code) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            if (code.length() == 0) {
                huffmanCodes.put(node.getCharacter(), "0");
            } else {
                huffmanCodes.put(node.getCharacter(), code.toString());
            }
            return;
        }

        code.append('0');
        generateCodes(node.getLeft(), code);
        code.deleteCharAt(code.length() - 1);

        code.append('1');
        generateCodes(node.getRight(), code);
        code.deleteCharAt(code.length() - 1);
    }
}
