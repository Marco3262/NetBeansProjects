/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcodivertimenti;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Giostra {
    
    private Semaphore attesaBambini;
    private Semaphore attesaAdulto;
    private Semaphore posti;
    private ReentrantLock mutex;
    private int numAdulti;
    private int numBambini;
    private int personeUscite;
    
// costruttore
    public Giostra(){
        this.numAdulti = 0;
        this.numBambini = 0;
        this.personeUscite = 0;
        
        this.mutex = new ReentrantLock();
        this.attesaBambini = new Semaphore(0,true);
        this.attesaAdulto = new Semaphore(0,true);
        this.posti = new Semaphore(8,true);
        
        
    }
    
    // metodo per cercare il posto
    // deve essere in mutua esclusione
    // blocca le persone finchè non si libera il posto
    public void cercaPosto(Persona p){
        this.mutex.lock();
        try{
            if(p instanceof Adulti)
                this.numAdulti++;
            else
                this.numBambini++;
            
            // ora vediamo se c'è una combinazione giusta
            if(this.numAdulti > 0 && this.numBambini > 2){
                this.attesaAdulto.release();
                this.numAdulti--;
                this.attesaBambini.release(3);
                this.numBambini-=3;
            }     
        }finally{
            this.mutex.unlock();
        }
        // ora mi metto in attesa se non ci sono posti
        try{
            if(p instanceof Adulti)
                this.attesaAdulto.acquire();
            else this.attesaBambini.acquire();
            
            // se sono qui vuol dire che c'era posto e l'ho occupato
            this.posti.acquire();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }// end metodo cerca posto
    
    public void liberaPosto(Persona p){
        this.mutex.lock();
        try{
            this.personeUscite++;
            if(this.personeUscite == 4){
                this.posti.release(4);
                this.personeUscite = 0;
            }
        }finally{
            this.mutex.unlock();
        }
    }
}
