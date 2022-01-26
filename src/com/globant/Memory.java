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
    private int busySlots = 0;
    private int currentRow = 0;
    private int processCreated = 1;

    public void addProcess(String process_type, Process process) throws MemoryOverflowException {
        if(busySlots + process.getSize() > MAX_SLOTS_MEMORY){
            throw new MemoryOverflowException("Memoria llena");
        }else{
            process.setName(process_type, processCreated);
            memoryProcess.put(processCreated,process);
            processCreated += 1;
            busySlots += process.getSize();
        }
    }

    public int printProcess(int initColumn, Process process, int printed){
        int totalSlots = process.getSize() - printed;
        if (visualMemory[currentRow]==null){
            visualMemory[currentRow] = "";
        }
        if(initColumn + totalSlots >= TOTAL_COLUMNS){
            visualMemory[currentRow] += (process.getName()+" ").repeat(TOTAL_COLUMNS-initColumn);
            printed = printed + TOTAL_COLUMNS - initColumn;
            if(currentRow < TOTAL_ROWS-1){
                currentRow += 1;
                initColumn = printProcess(0,process,printed);
            }
        } else {
            visualMemory[currentRow] += (process.getName()+" ").repeat(totalSlots);
            initColumn = initColumn + totalSlots;
            if(initColumn==TOTAL_COLUMNS){
                currentRow +=1;
            }
        }
        return initColumn;
    }

    public void printMemory(){
        int currentColumn=0;
        currentRow =0;
        visualMemory = new String[TOTAL_ROWS];
        Integer[] keys = memoryProcess.keySet().toArray(new Integer[memoryProcess.size()]);
        Arrays.sort(keys);

        for (Integer llave : keys) {
            Process process = memoryProcess.get(llave);
            currentColumn = printProcess(currentColumn,process,0);
        }
        if(currentColumn != TOTAL_COLUMNS && currentColumn != 0){
            visualMemory[currentRow] += "**** ".repeat(TOTAL_COLUMNS - currentColumn);
            currentRow +=1;
        }
        if(currentRow <= TOTAL_ROWS-1 && (visualMemory[currentRow]==null || visualMemory[currentRow].equals(""))){
            int initialRow = currentRow;
            for(int i = 0 ;i < TOTAL_ROWS - initialRow;i++){
                visualMemory[currentRow]=("**** ").repeat(TOTAL_COLUMNS);
                currentRow +=1;
            }
        }

        for (int i=TOTAL_ROWS-1;i >= 0;i--) {
            System.out.println(visualMemory[i]);
        }


    }

    public void deleteProcess(int delProcess) {
        Process process = memoryProcess.get(delProcess);
        busySlots = busySlots - process.getSize();
        memoryProcess.remove(delProcess);
    }
}
