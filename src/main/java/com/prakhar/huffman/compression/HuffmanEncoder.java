package com.prakhar.huffman.compression;

import java.util.Map;

public class HuffmanEncoder {
    public String encode(String text, Map<Character,String> codes){
        StringBuilder encodedBits = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            encodedBits.append(codes.get(ch));
        }
        return encodedBits.toString();
    }
}
