package com.filehasher.filehasher.controller;

import com.filehasher.filehasher.service.FileHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Controller
public class FileController {

    private final FileHashingService fileHashingService;

    private static final List<String> ALLOWED_ALGORITHMS = Arrays.asList("MD5", "SHA-1", "SHA-256", "SHA-512");

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    @Autowired
    public FileController(FileHashingService fileHashingService) {
        this.fileHashingService = fileHashingService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("algorithm") String algorithm) {
        try {
            if (!ALLOWED_ALGORITHMS.contains(algorithm)) {
                return "Unsupported algorithm. Allowed algorithms are: " + ALLOWED_ALGORITHMS;
            }

            fileHashingService.saveFile(file, uploadDirectory);

            byte[] fileContent = file.getBytes();
            String hash = fileHashingService.calculateFileHash(fileContent, algorithm);
            return "Hash of uploaded file (" + algorithm + "): " + hash;
        } catch (IOException | NoSuchAlgorithmException e) {
            return "Error processing file";
        }
    }
}
