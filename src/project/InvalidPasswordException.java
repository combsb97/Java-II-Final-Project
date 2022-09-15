/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author Ben
 */
public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(){
        super("Invalid Password: Password does not meet requirements!");
    }
    
    public InvalidPasswordException(String message){
        super(message);
    }
}
