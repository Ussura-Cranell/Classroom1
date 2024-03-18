/*
Задание 3. (4 балла)
В одном МФЦ работают три окна, которые обслуживают три категории граждан: молодые, пожилые и бизнесмены. Окна работают без очередей (каждый гражданин является отдельным потоком).
Первое окно принимает любые категории граждан, второе - только вторую категорию, а третье - только третью. Гражданин идёт в случайное окно.
Если гражданин не может получить услугу из-за занятости окна или неверной категории, то он разгневанно уходит. Определить процент ушедших клиентов из каждой категории.
Подсказка: для определения “занятости окна” достаточно использовать булевый флаг.
*/

package com.goncharuk.Task3;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3 {
    private static final int TOTAL_CITIZENS = 100; // Общее количество граждан
    public static AtomicInteger angryYoung = new AtomicInteger(0);
    public static AtomicInteger angryElderly = new AtomicInteger(0);
    public static AtomicInteger angryBusinessmen = new AtomicInteger(0);

    public static ArrayList<Object> windows = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("\nЗапуск программы...\n");

        for (int i = 0; i < 3; i++) windows.add(new Object());

        Thread[] citizens = new Thread[TOTAL_CITIZENS];

        for (int i = 0; i < TOTAL_CITIZENS; i++) {
            citizens[i] = new Thread(new Citizen());
            citizens[i].start();
        }

        for (Thread citizen : citizens) {
            citizen.join();
        }

        System.out.println("\nПроцент ушедших молодых граждан: " + (angryYoung.get() * 100.0 / TOTAL_CITIZENS) + "%");
        System.out.println("Процент ушедших пожилых граждан: " + (angryElderly.get() * 100.0 / TOTAL_CITIZENS) + "%");
        System.out.println("Процент ушедших бизнесменов: " + (angryBusinessmen.get() * 100.0 / TOTAL_CITIZENS) + "%");

        System.out.println("\nПрограмма завершена.");
    }
}
