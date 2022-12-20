/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundedbuffercondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lattanzi
 */
// sarà l'oggetto thread-safe implementato
// facendo uso di variabili condition

public class BoundedBuffer {
    // attributi funzionali
    private int buffer[];
    // puntatori logici
    private int in,out;
    // contatore di elementi nel buffer
    private int numElements;
    // tempo di lancio dell'applicazione
    private long bootTime;
    
    // attributi di sincronizzazione
    // a guardia di buffer, in, out, numElements
    private ReentrantLock mutex;
    
    // variabili condition per sospedere il thread
    // sospende i produttori quando il buffer sarà pieno
    private Condition notFull;
    // sospende i consumatori quando il buffer sarà vuoto
    private Condition notEmpty;
    
    // costruttore della classe 
    public BoundedBuffer(int size){
        this.buffer      = new int[size];
        this.in          = 0;
        this.out         = 0;
        this.numElements = 0;
        // salvo la data e ora attuali nella variabile bootTime
        this.bootTime    = System.currentTimeMillis(); 
        // inizializzo gli attributi di sincronizzazione
        this.mutex       = new ReentrantLock();
        // ora posso inizializzare le variabili condition
        // derivandole dall'istanza di ReentrantLock
        this.notFull     = this.mutex.newCondition();
        this.notEmpty    = this.mutex.newCondition();
    }// end costruttore
    
    // metodo di inserimento elementi
    // deve essere in mutua esclusione con il metodo di rimozione
    // deve sospendere i produttori se il buffer è pieno
    public void insert(int item){
        this.mutex.lock();
        try{
            // devo testare la variabile condivisa per 
            // sapere se c'è spazio nel buffer
            while(this.numElements == this.buffer.length)
                this.notFull.await(); // mette in sospension il thread
                                      // automaticamene rilascia il permesso
                                      // sul semaforo binario dal quale deriva
                                      // la condition
            // se sto eseguendo qua significa che
            // almeno una posizione libera esiste
            this.buffer[this.in] = item;
            this.in = (this.in + 1)%this.buffer.length;
            this.numElements++;
            // ora devo svegliare un eventuale consumer se sta dormendo
            // se sono più di uno si sveglia il primo in ordine FIFO
            this.notEmpty.signal();            
            System.out.println(this.getElapsedTime()+"> "+
                    "Inserito elemento "+item);
        }catch(InterruptedException e){
            System.out.println(e);
        }finally{
            this.mutex.unlock();
        }
    }// end metodo insert
    
    // metodo di rimozione elementi
    // deve essere in mutua esclusione con il metodo 
    // di inserimento e deve sospendere i consumatori
    // se non ci sono elementi nel buffer
    public int remove(){
        int removed = -1;
        this.mutex.lock();
        try{
            // controllo che ci sia almeno un elemento da consumare
            while(this.numElements == 0)
                this.notEmpty.await(); // mi sospendo e rilascio il lock
                                       // automaticamente
            // se sono qua significa che almeno un elemento da consumare
            // è presente nel buffer
            removed = this.buffer[this.out];
            // aggiorno il puntatore logico
            this.out = (this.out + 1)% this.buffer.length;
            this.numElements--;
            // devo segnalare ad un eventuale produttore sospeso
            // che c'è un nuvo posto nel buffer
            this.notFull.signal();
            System.out.println(this.getElapsedTime()+"> "+
                    "Rimosso elemento "+removed);            
        }catch(InterruptedException e){
            System.out.println(e);
        }finally{
            this.mutex.unlock();
        }
        return removed;
    }//end metodo remove()
    
    // metodo per conoscere il tempo passato dal boot
    public int getElapsedTime(){
        return (int)(System.currentTimeMillis() - this.bootTime);
    }
}// end classe







