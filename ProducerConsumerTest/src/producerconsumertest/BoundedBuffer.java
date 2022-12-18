/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producerconsumertest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lattanzi
 */
// oggetto condiviso fra i thread producer ed i thread consumer
// dovrà essere thread-safe cioè chiunque potrà utilizzarlo 
// senza sapere come funziona e questo non deve produrre errori
public class BoundedBuffer {
    // dichiaro gli attributi funzionali
    private int buffer[];
    // puntatori logici per gestire il buffer
    // in modo circolare
    private int in,out;
    
    // attributi di sincronizzazione 
    // semaforo binario per la mutua esclusione
    // a guardia di buffer, in, out,
    private ReentrantLock mutex;
    // semafori contatore per sospendere i produttori
    // in caso di buffer pieno e i consumatori in caso di
    // buffer vuoto
    
    // blocca i produttori
    private Semaphore notFull;
    // blocca i consumatori
    private Semaphore notEmpty;
    
    // costruttore della classe 
    public BoundedBuffer(int n){
        // salto i controlli sul valore di n
        this.buffer   = new int[n];
        this.in       = 0;
        this.out      = 0;
        // inizializzo gli attributi di sincronizzazione 
        this.mutex    = new ReentrantLock();
        this.notEmpty = new Semaphore(0,true); // blocca subito il consumer
                                                // sveglia eventuali consumer
                                                // in ordine FIFO
        this.notFull  = new Semaphore(n,true);  // il producer può inserire
                                                // subito n elementi, sveglia
                                                // in ordine FIFO
    }// end costruttore 
    
    // implemento i methodi pubblici di interfaccia 
    // che dovranno essere thread-safe
    
    // metodo di inserimento elementi nel buffer
    // deve essere in mutua esclusione con il metodo di rimozione
    // deve sospendere il producer in caso di buffer pieno
    public void insert(int item){
        try{
            // controllo se il buffer è pieno
            // acquisisco un permesso sul semaforo notFull
            this.notFull.acquire();
            // se sono qua ad eseguire significa che almeno una 
            // posizione libera c'è
            // ora posso inserire l'item nel buffer
            // acquisisco il semaforo binario per la mutua esclusione
            this.mutex.lock();
            // posso scrivere in totale sicurezza perchè 
            // sono in mutua esclusione
            this.buffer[this.in] = item;
            // incremento il puntatore logico in
            this.in = (this.in+1)%this.buffer.length;
            // devo segnalare ai consumatori che ora c'è un nuovo 
            // elemento da consumare
            this.notEmpty.release();
            System.out.println("Elemento "+item+" aggiunto!");
        }catch(InterruptedException e){
            System.out.println("Eccezione "+e);
        }finally{
            // reminder section
            // rilascio il lock
            this.mutex.unlock();
        }
    }// end metodo insert()
    
    // metodo di rimozione di un elemento
    // in mutua esclusione con il metodo insert()
    // deve sospendere i consumer in caso di buffer vuoto
    public int remove(){
        int removed = -1;
        try{
            // controllo se ci sono elementi da consumare
            this.notEmpty.acquire();
            // se sono qua significa che c'è almeno un elemento
            // da consumare 
            this.mutex.lock();
            removed  = this.buffer[this.out];
            this.out = (this.out+1)%this.buffer.length;
            // devo segnalare ad un produttore che ora c'è un nuovo 
            // spazio nel buffer
            this.notFull.release();
            System.out.println("Consumato elemento "+removed);
        }catch(InterruptedException e){
            System.out.println("Eccezione "+e);
        }finally{
            // reminder section
            this.mutex.unlock();
        }
        return removed;
    }// end metodo remove()
}// end classe







