package com.prakhar.huffman.io;
import com.prakhar.huffman.model.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffHeaderReader {
    private final DataInputStream in;

    public HuffHeaderReader(DataInputStream in){
        this.in = in;
    }

    public Header readHeader() throws IOException {
        byte[] magic = new byte[4];
        in.readFully(magic);
        String magicString = new String(magic);
        if(!magicString.equals("HUFF")) {
            throw new IOException("Not a Huffman file !!");
        }

        int uniqueCharacters = in.readInt();

        Map<Character,Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < uniqueCharacters; i++){
            char ch = in.readChar();
            int frequency = in.readInt();
            frequencyMap.put(ch, frequency);
        }

        int validBitsInLastByte = in.readByte();

        return new Header(validBitsInLastByte, frequencyMap);
    }
}
