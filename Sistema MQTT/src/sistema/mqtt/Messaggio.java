/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.mqtt;

/**
 *
 * @author marco
 */
public class Messaggio {
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    
    private String topic;
    private int value;
    
    public Messaggio(String t, int v){
        this.topic = t;
        this.value = v;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public String getTopic(){
        return this.topic;
    }
}
