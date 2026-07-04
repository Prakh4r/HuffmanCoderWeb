package com.prakhar.huffman.controller;

import com.prakhar.huffman.exception.InvalidFileException;
import com.prakhar.huffman.service.HuffmanService;
import com.prakhar.huffman.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class HuffmanController {
    private final HuffmanService huffmanService;

    public HuffmanController(HuffmanService huffmanService){
        this.huffmanService = huffmanService;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(HuffmanController.class);

    @GetMapping("/hello")
    public String home(){
        return huffmanService.getWelcomeMessage();
    }
    //ResponseEntity - construction of an HTTP response.
    /*  | HTTP Method | Meaning                   |
        | ----------- | ------------------------- |
        | GET         | Give me something         |
        | POST        | I'm sending you something |
        | PUT         | Replace something         |
        | DELETE      | Remove something          |
    */
    //@RequestParam means Find the uploaded part named file and put it into this variable.

    @PostMapping("/compress")
    public ResponseEntity<byte[]> compress(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (file.isEmpty()) {
            logger.warn("Empty file uploaded.");
            throw new InvalidFileException("Uploaded file is empty.");
        }

        byte[] compressedData = huffmanService.compress(file);

        //Building of a header file so that our browser can understand what to do with those bytes

        /*ResponseEntity.ok() -> returns status code*/

        /*Content-Type -> since binary data, we use MediaType.APPLICATION_OCTET_STREAM
            It literally tells the browser: "This response contains arbitrary binary data."
        */

        /*
            Content-Disposition -> tells chrome what to do with those bytes
            Example - attachment; filename="compressed.huff"
            so chrome will download the file with those bytes
        */
        String downloadFileName = FileManager.getCompressedFileName(file.getOriginalFilename());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + downloadFileName + "\"")
                .body(compressedData);
    }
    @PostMapping("/decompress")
    public ResponseEntity<byte[]> decompress(
            @RequestParam("file") MultipartFile file
    ) throws IOException{

        if (file.isEmpty()) {
            logger.warn("Empty file uploaded.");
            throw new InvalidFileException("Uploaded file is empty.");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || !fileName.endsWith(".huff")) {
            logger.warn("Invalid file uploaded for decompression: {}", fileName);
            throw new InvalidFileException("Please upload a valid .huff file.");
        }

        byte[] decompressedData = huffmanService.decompress(file);

        String downloadFileName = FileManager.getDecompressedFileName(file.getOriginalFilename());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + downloadFileName + "\"")
                .body(decompressedData);
    }
}
