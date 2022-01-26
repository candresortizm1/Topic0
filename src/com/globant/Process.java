package com.globant;

public abstract class Process {
    private int size = 0;
    private String name = "";

    public void setName(String process_name, int process_number) {
        name = process_name+String.format("%03d", process_number);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int min_size, int max_size){
        size = (int) (Math.random() * (max_size - min_size)) + min_size;
    };

}
