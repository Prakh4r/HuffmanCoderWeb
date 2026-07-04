package com.prakhar.huffman.controller;

import com.prakhar.huffman.service.HuffmanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HuffmanController {
    private final HuffmanService huffmanService;

    public HuffmanController(HuffmanService huffmanService){
        this.huffmanService = huffmanService;
    }

    @GetMapping("/hello")
    public String home(){
        return huffmanService.getWelcomeMessage();
    }
}
