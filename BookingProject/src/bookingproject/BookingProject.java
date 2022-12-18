/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bookingproject;

/**
 *
 * @author marco
 */
public class BookingProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // creo l'oggetto da condividere
        // poi creo i thread
        // poi lancio i thread
        // poi attendo per la terminazione di tutti i figli
        
        BookingManager manager = new BookingManager();
        //ora creo un array di oggetti client
        
        Client clienti[] = new Client[6];
        
        for(int i = 0; i < clienti.length; i++){
            clienti[i] = new Client(manager, "Client_"+i);
            clienti[i].start();
        }
        //ora il thread regista si mette in attesa della terminazione di tutti i figli
        try{
            for(int i = 0; i < clienti.length;i++)
                    clienti[i].join();
        }catch(InterruptedException c){
            System.out.println(c);
        }
        
        //se sono qua significa che tutti i figli sono terminati. quindi vado a leggere tutti i posti da occupare
        System.out.println("Simulazione terminata" + "posti disponibili" + manager.getPostiLiberi());
    }//end metodo main
    
}//end classe
