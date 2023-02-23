/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usobagni;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Bagno {
    private Semaphore posti;
    private ReentrantLock mutex;
    private Semaphore attesaUomo;
    private Semaphore attesaDonne;
    private int tipo;
    private int numUomini;
    private int bagniLiberi;
    
    public Bagno(){
        this.posti = new Semaphore(3);
        this.mutex = new ReentrantLock();
        this.attesaDonne = new Semaphore(0,true);
        this.attesaUomo = new Semaphore(0,true);
        this.tipo = 0;
        this.numUomini = 0;
        this.bagniLiberi = 3;
        
    }
    
    private void accessoBagno(Persona p) throws InterruptedException{
        while(this.bagniLiberi == 0){
        
        }
        
            
    }
    
    private void liberaAccesso(){
    
    }
}
