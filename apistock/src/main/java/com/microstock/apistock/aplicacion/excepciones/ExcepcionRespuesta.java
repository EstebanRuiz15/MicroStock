package com.microstock.apistock.aplicacion.excepciones;

public class ExcepcionRespuesta {
    private int statusCode;
    private String message;
    private String details;

    public ExcepcionRespuesta(){
        
    }
    public ExcepcionRespuesta(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    
    
}