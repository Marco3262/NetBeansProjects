/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.mqtt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Broker {
    private Messaggio buffer[];
    private int in,out;
    private ReentrantLock publishingLock;
    private ReentrantLock subscribingLock;
    private Condition availableA;
    private Condition availableB;
    private Condition availableC;
    private int numElem;
    private Condition notEmpty;
    private Condition notFull;
    private Messaggio daConsegnare;
    
    public Broker(int size){
        this.numElem = 0;
        this.buffer = new Messaggio[size];
        this.in = 0;
        this.out = 0;
        
        this.subscribingLock = new ReentrantLock();
        this.publishingLock = new ReentrantLock();
        this.availableA = this.subscribingLock.newCondition();
        this.availableB = this.subscribingLock.newCondition();
        this.availableC = this.subscribingLock.newCondition();
        
        this.notEmpty = this.publishingLock.newCondition();
        this.notFull = this.publishingLock.newCondition();
                
    }
    
    public void publish(Messaggio m)throws InterruptedException{
        this.publishingLock.lock();
        try{
            while(this.numElem == this.buffer.length)
                this.notFull.await();
            this.buffer[this.in] = m;
            this.in = (this.in +1)%this.buffer.length;
            this.numElem++;
            this.notEmpty.signal();
        }finally{
            this.publishingLock.unlock();
        }        
    }
    
    public Messaggio subscribe(Subscriber s)throws InterruptedException{
        this.subscribingLock.lock();
        try{
            switch(s.getType()){
                case Messaggio.A:
                    this.availableA.await();
                    break;
                case Messaggio.B:
                    this.availableB.await();
                    break;
                case Messaggio.C:
                    this.availableC.await();
                    break;
                default:
                    System.out.println("Errore nel tipo");
                    System.exit(1);
            }
            return this.daConsegnare;
        }finally{
            this.subscribingLock.unlock();
        }
    
    }
    
    public void dispatchMessage(Subscriber s) throws InterruptedException{
        this.publishingLock.lock();
        try{
            while(this.numElem == 0)
                this.notEmpty.await();
            this.buffer[this.out] = this.daConsegnare;
            this.out = (this.out+1)%this.buffer.length;
            this.numElem--;
            this.notFull.signal();
        }finally{
            this.publishingLock.unlock();
        }
        
        this.subscribingLock.lock();
        try{
            switch(s.getType()){
                case Messaggio.A:
                    this.availableA.signalAll();
                    break;
                case Messaggio.B:
                    this.availableB.signalAll();
                    break;
                case Messaggio.C:
                    this.availableC.signalAll();
                    break;
                default:
                    System.out.println("Errore nel tipo");
                    System.exit(1);
            }
        }finally{
            this.subscribingLock.unlock();
        }
    }
}
