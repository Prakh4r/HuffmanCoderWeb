package com.prakhar.huffman.util;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter {
    public Map<Character,Integer> count(String text){
        Map<Character,Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch,0) + 1);
        }
        return frequencyMap;
    }
}
