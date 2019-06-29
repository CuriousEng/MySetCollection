package com.company;

import java.io.*;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //################### проверим клонирование #################################
        /*MySet<String> oldSet = new MySet<>();
        oldSet.add("привет!");
        oldSet.add("Проверка клонирования!");
        MySet<String> newSet = (MySet<String>) oldSet.clone();
        System.out.println(newSet.contains("привет!"));
        System.out.println(newSet.contains("Проверка клонирования!"));*/
        // ###################### проверка сериализации ##############################
        HashSet<String> hashSet = new HashSet<>(); //начальные данные
        hashSet.add("ddd");
        hashSet.add("rrrr");

        MySet amigoSet = new MySet(hashSet);  //куда сериализуем
        //сериализация
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(amigoSet);
        objectOutputStream.close();
        //десериализация
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        MySet amigoSet1 = (MySet)  objectInputStream.readObject();

        System.out.println(amigoSet.equals(amigoSet1));  //проверка равенства
        System.out.println(amigoSet);  //первый объект
        System.out.println("________");
        System.out.println(amigoSet1);  //второй объект
    }
}
