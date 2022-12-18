/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundedbuffercondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Marco
 */

/* Sarà l'oggetto thread safe mplementato */
/* facendo l'uso di variabili condition */
public class BoundedBuffer {
    // attributi funzionali
    private int buffer[];
    //puntatori logici
    private int in ,out;
    // contatore di elementi nel buffer
    private int numElements;
    
    // attributi di sincronizzazione
    // a guardia di buffer, in, out, numElements
    private ReentrantLock mutex;
    
    // variabili condition per sospendere i thread
    // sospende i produttori quando il buffer sarà pieno
    private Condition notFull;
    // sospende i consumatori quando il buffer sarà vuoto
    private Condition notEmpty;
    
    // costruttore della classe
    public BoundedBuffer(int size){
        this.buffer = new int[size];
        this.in = 0;
        this.out = 0;
        this.numElements = 0;
        
        // ora inizializzo gli attributi di sincronizzazione
        this.mutex = new ReentrantLock();
        
        // ora posso inizializzare le variabili condition
        // derivandole dall'istanza di ReentrantLock
        this.notFull = this.mutex.newCondition();
        this.notEmpty = this.mutex.newCondition();
    }// end costruttore
    
}
