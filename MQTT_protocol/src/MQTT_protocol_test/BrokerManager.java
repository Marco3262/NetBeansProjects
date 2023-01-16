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
public class BrokerManager extends Thread {
    private Broker myBroker;
    
    public BrokerManager(Broker m){
        super("BrokerManager");
        this.myBroker = m;
    }

// comportamento del thread 
    @Override
    public void run(){  
        boolean isAlive = true;
        try{
            while (isAlive){                 
                this.myBroker.dispatchMessage();
                System.out.println(super.getName()+" message dispatched");                
            }
        }catch(InterruptedException e){
            System.out.println(e);
            isAlive = false;
        }
        
    }  
}
