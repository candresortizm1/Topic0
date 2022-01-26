package com.globant;

public class SystemProcess extends Process {
    final int MIN_SIZE=5;
    final int MAX_SIZE=15;

    public SystemProcess(){
        super.setSize(MIN_SIZE,MAX_SIZE);
    }
}
