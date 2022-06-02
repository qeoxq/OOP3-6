package runner;

import adapter.Adapter;
import adapter.Encryptor;
import by.vladik.Deserializer;
import by.vladik.Serializer;
import by.vladik.Transformer;
import entity.*;
import singleton.SingletonScanner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    static final int ADD = 1;
    static final int EDIT = 2;
    static final int REMOVE = 3;
    static final int SHOW = 4;
    static final int SER = 5;
    static final int DESER = 6;
    static final int JSONXML = 7;
    static final int CRYPT = 8;
    static final int EXIT = 0;

    static Scanner sc = SingletonScanner.getInstance();
    private final static Encryptor encryptor = new Adapter();
    static ArrayList<Food> listOfFood = new ArrayList<>();

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {

        System.out.println("""
                ----------------------------
                            МЕНЮ
                ----------------------------
                (1) - Добавить еду
                (2) - Изменить еду
                (3) - Удалить еду
                (4) - Показать список
                (5) - Сериализация в JSON
                (6) - Десериализация из JSON
                (7) - JSON <-> XML
                (8) - Шифратор
                (0) - Выйти
                ----------------------------""");
        switch (inputChoice(0, 8)) {
            case ADD -> {
                addFood();
                showMenu();
            }
            case EDIT -> {
                editFood();
                showMenu();
            }
            case REMOVE -> {
                removeFood();
                showMenu();
            }
            case SHOW -> {
                showList();
                showMenu();
            }
            case SER -> {
                serializeList();
                showMenu();
            }
            case DESER -> {
                deserializeList();
                showMenu();
            }
            case JSONXML -> {
                convertMenu();
                showMenu();
            }
            case CRYPT -> {
                encrypt();
                showMenu();
            }
            case EXIT -> {
            }
        }
        sc.close();
    }

    private static void encrypt() {
        System.out.println(Arrays.toString(encryptor.encrypt(listOfFood)));
    }

    private static void convertMenu() {
        System.out.println("""
                Выберите действие:
                (1) - Конвертировать JSON в XML.
                (2) - Конвертировать XML в JSON.""");
        switch (inputChoice(1, 2)) {
            case 1 -> convertJSONtoXML();
            case 2 -> convertXMLtoJSON();
        }
    }

    private static void convertXMLtoJSON() {
        String XMLStr = null;
        try {
            XMLStr = Files.lines(Paths.get("file.xml")).reduce("", String::concat);
        } catch (IOException e) {
            System.out.println("error");
        }
        Transformer.transformToJSON(XMLStr);
    }

    private static void convertJSONtoXML() {
        String jsonStr = null;
        try {
            jsonStr = Files.lines(Paths.get("file.json")).reduce("", String::concat);
        } catch (IOException e) {
            System.out.println("error");
        }
        Transformer.transformToXML(jsonStr);
    }

    private static void deserializeList() {
        listOfFood.clear();
        List<LinkedHashMap<String, Object>> maps = (List<LinkedHashMap<String, Object>>) Deserializer.deserializeFromFile("file.json");
        for (LinkedHashMap<String, Object> map : maps ) {
            switch ((String) map.get("classID")) {
                case "Apple" ->
                        listOfFood.add(new Apple((Boolean) map.get("ripe"), (Integer) map.get("nutrition"), (Boolean) map.get("dried")));
                case "Banana" ->
                        listOfFood.add(new Banana((Boolean) map.get("ripe"), (Integer) map.get("nutrition"), (Boolean) map.get("dried")));
                case "Cucumber" ->
                        listOfFood.add(new Cucumber((Boolean) map.get("ripe"), (Integer) map.get("nutrition"), (Boolean) map.get("pickled")));
                case "Carrot" ->
                        listOfFood.add(new Carrot((Boolean) map.get("ripe"), (Integer) map.get("nutrition"), (Boolean) map.get("pickled")));
            }
        }
    }

    private static void serializeList() {
        Serializer.serializeToFile("file.json", listOfFood);
    }

    private static void showList() {
        if (!listOfFood.isEmpty()) {
            for (int i = 0; i < listOfFood.size(); i++) {
                System.out.println(i + ". " + listOfFood.get(i).toString());
            }
        } else {
            System.out.println("Список пуст.");
        }
    }

    private static void removeFood() {
        System.out.println("Введите индекс элемента списка, который хотите удалить");
        int index = inputChoice(0, listOfFood.size());
        listOfFood.remove(index);
    }

    public static void editFood () {
        System.out.println("Введите индекс элемента списка, который хотите изменить.");
        int index = inputChoice(0, listOfFood.size());
        if (listOfFood.get(index) instanceof Fruit) {
            System.out.println("Какой параметр хотите изменить?\n(1) - Спелость.\n(2) - Калорийность.\n(3) - Сушеность.");
            switch (inputChoice(1, 3)) {
                case 1 ->
                        listOfFood.get(index).setRipe(inputRipe());
                case 2 ->
                        listOfFood.get(index).setNutrition(inputNutrition());
                case 3 ->
                        ((Fruit) listOfFood.get(index)).setDried(inputDried());
            }
        } else {
            System.out.println("Какой параметр хотите изменить?\n(1) - Спелость.\n(2) - Калорийность.\n(3) - Маринованность.");
            switch (inputChoice(1, 3)) {
                case 1 ->
                        listOfFood.get(index).setRipe(inputRipe());
                case 2 ->
                        listOfFood.get(index).setNutrition(inputNutrition());
                case 3 ->
                        ((Vegetable) listOfFood.get(index)).setPickled(inputDried());
            }
        }
    }

    public static int inputChoice(int minValue, int maxValue) {
        int choice = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Введите целое число!");
                isIncorrect = true;
            }
            if (!isIncorrect && (choice < minValue || choice > maxValue)) {
                System.err.println("Введите число от " + minValue + " до " + maxValue + "!");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return choice;
    }

    public static void addFood() {
        System.out.println("Что вы хотите добавить?\n(1) - Фрукт.\n(2) - Овощь.");
        boolean ripe;
        int nutrition;
        switch (inputChoice(1, 2)) {
            case 1 -> {
                boolean dried;
                System.out.println("Что вы хотите добавить?\n(1) - Яблоко.\n(2) - Банан.");
                switch (inputChoice(1, 2)) {
                    case 1 -> {
                        ripe = inputRipe();
                        nutrition = inputNutrition();
                        dried = inputDried();
                        final var tempApple = Apple.builder()
                                .setRipe(ripe)
                                .setNutrition(nutrition)
                                .setDried(dried)
                                .build();
                        listOfFood.add(tempApple);
                    }
                    case 2 -> {
                        ripe = inputRipe();
                        nutrition = inputNutrition();
                        dried = inputDried();
                        Banana tempBanana = new Banana(ripe, nutrition, dried);
                        listOfFood.add(tempBanana);
                    }
                }
            }
            case 2 -> {
                boolean pickled;
                System.out.println("Что вы хотите добавить?\n(1) - Огурец.\n(2) - Морковь.");
                switch (inputChoice(1, 2)) {
                    case 1 -> {
                        ripe = inputRipe();
                        nutrition = inputNutrition();
                        pickled = inputPickled();
                        Cucumber tempCucumber = new Cucumber(ripe, nutrition, pickled);
                        listOfFood.add(tempCucumber);
                    }
                    case 2 -> {
                        ripe = inputRipe();
                        nutrition = inputNutrition();
                        pickled = inputPickled();
                        Carrot tempCarrot = new Carrot(ripe, nutrition, pickled);
                        listOfFood.add(tempCarrot);
                    }
                }
            }
        }
    }

    public static int inputNutrition() {
        System.out.println("Введите калорийность.");
        boolean isIncorrect;
        int nutrition = 0;
        do {
            isIncorrect = false;
            try {
                nutrition = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Введите целое число!");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return nutrition;
    }

    public static boolean inputRipe() {
        System.out.println("Фрукт/Овощь спелый?\n(1) - Да.\n(2) - Нет.");
        int choice = inputChoice(1, 2);
        return choice == 1;
    }

    public static boolean inputDried() {
        System.out.println("Фрукт сушеный?\n(1) - Да.\n(2) - Нет.");
        int choice = inputChoice(1, 2);
        return choice == 1;
    }

    public static boolean inputPickled() {
        System.out.println("Овощь маринованный?\n(1) - Да.\n(2) - Нет.");
        int choice = inputChoice(1, 2);
        return choice == 1;
    }

}