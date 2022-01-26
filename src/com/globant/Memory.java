package com.globant;
import java.util.Arrays;
import java.util.HashMap;
import exceptions.MemoryOverflowException;

public class Memory{
    private HashMap<Integer, Process> memoryProcess = new HashMap<Integer, Process>();
    private final int MAX_SLOTS_MEMORY = 200;
    private final int TOTAL_COLUMNS = 10;
    private  final int TOTAL_ROWS = (int) MAX_SLOTS_MEMORY/TOTAL_COLUMNS;
    private String[] visualMemory = new String[TOTAL_ROWS];
    private int busy_slots = 0;
    private int current_row = 0;
    private int process_created = 1;

    public void addProcess(String process_type, Process process) throws MemoryOverflowException {
        if(busy_slots + process.getSize() > MAX_SLOTS_MEMORY){
            throw new MemoryOverflowException("Memoria llena");
        }else{
            process.setName(process_type, process_created);
            memoryProcess.put(process_created,process);
            process_created += 1;
            busy_slots += process.getSize();
        }
    }

    public int printProcess(int init_column, Process process, int printed){
        int total_slots = process.getSize() - printed;
        if (visualMemory[current_row]==null){
            visualMemory[current_row] = "";
        }
        if(init_column + total_slots >= TOTAL_COLUMNS){
            visualMemory[current_row] += (process.getName()+" ").repeat(TOTAL_COLUMNS-init_column);
            printed = printed + TOTAL_COLUMNS - init_column;
            if(current_row < TOTAL_ROWS-1){
                current_row += 1;
                init_column = printProcess(0,process,printed);
            }
        } else {
            visualMemory[current_row] += (process.getName()+" ").repeat(total_slots);
            init_column = init_column + total_slots;
            if(init_column==TOTAL_COLUMNS){
                current_row+=1;
            }
        }
        return init_column;
    }

    public void printMemory(){
        int current_column=0;
        current_row=0;
        visualMemory = new String[TOTAL_ROWS];
        Integer[] keys = memoryProcess.keySet().toArray(new Integer[memoryProcess.size()]);
        Arrays.sort(keys);

        for (Integer llave : keys) {
            Process process = memoryProcess.get(llave);
            current_column = printProcess(current_column,process,0);
        }
        if(current_column != TOTAL_COLUMNS && current_column != 0){
            visualMemory[current_row] += "**** ".repeat(TOTAL_COLUMNS - current_column);
            current_row+=1;
        }
        if(current_row <= TOTAL_ROWS-1 && (visualMemory[current_row]==null || visualMemory[current_row].equals(""))){
            int initial_row = current_row;
            for(int i = 0 ;i < TOTAL_ROWS - initial_row;i++){
                visualMemory[current_row]=("**** ").repeat(TOTAL_COLUMNS);
                current_row+=1;
            }
        }

        for (int i=TOTAL_ROWS-1;i >= 0;i--) {
            System.out.println(visualMemory[i]);
        }


    }

    public void deleteProcess(int del_process) {
        Process process = memoryProcess.get(del_process);
        busy_slots = busy_slots - process.getSize();
        memoryProcess.remove(del_process);
    }
}
