/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sleepingbarber;

/**
 *
 * @author marco
 */
public class SleepingBarber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creo l'oggetto da condividere
        BarberShop shop = new BarberShop();
        // creo il barbiere
        Barber barber = new Barber(shop);
        // lo faccio partire
        barber.start();
        // ora creo dieci clienti
        Customer customer[] = new Customer[10];
        for(int i = 0;i < customer.length;i++){
            customer[i] = new Customer(shop,"Customer_"+i);
            customer[i].start();
        }
        // ora il regista si mette in attesa solo per i clienti
        try{
            for(int i = 0; i < customer.length;i++){
                customer[i].join();
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
        // ora so che tutti i clienti sono terminati 
        // posso terminare il barbiere
        // invio un interrupt dal barbiere
        barber.interrupt();
        
        try{
            barber.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        // se sono qui anche il barbiere è terminato
        System.out.println("Simulazione terminata!!");
    }// end main
}// end classe
