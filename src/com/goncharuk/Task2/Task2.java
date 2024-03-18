/*
Задание 2. (2 балла)
Создать два класса, реализующих стандартный интерфейс Runnable.
Полем классов будет переменная типа CopyOnWriteArrayList<int> listOfNumbers.
Первый класс будет читать из переменной и выводить на экран, а второй класс будет в неё записывать числа.
Сделать таким образом, чтобы чтение и запись выполнялись с небольшой задержкой.
*/

package com.goncharuk.Task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Task2 {

    final public static int NUM_VALUES = 30; // кол-во значений для считывания и записи

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nЗапуск программы...\n");

        CopyOnWriteArrayList<Integer> listOfNumbers = new CopyOnWriteArrayList<>();
        Writer writer = new Writer(listOfNumbers);
        Reader reader = new Reader(listOfNumbers);

        Thread writerThread = new Thread(writer);
        Thread readerThread = new Thread(reader);

        writerThread.start();
        readerThread.start();

        writerThread.join();

        Thread.sleep(250);
        readerThread.join();

        System.out.printf("\nlistOfNumbers : %s\n", List.of(listOfNumbers));
        System.out.println("\nПрограмма завершена.");
    }
}