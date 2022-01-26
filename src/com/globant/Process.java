package com.globant;

public abstract class Process {
    private int size = 0;
    private String name = "";

    public void setName(String processName, int processNumber) {
        name = processName+String.format("%03d", processNumber);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int minSize, int maxSize){
        size = (int) (Math.random() * (maxSize - minSize)) + minSize;
    };

}
