/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQTT_protocol_test;

/**
 *
 * @author Utente
 */
public class Message {
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    
    private String topic;
    private int value;
    
    public Message(String t, int v){
        this.topic  = t;
        this.value = v;
    }
    
    public String getTopic(){
        return this.topic;
    }
    
    public int getValue(){
        return this.value;
    }
}
