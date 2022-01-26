package com.globant;

public class ApplicationProcess extends Process {
    final int MIN_SIZE=10;
    final int MAX_SIZE=20;

    public ApplicationProcess(){
        super.setSize(MIN_SIZE,MAX_SIZE);
    }

}
