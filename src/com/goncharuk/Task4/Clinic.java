package com.goncharuk.Task4;

import java.util.concurrent.atomic.AtomicInteger;

public class Clinic {
    static final int MAX_PATIENTS = 10; // Максимальное количество пациентов
    static final AtomicInteger queueLength = new AtomicInteger(0);
    static final AtomicInteger maxQueueLength = new AtomicInteger(0);
    private static final Object therapistLock = new Object();
    private static final Object mriLock = new Object();

    public static Object getTherapistLock() {
        return therapistLock;
    }

    public static Object getMriLock() {
        return mriLock;
    }

    public static void incrementQueueLength() {
        queueLength.incrementAndGet();
    }

    public static void decrementQueueLength() {
        queueLength.decrementAndGet();
    }

    public static void updateMaxQueueLength() {
        maxQueueLength.updateAndGet(x -> Math.max(x, queueLength.get()));
    }
}