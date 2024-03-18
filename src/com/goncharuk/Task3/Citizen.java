package com.goncharuk.Task3;

import java.util.concurrent.ThreadLocalRandom;

class Citizen implements Runnable {
    @Override
    public void run() {
        int category = ThreadLocalRandom.current().nextInt(3); // 0 - молодые, 1 - пожилые, 2 - бизнесмены
        int window = ThreadLocalRandom.current().nextInt(3); // 0 - 1, 1 - 2 или 2 - 3 окно

        switch (window) {
            case 0:
                synchronized (Task3.windows.get(0)) {
                    // Услуга получена
                    System.out.printf("Житель [%s] получил услугу в 1 окне\n", this.whatCategory(category));
                }
                break;
            case 1:
                if (category == 1) {
                    synchronized (Task3.windows.get(1)) {
                        // Услуга получена
                        System.out.printf("Житель [%s] получил услугу в 2 окне\n", this.whatCategory(category));
                    }
                } else {
                    angryCitizen(category);
                }
                break;
            case 2:
                if (category == 2) {
                    synchronized (Task3.windows.get(2)) {
                        // Услуга получена
                        System.out.printf("Житель [%s] получил услугу в 3 окне\n", this.whatCategory(category));
                    }
                } else {
                    angryCitizen(category);
                }
                break;
        }
    }

    private void angryCitizen(int category) {
        switch (category) {
            case 0:
                Task3.angryYoung.incrementAndGet();
                System.out.printf("Житель [%s] не получил услугу\n", this.whatCategory(0));
                break;
            case 1:
                Task3.angryElderly.incrementAndGet();
                System.out.printf("Житель [%s] не получил услугу\n", this.whatCategory(1));
                break;
            case 2:
                Task3.angryBusinessmen.incrementAndGet();
                System.out.printf("Житель [%s] не получил услугу\n", this.whatCategory(2));
                break;
        }
    }
    private String whatCategory(int i){
        return switch (i) {
            case 0 -> "молодые";
            case 1 -> "пожилые";
            case 2 -> "бизнесмены";
            default -> null;
        };
    }
}