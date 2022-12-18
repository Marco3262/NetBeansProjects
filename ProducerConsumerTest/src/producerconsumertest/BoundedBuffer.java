/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producerconsumertest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class BoundedBuffer {
    
    // atttributi funzionali
    private int buffer[];
    // puntatori logici
    private int in,out;
    // attributi di sincronizzazione
    // semaforo binario per mutua esclusione
    private ReentrantLock mutex;
    // per il buffer che gestisce se è pieno
    // semaforo contatore che blocca i produttori(gli diamo il nome dall'evento che lo risveglierà)
    private Semaphore notFull;
    // semaforo contatore che blocca i consumatori
    private Semaphore notEmpty;
    
    // adesso creiamo il costruttore
    // deve essere creato inserendo anche la dimensione che vogliamo
    public BoundedBuffer(int n){
    // salto i controlli sul valore di n
    this.buffer = new int[n];
    this.in     = 0;
    this.out    = 0;
    // inizializzo gli attributi di sincronizzazione
    this.mutex = new ReentrantLock();
    this.notEmpty = new Semaphore(0,true); // blocca subito il consumer e poi sveglia eventuali cosumer in ordine FIFO
    this.notFull = new Semaphore(n,true); // il producer puo inserire subito n elementi in ordine FIFO
    } // end costruttore
    
    // metodo di inserimento nel buffer
    // deve essere in mutua esclusione con la rimozione
    // deve sospendere il producer se il buffer è pieno
    public void insert(int item){
        try{
            // controllo se il buffer è pieno
            // lo faccio provando ad acquisire un permesso su notFull
            this.notFull.acquire();
            // se supero l'istruzione precedente allora c'è un posto
            // altrimenti ci sarebbe stata l'eccezione
            // ora posso inserire l'item nel buffer
            //acquisisco adesso il semaforo binario per la mutua esclusione
            this.mutex.lock();
            // qui posso scrivere in tutale sicurezza grazie a mutex
            this.buffer[this.in] = item;
            // ora incremento in per il prossimo oggetto
            this.in = (this.in+1)%this.buffer.length; //?????????????????????
            // ora invece avviso i consumatori che c'è un elemento da consumare
            this.notEmpty.release();
            System.out.println("Elemento "+item+" aggiunto");
        }catch(InterruptedException e){
            System.out.println("Eccezione "+e);
        }finally{
        //reminder section in cui rilascio invece il semaforo per la mutua esclusione
        this.mutex.unlock();
        }  
    }// end metodo insert
    
    // metodo rimozione di un elemento
    // deve essere in mutua esclusione con il metodo insert
    // deve sospendere il consumer in caso di buffer vuoto
    public int remove(){
        
        int removed = -1;
        try{
            // controllo se ci sono elementi da consumare
            this.notEmpty.acquire();
            // se sono qua c'è almeno un elemento da consumare
            this.mutex.lock();
            removed = this.buffer[this.out];
            this.out = (this.out+1)%this.buffer.length;
            // segnalo al produttore che c'è uno spazio vuoto
            this.notFull.release();
            System.out.println("Consumato elemento "+removed);
        }catch(InterruptedException e){
            System.out.println("Eccezione"+e);
        }finally{
        this.mutex.unlock();
        }
    return removed;
    }// end metodo remove
    
} // end classe




















