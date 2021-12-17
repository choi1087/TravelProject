package com.example.triptobuy.travel.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UploadController {

    @GetMapping(value = "/display")
    public ResponseEntity<Resource>display(@Param("filename")String filename){

        String path = "http://13.125.233.80";
        String folder = "";

        Resource resource = new FileSystemResource(path + filename);

        //파일이 존재하지 않으면 400 ERROR
        if (!resource.exists()) {
            return new ResponseEntity<Resource>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

        try {
            //file의 경로를 구함
            filePath = Paths.get(path + filename);
            //파일의 확장자명에 따라 달라지는 Content-Type, IMAGE/JPEG or IMAGE/PNG
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource,header, HttpStatus.OK);
    }
}
