/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MQTT_protocol_test;

import java.util.Random;

/**
 *
 * @author Lattanzi
 */
public class Publisher extends Thread{
    // attributo interno per la condivisione del buffer
    private Broker myBroker;
    private String topic;
    private Random rnd;
    
    // costruttore del produttore
    public Publisher(String name, Broker broker, String t){
        super(name);
        this.myBroker = broker;
        this.topic    = t;
        this.rnd      = new Random();
    }
    
    // comportamento del thread 
    @Override
    public void run(){        
        try{
            for(int i = 0; i < 10; i++){ 
                Thread.sleep(this.rnd.nextInt(350));
                this.myBroker.publish(new Message(this.topic,i));               
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }        
    }  
    
    public String getTopic(){
        return this.topic;
    }
}







