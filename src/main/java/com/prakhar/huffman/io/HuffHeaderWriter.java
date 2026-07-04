package com.prakhar.huffman.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class HuffHeaderWriter {
    private final DataOutputStream out;
    private static final String MAGIC = "HUFF";

    public HuffHeaderWriter(DataOutputStream out){
        this.out = out;
    }

    public void writeHeader(Map<Character,Integer> frequencyMap, int validBitsInLastByte) throws IOException {
        out.writeBytes(MAGIC);
        out.writeInt(frequencyMap.size());


        for(Map.Entry<Character,Integer> entry : frequencyMap.entrySet()){
            out.writeChar(entry.getKey());
            out.writeInt(entry.getValue());
        }

        out.writeByte(validBitsInLastByte);

    }
}
