package exceptions;

public class MemoryOverflowException extends Exception{

    public MemoryOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
