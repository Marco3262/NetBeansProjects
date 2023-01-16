/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MQTT_protocol_test;

/**
 *
 * @author Lattanzi
 */
public class MQTT_protocol_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creo l'oggetto condiviso
        Broker broker             = new Broker(10);
        Publisher[] publishers    = new Publisher[3];
        Subscriber[] subscribers  = new Subscriber[30];
        
        publishers[0] = new Publisher("Publisher_0",broker, Message.A);
        publishers[1] = new Publisher("Publisher_1",broker, Message.B);
        publishers[2] = new Publisher("Publisher_2",broker, Message.C);
        
        for(int i = 0; i < subscribers.length; i++){
            if(i<10)
                subscribers[i] = new Subscriber("Subscriber_"+i,broker, Message.A);
            else if(i<20)
                subscribers[i] = new Subscriber("Subscriber_"+i,broker, Message.B);
            else 
                subscribers[i] = new Subscriber("Subscriber_"+i,broker, Message.C);
        }
        
        BrokerManager manager = new BrokerManager(broker);
        manager.start();
        for(int i = 0; i < publishers.length; i++){
            // lancio i thread 
            publishers[i].start();            
        }
        for(int i = 0; i < subscribers.length; i++){
            // lancio i thread             
            subscribers[i].start();
        }
        
        // attesa per la terminazione dei thread 
        
        try{
            for(int i = 0; i < publishers.length; i++){            
                publishers[i].join();                
            }
            for(int i = 0; i < subscribers.length; i++){            
                subscribers[i].interrupt();                
                subscribers[i].join();  
            }
            manager.interrupt();
            manager.join();
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }
        System.out.println("Simulazione terminata!!");
    }
}
