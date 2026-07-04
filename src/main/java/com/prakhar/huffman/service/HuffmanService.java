package com.prakhar.huffman.service;

import com.prakhar.huffman.compression.HuffmanCompressor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class HuffmanService {
    public String getWelcomeMessage() {
        return "Welcome to HuffmanCoder!";
    }
    public byte[] compress(MultipartFile file) throws IOException{
        Path inputFile = Files.createTempFile("input-", ".txt");
        Path outputFile = Files.createTempFile("output-", ".huff");

        return null;
    }
}
