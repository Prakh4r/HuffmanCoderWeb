package com.prakhar.huffman.model;
import java.util.Map;

public class Header {
    private final int validBitsInLastByte;
    private final Map<Character,Integer> frequencyMap;

    public Header(int validBitsInLastByte, Map<Character,Integer> frequencyMap){
        this.frequencyMap = frequencyMap;
        this.validBitsInLastByte = validBitsInLastByte;
    }

    public int getValidBitsInLastByte(){
        return this.validBitsInLastByte;
    }
    public Map<Character,Integer> getFrequencyMap(){
        return this.frequencyMap;
    }
}
