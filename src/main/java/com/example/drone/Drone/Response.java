package com.example.drone.Drone;

public class Response {
    private Boolean ok;
    private String status;
    private String message;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response(Boolean ok, String status, String message) {
        this.ok = ok;
        this.status = status;
        this.message = message;
    }
}
