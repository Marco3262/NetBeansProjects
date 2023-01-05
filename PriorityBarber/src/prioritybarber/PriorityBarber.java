/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prioritybarber;

/**
 *
 * @author marco
 */
public class PriorityBarber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creo l'oggetto da condividere
        BarberShop shop = new BarberShop();
        Barber barber = new Barber(shop);
        barber.start();
        
        // creo dieci clienti
        Customer customer[] = new Customer[10];
        for(int i = 0; i < customer.length;i++){
            customer[i] = new Customer(shop,"Customer_"+i);
            customer[i].start();
        }
        // mi metto ora in attesa per la terminazione solo dei clienti
        try{
            for(int i = 0;i < customer.length; i++)
                customer[i].join();
            // ora tutti i clienti sono terminati 
            // invio l'interrut al barbiere
            barber.interrupt();
            // attendo la terminazione del barbiere
            barber.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Simulazione terminata!!");
    }// end main
    
}// end classe
