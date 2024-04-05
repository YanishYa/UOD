package com.example.serverUOD.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data")
@RestController
public class DataController {
    @GetMapping
    public String getPlotData(@RequestParam("file") String file){

        return "data";
    }
}
