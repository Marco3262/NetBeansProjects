/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prioritybarber;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
// l'oggetto condiviso tra barbiere e clienti
public class BarberShop {
    // attributi funzionali
    // mettiamo una coda esplicita per la gestione 
    // del risveglio arbitrario dei thread
    private LinkedList<Customer> myQueue;
    // attributi di sincronizzazione
    // semaforo binario a guardia di myQueue
    private ReentrantLock mutex;
    // semaforo contatore per sospendere il barbiere
    private Semaphore newCustomer;
    
    // costruttore
    public BarberShop(){
        // inizializzo gli attriubuti funzionali
        this.myQueue = new LinkedList();
        // inizializzo gli attributi di sincronizzazione
        this.mutex = new ReentrantLock();
        this.newCustomer = new Semaphore(0);
    }
    // definiamo metodi publici thread-safe
    
    // metodo per entrare in attesa del taglio
    // deve essere in mutua esclusione con il metodo che taglia i capelli
    // deve sospendere il thread invocante in attesa del taglio
    public void enter(Customer c){
        // devo inserire i l riferiento nella coda
        // inizia sezione critica
        this.mutex.lock();
        try{
            System.out.println(c.getName() + " si mette in attesa con priorità" +
                    c.getMyPriority());
            this.myQueue.add(c);
            this.newCustomer.release();
        }finally{
            this.mutex.unlock();
            // fine sezione critica
        }
        // ora posso mettermi in attesa del servizio
        try{
            c.block();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        // se sono qua significa che il barbiere mi ha tagliato i capelli
        // e sono stato risvegliato
        System.out.println(c.getName() + " priorità: "+ c.getMyPriority() +
                " ha tagliato i capelli");
    }// end metodo enter
    
    // metodo invocato dal barbiere per tagliare i capelli
    // sarà in mutua esclusione con enter
    // sospende il barbiere se non ci sono richieste
    // deve inoltrare l'eccezione di interruptedException
    // per attuare la terminazione deferita del thread barbiere
    public void cut() throws InterruptedException{
        // controllo se ci sono clienti
        this.newCustomer.acquire();
        // se sono qua c'è almeno un cliente in coda
        // devo trovare il cliente migliore con massima priorità
        // e tagliare i capelli a lui
        Customer best = null;
        // inizio sezione critica
        this.mutex.lock();
        try{
            best = getAndRemoveBestCustomer();
            // Abbiamo nella variabile best il miglior cliente
            System.out.println("Il barbiere taglia i capelli a: " +
                    best.getName() + " con priorità: " +
                    best.getMyPriority());
        }finally{
            this.mutex.unlock();
            // fine sezione critica
        }
        // simulo il tempo necessario a tagliare i capelli
        Thread.sleep(20);
        // ora posso liberare il cliente
        best.wakeUp();
    
    
    }

    private Customer getAndRemoveBestCustomer() {
       Customer theBest = null;
       Customer current = null;
       int maxPriority = -1;
       for(int i = 0; i < this.myQueue.size(); i++){
           current = this.myQueue.get(i);
           // SE FACCIO SOLO MAGGIORE A PARITA DI PUNTEGGIO 
           // SCELGO IN ORDINE FIFO
           // SE FACCIO MAGGIORE UGUALE INVECE LIFO 
           if(current.getMyPriority() > maxPriority){
               maxPriority = current.getMyPriority();
               theBest = current;
           }
       }
       // rimuovo dalla coda il cliente migliore
       this.myQueue.remove(theBest);
       return theBest;
    }
    
}// end metodo classe
