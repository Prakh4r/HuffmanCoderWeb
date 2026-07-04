package com.prakhar.huffman.io;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStream {
    private final InputStream in;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStream(InputStream in){
        this.in = in;
        currentByte = 0;
        numBitsRemaining = 0;
    }

    public int readBit() throws IOException{
        if(numBitsRemaining == 0){
            currentByte = in.read();
            if(currentByte == -1){
                return -1;
            }else{
                numBitsRemaining = 8;
            }
        }
        int digit = (currentByte >> (numBitsRemaining - 1)) & 1;
        numBitsRemaining--;
        return digit;
    }
}
