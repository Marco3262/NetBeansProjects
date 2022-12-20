/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package boundedbuffercondition;

/**
 *
 * @author Lattanzi
 */
public class BoundedBufferCondition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creo l'oggetto da condividere
        // creo i thread
        // lancio i thread 
        // attendo per la terminazione dei thread figli
        BoundedBuffer buffer = new BoundedBuffer(15);
        Consumer consumer    = new Consumer(buffer, "Consumer");
        Producer producer    = new Producer(buffer, "Producer");
        
        consumer.start();
        producer.start();
        
        // mi metto in attesa per la terminazione dei thread figli
        try{
            producer.join();
            consumer.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        // ora tutti i thread sono terminati
        System.out.println("Simulazione terminata!!");
    }//end mentodo main()    
}// end classe



