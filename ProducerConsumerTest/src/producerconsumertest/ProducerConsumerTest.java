/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package producerconsumertest;

/**
 *
 * @author marco
 */
public class ProducerConsumerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        // creo oggetto condiviso
        // creo thread
        // lancio i thread
        // attendo la terminazione dei thread
        BoundedBuffer buffer = new BoundedBuffer(15);
        Producer producer = new Producer(buffer, "Producer");
        Consumer consumer = new Consumer(buffer,"Consumer");
        
        producer.start();
        consumer.start();
        
        //attendo la terminazione
        try{
            producer.join();
            consumer.join();
        
        }catch(InterruptedException e){
            System.out.println(e);
        
        }
        System.out.println("Tutti i thread sono terminati");
    }
    
}
