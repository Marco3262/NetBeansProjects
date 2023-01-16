/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MQTT_protocol_test;

/**
 *
 * @author Lattanzi
 */
public class Subscriber extends Thread{
    // attributo interno per la condivisione del buffer
    private Broker myBroker;
    private String topic; 
    
    // costruttore del produttore
    public Subscriber(String name, Broker buff, String t){
        super(name+"_"+t);
        this.myBroker = buff;
        this.topic   = t;        
    }
    
    // comportamento del thread 
    @Override
    public void run(){  
        boolean isAlive = true;
        try{
            while (isAlive){ 
                Message tmp = this.myBroker.subscribe(this);
                System.out.println(super.getName()+" received message: "+tmp.getTopic()+" value: "+tmp.getValue());
            }
        }catch(InterruptedException e){
            System.out.println(e);
            isAlive = false;
        }        
    }  
    public String getType(){
        return this.topic;
    }
}







