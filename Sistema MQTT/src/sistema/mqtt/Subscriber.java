/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.mqtt;

/**
 *
 * @author marco
 */
public class Subscriber extends Thread {
    private String topic;
    private Broker myBroker;
    private boolean isAlive;
    
    public Subscriber(String t,String name,Broker b){
        super(name+"_"+t);
        this.topic = t;
        this.myBroker = b;
        this.isAlive = true;
    }
    
    @Override
    public void run(){
        try{
            while(isAlive){
                Messaggio tmp = this.myBroker.subscribe(this);
                System.out.println(super.getName()+" ha ricevuto il messaggio!!");
            }
        }catch(InterruptedException e){
            isAlive = false;
            System.out.println(e);
            
        }
        
    }
    public String getType() {
        return this.topic;
    }
    
}
