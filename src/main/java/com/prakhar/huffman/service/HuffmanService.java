package com.prakhar.huffman.service;

import com.prakhar.huffman.compression.HuffmanCompressor;
import com.prakhar.huffman.compression.HuffmanDecompressor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HuffmanService {

    private static final Logger logger =
            LoggerFactory.getLogger(HuffmanService.class);

    public String getWelcomeMessage() {
        return "Welcome to HuffmanCoder!";
    }
    public byte[] compress(MultipartFile file) throws IOException{
        Path inputFile = Files.createTempFile("input-", ".txt");
        Path outputFile = Files.createTempFile("output-", ".huff");

        try{
            logger.info("Compressing file: {}", file.getOriginalFilename());
            //converts or writes MultipartFile into inputFile so that our engine can understand
            file.transferTo(inputFile);


            //since compressor is a normal java Class spring does not create the object of it in its own
            HuffmanCompressor compressor = new HuffmanCompressor();
            compressor.compress(inputFile.toString(), outputFile.toString());


            /*  output.huff (Temp File)
                        │
                        ▼
                Files.readAllBytes(...)
                        │
                        ▼
                    byte[]
            */
            //HTTP responses don't send Java File objects. They send bytes over the network.
            logger.info("Compression completed successfully.");
            return Files.readAllBytes(outputFile);
        }finally {
            Files.deleteIfExists(inputFile);
            Files.deleteIfExists(outputFile);
        }
    }
    public byte[] decompress(MultipartFile file) throws IOException{
        Path inputFile = Files.createTempFile("input-", ".huff");
        Path outputFile = Files.createTempFile("output-", ".txt");

        try{
            logger.info("Decompressing file: {}", file.getOriginalFilename());
            file.transferTo(inputFile);

            HuffmanDecompressor decompressor = new HuffmanDecompressor();
            decompressor.decompress(inputFile.toString(), outputFile.toString());

            logger.info("Decompression completed successfully.");
            return Files.readAllBytes(outputFile);
        }finally {
            Files.deleteIfExists(inputFile);
            Files.deleteIfExists(outputFile);
        }
    }
}
