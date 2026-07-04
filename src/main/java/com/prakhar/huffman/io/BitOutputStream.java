package com.prakhar.huffman.io;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {
    private final OutputStream out;
    private int currentByte;
    private int numBitsFilled;

    public BitOutputStream(OutputStream out) {
        this.out = out;
        currentByte = 0;
        numBitsFilled = 0;
    }
    public void writeBit(int bit) throws IOException{
        if(bit == 0 || bit == 1){
            currentByte = currentByte << 1;
            currentByte = currentByte | bit;
            numBitsFilled++;

            if(numBitsFilled == 8){
                //Suppose the disk is full, or the file can't be written.
                //Hey OutputStream, here's one byte. You decide what to do with it.
                out.write(currentByte);
                currentByte = 0;
                numBitsFilled = 0;
            }
        }else{
            //If invalid input then this will throw error
            //For example writeBit(-2) || or any thing not equal to 0 & 1
            throw new IllegalArgumentException("Bit must be 0 or 1");
        }
    }
    public void flush() throws IOException{
        if (numBitsFilled == 0) {
            return;
        }

        currentByte = currentByte << (8 - numBitsFilled);
        out.write(currentByte);
        currentByte = 0;
        numBitsFilled = 0;
    }
    public void close() throws IOException{
        flush();
        out.close();
    }
}
