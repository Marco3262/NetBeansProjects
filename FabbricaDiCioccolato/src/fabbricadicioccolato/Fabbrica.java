/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabbricadicioccolato;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Fabbrica {
    
    // Attributi funzionali
    private LinkedList<UmpaLumpa> myQueue;
    private Semaphore Forni;
    private ReentrantLock mutex;
    private Semaphore nuoveRichieste;

    
    public Fabbrica(){
        this.Forni = new Semaphore(3);
        this.mutex = new ReentrantLock();
        this.nuoveRichieste = new Semaphore(0);
        this.myQueue = new LinkedList();
    
    }
    
    public void richiediForno(UmpaLumpa p){
        this.mutex.lock();
        try{
            this.myQueue.add(p);
            System.out.println(p.getName()+ " ha fatto la richiesta "+ 
                    "e ha "+p.getSemi()+" semi da lavorare!");
            this.nuoveRichieste.release();
            p.attende();
        }finally{
            this.mutex.unlock();
        }
        
        
    }// end metodo richiediForno
    
    public void liberaForno(){
        this.mutex.lock();
        try{
            this.Forni.release();
        }finally{
            this.mutex.unlock();
        }
        
    }
    
    public void gestisciRichieste() throws InterruptedException{
        UmpaLumpa best = null;
        UmpaLumpa current = null;
        int semiMassimi = 10;
        this.nuoveRichieste.acquire();
        this.mutex.lock();
        try{
            
            // ora devo trovare il migliore
            for(int i = 0;i < this.myQueue.size();i++){
                current = this.myQueue.get(i);
                if(current.getSemi() > semiMassimi){
                    semiMassimi = current.getSemi();
                    best = current;
                }
            }

            this.myQueue.remove(best);
            this.Forni.acquire();
            System.out.println(best.getName()+" ha ottenuto il forno e lo usa!!");
        best.sveglia();
        }finally{
            this.mutex.unlock();
        }
    }
    
}
