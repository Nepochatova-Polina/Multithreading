package com.company.Module1.ModuleTask;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;

public class task11Java {
            public static void main(String[] args) {
                Passenger[] passengers = new Passenger[20];
            for (int i = 0; i < passengers.length; i++) {
                passengers[i] = new Passenger(i + 1);
            }

            List<Passenger> listPassenger = Arrays.asList(passengers);
                WaitingRoom depatureRoom = WaitingRoom.createwaitingRoom(listPassenger.size(), listPassenger);
                WaitingRoom dectinationRoom = WaitingRoom.createwaitingRoom(listPassenger.size());
            Phaser phaser = new Phaser();
            phaser.register();
            int currentPhase;
            Thread tr1 = new Thread(new Plane(phaser, "tr1", 5, depatureRoom, dectinationRoom));
            Thread tr2 = new Thread(new Plane(phaser, "tr2", 6, depatureRoom, dectinationRoom));
            Thread tr3 = new Thread(new Plane(phaser, "tr3", 7, depatureRoom, dectinationRoom));

            tr1.start();
            tr2.start();
            tr3.start();
            currentPhase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();

        }

}

 class Plane implements Runnable {
    private final Phaser phaser;
    private final String number;
    private final int capacity;
    private final WaitingRoom depature;
    private final WaitingRoom dectination;
    private final Queue<Passenger> planeBody;

    public Plane(Phaser phaser, String name, int capacity, WaitingRoom stFrom,
                 WaitingRoom stTo) {
        this.phaser = phaser;
        this.number = name;
        this.capacity = capacity;
        this.planeBody = new ArrayDeque<Passenger>(capacity);
        this.depature = stFrom;
        this.dectination = stTo;
        this.phaser.register();
    }

    public void run() {
        loadPlane();
        phaser.arriveAndAwaitAdvance();
        goFly();
        phaser.arriveAndAwaitAdvance();
        unloadPlane();
        phaser.arriveAndDeregister();
    }

    private void loadPlane() {
        for (int i = 0; i < capacity; i++) {
            Passenger g = depature.getPassenger();
            if (g == null) {
                return;
            }
            planeBody.add(g);
            System.out.println("Plane " + number + " accepted a passenger with a number №"
                    + g.getRegistrationNumber());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unloadPlane() {
        int size = planeBody.size();
        for (int i = 0; i < size; i++) {

            Passenger g = planeBody.poll();
            dectination.setPassenger(g);
            assert g != null;
            System.out.println("Passenger " + g.getRegistrationNumber() + " flight by plane №"
                    + number );
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void goFly() {
        try {
            Thread.sleep(new Random(100).nextInt(500));
            System.out.println("Plane " + number + " started flying.");
            Thread.sleep(new Random(100).nextInt(1000));
            System.out.println("Plane " + number + " ended flying.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Passenger {
    private final int registrationNumber;

    public Passenger(int number) {
        this.registrationNumber = number;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }
}

class WaitingRoom implements Iterable<Passenger> {
    public static final int DEFAULT_STORAGE_CAPACITY = 20;
    private Queue<Passenger> passengerQueue = null;

    private WaitingRoom() {
        passengerQueue =
                new LinkedBlockingQueue<Passenger>(DEFAULT_STORAGE_CAPACITY);
    }

    private WaitingRoom(int capacity) {
        passengerQueue = new LinkedBlockingQueue<Passenger>(capacity);
    }

    public static WaitingRoom createwaitingRoom(int capacity) {
        return new WaitingRoom(capacity);
    }

    public static WaitingRoom createwaitingRoom(int capacity, List<Passenger> passenger) {

        WaitingRoom waitingRoom = new WaitingRoom(capacity);
        waitingRoom.passengerQueue.addAll(passenger);
        return waitingRoom;
    }

    public Passenger getPassenger() {
        return passengerQueue.poll();
    }

    public void setPassenger(Passenger passenger) {
        passengerQueue.add(passenger);
    }

    @Override
    public Iterator<Passenger> iterator() {
        return passengerQueue.iterator();
    }
}
