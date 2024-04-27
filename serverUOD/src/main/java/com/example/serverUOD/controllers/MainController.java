package com.example.serverUOD.controllers;

import com.example.serverUOD.People;
import com.example.serverUOD.workWithData.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    String urlForPostFileForAllAnalize = "dsa";
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

    @PostMapping("/ServerApi")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model){


        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                String currentPath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\text.txt";
                file.transferTo(new File(currentPath));
                Gson gson = new Gson();
                JsonReader jsonReader = new JsonReader(new FileReader(currentPath));
                TypeToken<List<Data>> listDataTT = new TypeToken<>(){};
                String json = gson.fromJson(jsonReader, listDataTT).toString();

                URL url = new URL(urlForPostFileForAnalize);

                // Создаем объект HttpURLConnection и настраиваем его
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Записываем тело запроса в поток вывода
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(json);
                writer.flush();

                // Считываем ответ от сервера
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Выводим ответ от сервера
                System.out.println(response.toString());
                return "index.html";
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
