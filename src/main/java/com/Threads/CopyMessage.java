package com.Threads;

import java.util.concurrent.atomic.AtomicBoolean;

public class CopyMessage implements Runnable {
    private Thread initializing;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public void start(){
        initializing = new Thread(this);
        initializing.start();
    }
    public void stop(){
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        while(running.get()){
            try{
                System.out.println("Copying files...");
                Thread.sleep(8000);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
