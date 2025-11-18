/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.utils;

/**
 *
 * @author USUARIO
 */
public class Response {             // Cree las respuestas porque sino tenia que dejar el boton del stand sin respuesta... -Fernando
    
    private boolean success;
    private String message;
      
    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    
}
