package by.vladik;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Deserializer {
    public static Object deserializeFromFile(String path){
        File file = new File(path);
        Object object = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            object = mapper.readValue(file, Object.class);
        } catch (IOException e) {
            System.err.println("Не удалось считать данные из файла. Возможно, он содержит некорректные данные.");
        }
        return object;
    }
}
