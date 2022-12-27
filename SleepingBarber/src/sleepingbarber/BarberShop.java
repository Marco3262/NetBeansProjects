/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sleepingbarber;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
// oggetto condiviso fra il thread cliente barbiere e i thread clienti

public class BarberShop {
    // attributii funzionali 
    // costante che definisce il numero di sedie
    private static final int NUM_SEATS = 5;
    // variabile che conta i posti disponibili
    private int freeSeats;
    
    // attributi di sincronizzazione
    
    // a guardia di freeSeats
    private ReentrantLock mutex;
    // semaforo per sospendere il barbiere
    private Semaphore newCustomers;
    // semaforo per sospendere i clienti
    private Semaphore barberAvailable;
    
    // costruttore dell'oggetto
    public BarberShop(){
        // attributi funzionali
        this.freeSeats = BarberShop.NUM_SEATS;
        // attributi di sincronizzazione
        this.mutex = new ReentrantLock();
        this.newCustomers = new Semaphore(0); // qui fifo non ha senso perchè è il barbiere che dorme ed è solo uno
        this.barberAvailable = new Semaphore(0, true); // politica fifo perchè i clienti sono quelli che si svegliano con questo semaforo
    }// end costruttore
    
    // metodo utilizzato dai clienti per 
    // cercare posto nel negozio ed eventualmente attendere
    // il taglio di capelli
    public boolean lookAndWait(Customer c){
        boolean ret = false;
        // controllo se ci sono posti per l'attesa
        // inizia la sezione critica
        this.mutex.lock();
        if(this.freeSeats > 0){
            // posso fermarmi in attesa per il taglio
            this.freeSeats--;
            // sveglio il barbiere se dorme
            this.newCustomers.release();
            // mi devo mettere in attesa del taglio di capelli
            // rilascio il lock
            this.mutex.unlock();
            // ora posso aspettare sul semaforo
            try{
                this.barberAvailable.acquire();
                // mi sono stati tagliati i capelli
                ret = true;
                System.out.println(c.getName() + " ha tagliato i capelli");
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }else{
            // non c'è posto
            // rilascio il semaforo 
            System.out.println("Il cliente "+c.getName()+
                    " non ha potuto tagliare i capelli!!");
            this.mutex.unlock();
        }
        
        return ret;
    }// end metodo lookAndWait
    
    // metodo invocato dal barbiere per tagliare i capelli
    public void cut() throws InterruptedException{
        // prima cosa da fare è svegliare il barbiere
        // si sospenderà subito in attesa dei clienti
        // perchè all'inizio ha zero permessi
        this.newCustomers.acquire();
        // se sono qua almeno un cliente è arrivato
        // incremento allora il numero di sedie disponibili
        this.mutex.lock();
        try{
            this.freeSeats++;
            System.out.println("Il barbiere taglia i capelli!!");
        }finally{
            this.mutex.unlock();
        }
        
        // simulo il tempo necessario a tagliare i capelli con una sleep
        // FUORI DALLA SEZIONE CRITICA
        Thread.sleep(25); // tempo necessario 25 ms
        // ora libero dalla coda d'attesa un altro cliente in ordine fifo
        this.barberAvailable.release();
    
    }
    
}// end classe
