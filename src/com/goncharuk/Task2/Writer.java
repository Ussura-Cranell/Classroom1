package com.goncharuk.Task2;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

class Writer implements Runnable {
    private final Random random = new Random();
    private final CopyOnWriteArrayList<Integer> listOfNumbers;

    private int lastWriteValue = -1;

    Writer(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        while (listOfNumbers.size() < Task2.NUM_VALUES) {
            int writeValue = this.getNonRepeatValue();
            listOfNumbers.add(writeValue);
            System.out.printf("Запись -> %d\n", writeValue);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int getNonRepeatValue(){
        int value = random.nextInt(100);
        value = (this.lastWriteValue != value) ? value : value+1;
        this.lastWriteValue = value;
        return value;
    }
}