/*
Задание 1. (1 балл)
Создать класс, реализующий стандартный интерфейс Runnable. Переопределить метод run() в котором выводить на экран ID потока.
ID потока возвращает метод Thread.currentThread().getId();
Создать и запустить три объекта этого класса на выполнение в трёх потоках.
*/

package com.goncharuk.Task1;

public class Task1 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread1 = new Thread(new Run1());
            thread1.start();
        }
    }
}


