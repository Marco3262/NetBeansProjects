/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MQTT_protocol_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lattanzi
 */
public class Broker {
    // attributi della classe 
    private Message buffer[];
    private Message toBeDispatched;
    // puntatori logici 
    private int removingPointer, addingPointer;
    private int elementsInBuffer;
    
    // strumenti per la sincronizzazione
    // lock per la mutua esclusione tra i metodi 
    // put e get
    private Lock publishingLock;
    private Lock subscribingLock;
    
    // due variabili condizionali per sospendere 
    // il consumatore ed il produttore
    private Condition notFull;
    private Condition notEmpty;
    
    private Condition availableA;
    private Condition availableB;
    private Condition availableC;
    
    
    // costruttore dell'oggetto buffer
    
    public Broker(int size){
        // inizializzare gli attributi
        this.buffer           = new Message[size];
        this.addingPointer    = 0;
        this.removingPointer  = 0;
        this.elementsInBuffer = 0;
        
        this.publishingLock   = new ReentrantLock();
        this.subscribingLock  = new ReentrantLock();
        
        // producer consumer
        this.notFull    = this.publishingLock.newCondition();
        this.notEmpty   = this.publishingLock.newCondition();
        
        // subscribers
        this.availableA = this.subscribingLock.newCondition();
        this.availableB = this.subscribingLock.newCondition();
        this.availableC = this.subscribingLock.newCondition();
        
        
    }
    
    // definisco i metodi pubblici di interfaccia 
    // con il buffer
    
    // forwardo l'eccezione al thread invocante
    public void publish(Message item) throws InterruptedException {
        // inizio sezione critica 
        this.publishingLock.lock();
        try{
            // controllo se il buffer è pieno 
            // se il buffer è pieno mi sospendo 
            // sulla mia variabile condizionale
            while(this.elementsInBuffer == this.buffer.length)
                this.notFull.await(); // il thread invocante
                // viene sospeso ed automaticamente il lock
                // viene rilasciato permettendo l'ingresso 
                // di altri thread
            // se sono qua posso inserire l'elemento
            this.elementsInBuffer++;
            this.buffer[this.addingPointer] = item;
            this.addingPointer = (this.addingPointer+1)%this.buffer.length;            
            System.out.println("New message of type: "+item.getTopic()+" added to the buffer ");
            System.out.println("Elements count: "+this.elementsInBuffer);
            // segnalo la presenza di un nuovo elemento nel buffer
            // ad un eventuale consumatore sospeso  
            this.notEmpty.signal();
        }finally {
            this.publishingLock.unlock();
            // fine sezione critica
        }
    }
    
    // metodo di get
    public Message subscribe(Subscriber s) throws InterruptedException{
        // inizio sezione critica
        this.subscribingLock.lock();
        try{
            System.out.println(s.getName()+" subscribes for "+s.getType()+" messages");            
            switch(s.getType()){
                case Message.A:
                        this.availableA.await(); 
                        break;
                case Message.B:
                        this.availableB.await(); 
                        break;
                case Message.C:
                        this.availableC.await(); 
                        break;
                default:
                    System.out.println("ERROR: illegal packet type");
                    System.exit(1);
            }            
            return this.toBeDispatched;
        }finally{
            this.subscribingLock.unlock();
        }
    }
    
    public void dispatchMessage()throws InterruptedException{
        // inizio sezione critica
        this.publishingLock.lock();        
        try{
            // mi sospendo se il buffer è vuoto
            while(this.elementsInBuffer == 0)
                this.notEmpty.await();
            // rimuovo l'elemento
            this.toBeDispatched = this.buffer[this.removingPointer];
            this.removingPointer = (this.removingPointer+1) % this.buffer.length;
            this.elementsInBuffer--;
            System.out.println("Dispatcher sent message T: "+this.toBeDispatched.getTopic());
            //System.out.println("Elementi attuali "+
            //        this.elementsInBuffer);
            this.notFull.signal();      
        }finally{
            this.publishingLock.unlock();            
        }
        
        this.subscribingLock.lock(); 
        try{
            switch(this.toBeDispatched.getTopic()){
                case Message.A:
                        this.availableA.signalAll(); 
                        break;
                case Message.B:
                        this.availableB.signalAll(); 
                        break;
                case Message.C:
                        this.availableC.signalAll(); 
                        break;
                default:
                    System.out.println("ERROR: illegal packet type");
                    System.exit(1);
            }                  
        }finally{            
            this.subscribingLock.unlock();
        }
    }
    
}








