package co.com.luisprmat.training.attendance.model.network.responses.apiError;

public class ApiErrorResponse {
    private String message;
    private Error errors;

    public ApiErrorResponse(String message, Error errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getErrors() {
        return errors;
    }

    public void setErrors(Error errors) {
        this.errors = errors;
    }
}
