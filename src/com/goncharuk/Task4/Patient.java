package com.goncharuk.Task4;

import java.util.concurrent.ThreadLocalRandom;

public class Patient implements Runnable {
    private final int id;

    public Patient(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Clinic.incrementQueueLength();
        System.out.println("Пациент " + id + " встал в очередь. Длина очереди: " + Clinic.queueLength.get());
        Clinic.updateMaxQueueLength();
        synchronized (Clinic.getTherapistLock()) {
            try {
                System.out.println("Пациент " + id + " начал осмотр у терапевта.");
                Thread.sleep(ThreadLocalRandom.current().nextInt(300, 600));
                System.out.println("Пациент " + id + " закончил осмотр у терапевта.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Clinic.getMriLock()) {
                try {
                    System.out.println("Пациент " + id + " начал осмотр на МРТ.");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(300, 600));
                    System.out.println("Пациент " + id + " закончил осмотр на МРТ.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Clinic.decrementQueueLength();
        System.out.println("Пациент " + id + " покинул клинику. Длина очереди: " + Clinic.queueLength.get());
    }
}