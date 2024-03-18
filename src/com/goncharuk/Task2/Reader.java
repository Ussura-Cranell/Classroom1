package com.goncharuk.Task2;

import java.util.concurrent.CopyOnWriteArrayList;

class Reader implements Runnable {
    private final CopyOnWriteArrayList<Integer> listOfNumbers;
    private int lastReadValue = -1;

    Reader(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        while (listOfNumbers.size() < Task2.NUM_VALUES || listOfNumbers.get(listOfNumbers.size() - 1) != lastReadValue) {
            if (!listOfNumbers.isEmpty()) {
                int currentValue = listOfNumbers.get(listOfNumbers.size() - 1);
                if(currentValue != lastReadValue) {
                    lastReadValue = currentValue;
                    System.out.printf("Чтение <- %d\n", currentValue);
                }
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}