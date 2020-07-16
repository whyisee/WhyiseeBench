package com.base.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {
    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask (BlockingQueue<File> queue,String keyword){
        this.queue = queue;
        this.keyword = keyword;
    }
    @Override
    public void run() {
        try {
        boolean done = false;
        while (!done){
            File file = null;

                file = queue.take();

            if(file == FileEnumerationTask.DUMMY){
                queue.put(file);
                done = true;
            }else {
                search(file);
            }
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e){

        }
    }

    public void search(File file) throws FileNotFoundException {
        try(Scanner in = new Scanner(file)){
            int lineNumber = 0;
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)){
                    System.out.println("===test===>"+file.getPath()+lineNumber+line);
                }
            }
        }
    }
}
