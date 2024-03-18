package com.goncharuk.Task1;

class Run1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId());
    }
}
