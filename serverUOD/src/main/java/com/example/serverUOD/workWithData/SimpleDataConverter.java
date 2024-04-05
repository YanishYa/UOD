package com.example.serverUOD.workWithData;

import com.example.serverUOD.People;
import com.example.serverUOD.controllers.DataController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class SimpleDataConverter implements DataConverterInterface {
    String data;
    public SimpleDataConverter(){

    }

    @Override
    public List<Data> dataReturn() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson answer = gsonBuilder.create();
        List<Data> listOfData = answer.fromJson(data, LinkedList.class);
        this.data = data;
        return listOfData;
    }

}
