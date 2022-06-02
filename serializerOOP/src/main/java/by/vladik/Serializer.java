package by.vladik;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Serializer {

    public static void serializeToFile(String path, List<?> list) {
        boolean flag = true;
        if (!list.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonList = null;
            try { 
                jsonList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            } catch (JsonProcessingException e) {
                System.err.println("Ошибка.");
            }
            File file = new File(path);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(jsonList);
            } catch (IOException e) {
                System.err.println("Не удалось записать данные в файл!");
                flag = false;
            }
            if (flag) {
                System.out.println("Данные успешно записаны в файл.");
            }
        }
        else {
            System.out.println("Список пуст.");
        }
    }

    public static String serialize(List<?> list) {
        String jsonList = null;
        if (!list.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                jsonList = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                System.err.println("Ошибка.");
            }
        }
        else {
            System.out.println("Список пуст.");
        }
        return jsonList;
    }

    public static 

}
