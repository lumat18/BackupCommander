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
        System.out.print("Copying files");
        int i = 0;
        while(running.get()){
            try{
                i++;
                if(i%4!=0) {
                    System.out.print(".");
                }
                else{
                    System.out.print("\b\b\b");
                }
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
