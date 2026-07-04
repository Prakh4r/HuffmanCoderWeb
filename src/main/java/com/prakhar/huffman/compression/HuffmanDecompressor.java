package com.prakhar.huffman.compression;

import com.prakhar.huffman.io.BitInputStream;
import com.prakhar.huffman.io.HuffHeaderReader;
import com.prakhar.huffman.model.Header;
import com.prakhar.huffman.model.Node;
import com.prakhar.huffman.util.FileManager;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanDecompressor {
    public void decompress(String inputPath, String outputPath) throws IOException{
        try(
                FileInputStream fileIn = new FileInputStream(inputPath);
                DataInputStream dataIn = new DataInputStream(fileIn);
        ) {

            HuffHeaderReader reader = new HuffHeaderReader(dataIn);
            Header header = reader.readHeader();

            HuffmanTree huffmanTree = new HuffmanTree(header.getFrequencyMap());
            Node root = huffmanTree.buildTree();

            BitInputStream bitIn = new BitInputStream(dataIn);


            int headerSize = 4 + 4 + header.getFrequencyMap().size() * 6 + 1;
            long fileSize = Files.size(Path.of(inputPath));

            int compressedBytes = (int) (fileSize - headerSize);
            int totalValidBits = (compressedBytes - 1) * 8 + header.getValidBitsInLastByte();

            HuffmanDecoder decoder = new HuffmanDecoder();
            String decodedText = decoder.decode(bitIn, root, totalValidBits);

            FileManager fileManager = new FileManager();
            fileManager.writeFile(outputPath, decodedText);
        }
    }
}
