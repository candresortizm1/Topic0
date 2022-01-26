package com.globant;
import exceptions.MemoryOverflowException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Memory memory = new Memory();
        while(true){
            String user_entry = scanner.nextLine().toLowerCase();
            String action = user_entry.substring(0,1);
            String argument = user_entry.substring(1);
            if(action.equals("c") && (argument.equals("s") || argument.equals("a"))){
                addProcess(memory,argument);
            }else if(action.equals("d")){
                try {
                    memory.deleteProcess(Integer.parseInt(argument));
                } catch(NullPointerException e) {
                    System.out.println("No fue posible encontrar el proceso");
                }
            }else if(action.equals("e")){//exit
                break;
            }else{
                System.out.println("Entrada inválida");
            }
            memory.printMemory();
        }

    }

    private static void addProcess(Memory memory,String processType){
        Process newProcess;
        if (processType.equals("s")){
            newProcess = new SystemProcess();
        }else{
            newProcess = new ApplicationProcess();
        }
        try {
            memory.addProcess(processType,newProcess);
        }catch (MemoryOverflowException me){
            System.out.println("************************************************");
            System.out.println("*****************MEMORIA LLENA******************");
            System.out.println("************************************************");
        }
    }
}
