package com.prakhar.huffman.compression;

import com.prakhar.huffman.io.BitInputStream;
import com.prakhar.huffman.model.Node;

import java.io.IOException;

public class HuffmanDecoder {
    public String decode(BitInputStream bitIn, Node root, int totalValidBits) throws IOException{
        StringBuilder decodedText = new StringBuilder();

        Node current = root;
        for(int i = 0; i < totalValidBits; i++) {
            int bit = bitIn.readBit();
            if (bit == 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }

            if (current.isLeaf()) {
                decodedText.append(current.getCharacter());
                current = root;
            }
        }

        return decodedText.toString();
    }
}
