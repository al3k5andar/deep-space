package openspace.spacepicture.exceptions;

public class BadDateRangeException extends RuntimeException
{
    public BadDateRangeException() {
    }

    public BadDateRangeException(String message) {
        super(message);
    }
}
