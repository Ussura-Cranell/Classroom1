package com.goncharuk.Task4;

import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("\nЗапуск программы...\n");

        Thread[] patients = new Thread[Clinic.MAX_PATIENTS];
        for (int i = 0; i < Clinic.MAX_PATIENTS; i++) {
            patients[i] = new Thread(new Patient(i));
            patients[i].start();
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 601));
        }
        for (Thread patient : patients) {
            patient.join();
        }
        System.out.println("\nМаксимальная длина очереди: " + Clinic.maxQueueLength.get());

        System.out.println("\nПрограмма завершена.");
    }
}








