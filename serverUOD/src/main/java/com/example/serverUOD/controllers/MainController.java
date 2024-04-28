package com.example.serverUOD.controllers;

import com.example.serverUOD.People;
import com.example.serverUOD.workWithData.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
@RequestMapping("/ServerApi")*/
@Controller
public class MainController {
    String urlForPostFileForAnalize = "http://127.0.0.1:5000/predict";
    String urlForPostFileForAllAnalize = "http://127.0.0.1:5000/predictch";
    @GetMapping
    public String main(Model model){
        String currentPath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\text.txt";
        Gson gson = new Gson();
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(currentPath));
            TypeToken<List<Data>> listDataTT = new TypeToken<>(){};
            String json = gson.fromJson(jsonReader, listDataTT).toString();
            model.addAttribute("json", json);
            System.out.println("atribute added");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "index.html";
    }

    @PostMapping("/ServerApi/getByOne")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model){


        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                String currentPath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\test.txt";
                file.transferTo(new File(currentPath));
                Gson gson = new Gson();
                BufferedReader r = new BufferedReader(new FileReader(currentPath));
                String json = "";
                String line = "";
                while ((line = r.readLine()) != null){
                    json += line;
                }


                URL url = new URL(urlForPostFileForAnalize);

                // Создаем объект HttpURLConnection и настраиваем его
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                // Записываем тело запроса в поток вывода
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(json);
                writer.flush();
                System.out.println(connection.getResponseMessage());
                // Считываем ответ от сервера
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                line = "";
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println(response.toString().replace("\\", ""));
                // Выводим ответ от сервера
                return response.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "index.html";
                /*return "Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage();*/
            }
        } else {
            System.out.println("Вам не удалось загрузить");
            /*return "Вам не удалось загрузить " + file.getOriginalFilename() + " потому что файл пустой.";*/
            return "index.html";
        }
    }
    @PostMapping("/ServerApi/getAll")
    public String fileUploadAll(@RequestParam("file") MultipartFile file, Model model){


        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                String currentPath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\test.txt";
                file.transferTo(new File(currentPath));
                Gson gson = new Gson();
                BufferedReader r = new BufferedReader(new FileReader(currentPath));
                String json = "";
                String line = "";
                while ((line = r.readLine()) != null){
                    json += line;
                }


                URL url = new URL(urlForPostFileForAllAnalize);

                // Создаем объект HttpURLConnection и настраиваем его
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                // Записываем тело запроса в поток вывода
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(json);
                writer.flush();
                System.out.println(connection.getResponseMessage());
                // Считываем ответ от сервера
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                line = "";
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println(response.toString().replace("\\", ""));
                // Выводим ответ от сервера
                return response.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "index.html";
                /*return "Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage();*/
            }
        } else {
            System.out.println("Вам не удалось загрузить");
            /*return "Вам не удалось загрузить " + file.getOriginalFilename() + " потому что файл пустой.";*/
            return "index.html";
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

    @PostMapping("/lol")
    public String getHello(){
        return "hi";
    }
}
