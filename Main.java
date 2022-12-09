package com.company;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("snowy");
        arrayList.add("white");
        arrayList.add("winter");
        arrayList.add("cold");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("Меню:");
        System.out.println("1. Добавление объекта");
        System.out.println("2. Удаление объекта");
        System.out.println("3. Поиск одинаковых элементов с подсчётом совпадений");
        System.out.println("4. Выгрузка в xml-файл");
        System.out.println("5. Реверс всех строк, входящих в коллекцию");
        System.out.println("6. Статистика по всем символам, содержащимся в строках коллекции");
        System.out.println("7. Поиск подстроки в строках коллекции");
        System.out.println("8. Инициализация листа по текстовому файлу и вывод содержимого коллекции на экран");
        System.out.println("9. Посчитать длины строк, входящих в коллекцию, и вывести на экран");
        System.out.println("10. Завершить");

        while (true) {
            System.out.println("Выберите действие: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addObject(arrayList);
                    break;
                case 2:
                    deleteObject(arrayList);
                    break;
                case 3:
                    findEqualObjects(arrayList);
                    break;
                case 4:
                    inXml(arrayList);
                    break;
                case 5:
                    reverse(arrayList);
                    break;
                case 6:
                    statistics(arrayList);
                    break;
                case 7:
                    findSubstring(arrayList);
                    break;
                case 8:
                    textFile(arrayList);
                    break;
                case 9:
                    stringLength(arrayList);
                    break;
                default:
                    return;
            }

        }
    }

    public static void addObject(ArrayList<String> arrayList) {
        System.out.println("Введите строку для добавления: ");
        Scanner in = new Scanner(System.in);
        arrayList.add(in.nextLine());
        System.out.println(arrayList);
    }

    public static void deleteObject(ArrayList<String> arrayList) {
        System.out.println("Введите номер элемента для удаления: ");
        Scanner in = new Scanner(System.in);
        arrayList.remove(in.nextInt() - 1);
        System.out.println(arrayList);
    }

    public static void inXml(ArrayList<String> arrayList) {
        ConvertToXml object = new ConvertToXml(arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3), arrayList.get(4));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./arrayList.xml"));
            XMLEncoder encoder = new XMLEncoder(fileOutputStream);
            encoder.writeObject(object);
            encoder.close();
            fileOutputStream.close();
            System.out.println("Succeed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findEqualObjects(ArrayList<String> arrayList) {
        int i = 1;
        int numberOfEqualElements = 0;
        for (String string : arrayList) {
            for (int j = i; j < arrayList.size(); ++j) {
                String st = arrayList.get(j);
                if (string.equals(st)) {
                    numberOfEqualElements++;
                }
            }
            i++;
        }
        System.out.println(arrayList);
        System.out.println("Количество повторяющихся элементов: " + numberOfEqualElements);
    }

    public static void reverse(ArrayList<String> arrayList) {
        Collections.reverse(arrayList);
        System.out.println(arrayList);
    }

    public static void statistics(ArrayList<String> arrayList) {
        String arrayListString = arrayList.toString();
        Map<Character, Integer> map = new TreeMap<>();
        for (char c : arrayListString.toCharArray()) {
            map.put(c, (map.containsKey(c))?map.get(c) + 1 : 1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char c: map.keySet()) {
            stringBuilder.append(c);
        }

        System.out.println("Имеются символы \""+stringBuilder.toString()+"\"");
        for (char c:map.keySet()) {
            System.out.println("Количество \'" + c + "\'= " + map.get(c));
        }
    }

    public static void findSubstring(ArrayList<String> arrayList) {
        System.out.println("Введите подстроку для поиска: ");
        Scanner in = new Scanner(System.in);
        String substring = in.nextLine();

        for (String string : arrayList) {
            if (string.contains(substring)) {
                System.out.println("Подстрока найдена в строке " + string);
            }
        }
    }

    public static void textFile(ArrayList<String> arrayList) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C:\\Users\\Mi\\Pism\\Laba 7\\text.txt"));
        arrayList.add(in.nextLine());
        System.out.println(arrayList);
    }

    public static void stringLength(ArrayList<String> arrayList) {
        for (String string : arrayList) {
            System.out.println("Длина строки " + string + " равна " + string.length());
        }
    }

}
