package com.example.serverUOD.workWithData;

public class DataConverterFabric {
    public static DataConverterInterface getDataConverter(Converters converterInfo){
        if (converterInfo == Converters.Simple){
            return new SimpleDataConverter();
        }
        else return null;
    }
}
