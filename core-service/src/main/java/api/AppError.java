package api;

public class AppError {

    public AppError(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
