package exception;

public class NoContentExcepetion extends RuntimeException{

    private static final long SERIA_VERSION_UID=1L;

    public NoContentExcepetion(String message){super(message);}
}
