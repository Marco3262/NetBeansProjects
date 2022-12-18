/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingproject;

/**
 *
 * @author marco
 */

// Sarà un thread cliente che effettua le richieste di booking al manager

// Architettura di condivisione di oggetti nella programmzione concorrente a oggetti

public class Client extends Thread{
    
    // attributi interni
    private final BookingManager myBooking;
    
    // costruttore classe client
    public Client(BookingManager b, String name){
        //setto il nome del thread a livello di sistema
        super(name);
        //assegno l'ggetto da condividere al mio riferimento interno
        this.myBooking = b;
    }
    
    // definisco il comportamento del thread
    @Override
    public void run(){
        // chiedo un posto alla volta fino ad esaurimento posti
        while(this.myBooking.book(this)){
            System.out.println(super.getName()+
                    " ha ottenuto un posto!!!");            
        }// qua significa che i posti sono terminati
        System.out.println(super.getName()+" termina!!");
    }//end metodo run 
        

}