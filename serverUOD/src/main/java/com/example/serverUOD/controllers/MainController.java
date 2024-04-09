package com.example.serverUOD.controllers;

import com.example.serverUOD.People;
import com.example.serverUOD.workWithData.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;

/*
@RequestMapping("/ServerApi")*/
@Controller
public class MainController {
    String urlForPostFileForAnalize = "asd";
    String urlForPostFileForAllAnalize = "dsa";
    @GetMapping
    public String main(){

        System.out.println(System.getProperty("user.dir"));
        return "index.html";
    }

    @PostMapping("/ServerApi")
    public @ResponseBody RedirectView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        RedirectView redirectView = new RedirectView(urlForPostFileForAnalize);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String currentPath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\" + file.getOriginalFilename();
                file.transferTo(new File(currentPath));
                Gson gson = new Gson();
                JsonReader jsonReader = new JsonReader(new FileReader(currentPath));
                TypeToken<List<Data>> listDataTT = new TypeToken<>(){};
                redirectAttributes.addAttribute("file", gson.fromJson(jsonReader, listDataTT).toString());
                System.out.println("Sucksess");
                return redirectView;
            } catch (Exception e) {
                return redirectView;
                /*return "Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage();*/
            }
        } else {
            /*return "Вам не удалось загрузить " + file.getOriginalFilename() + " потому что файл пустой.";*/
            return redirectView;
        }
    }
    @GetMapping("/asd")
    public String asd(@RequestParam("file") String str){
        System.out.println(str);
        return "index.html";
    }
    @GetMapping("/ServerAnalizedData")
    public String getAnalizedDataByNN(@RequestParam("file") String str){
        return "index.html";
    }
    @GetMapping("/ServerFullAnalizedData")
    public String getFullAnalizedDataByNN(@RequestParam("file") String str){
        return "index.html";
    }
}
