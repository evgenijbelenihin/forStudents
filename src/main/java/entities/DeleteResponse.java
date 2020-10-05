package main.java.entities;

public class DeleteResponse {
    private int code;
    private String type;
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(int value) {
        this.code = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}
