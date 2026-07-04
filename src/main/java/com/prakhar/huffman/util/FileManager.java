package com.prakhar.huffman.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public String readFile(String path) throws IOException{
        return Files.readString(Path.of(path));
    }
    public void writeFile(String path, String text) throws IOException{
        Files.writeString(Path.of(path),text);
        return;
    }
    public static String getCompressedFileName(String originalFileName){
        String downloadFileName;

        if (originalFileName == null || originalFileName.isBlank()) {
            downloadFileName = "compressed.huff";
        } else if (originalFileName.lastIndexOf('.') != -1) {
            downloadFileName = originalFileName.substring(
                    0,
                    originalFileName.lastIndexOf('.')
            ) + ".huff";
        } else {
            downloadFileName = originalFileName + ".huff";
        }
        return downloadFileName;
    }
    public static String getDecompressedFileName(String originalFileName){
        String downloadFileName;

        if (originalFileName == null || originalFileName.isBlank()) {
            downloadFileName = "decompressed.txt";
        } else if (originalFileName.lastIndexOf('.') != -1) {
            downloadFileName = originalFileName.substring(
                    0,
                    originalFileName.lastIndexOf('.')
            ) + ".txt";
        } else {
            downloadFileName = originalFileName + ".txt";
        }
        return downloadFileName;
    }
}
