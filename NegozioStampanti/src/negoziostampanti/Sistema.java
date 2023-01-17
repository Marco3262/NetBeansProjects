/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negoziostampanti;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marco
 */
public class Sistema {
    private ReentrantLock mutex;
    private LinkedList<Processo> daStampare;
    private Semaphore notEmpty;
    private Semaphore stampanti;
    
    public Sistema(){
        this.daStampare = new LinkedList();
        this.notEmpty = new Semaphore(0);
        this.mutex = new ReentrantLock();
        this.stampanti = new Semaphore(4);
    }
    
    public void richiediStampa(int Pagine,Processo p)throws InterruptedException{
        this.mutex.lock();
        try{
            this.daStampare.add(p);
            this.notEmpty.release();
        
        }finally{
            
            this.mutex.unlock();
        }
    }
    
    public void liberaStampante(){
        this.stampanti.release();
    }
    
    public void concediPermesso(){
        Processo current = new Processo();
        Processo migliore = new Processo();
        int pagine_minori = 0;
        
        this.mutex.lock();
        try{
            for(int i = 0;i < this.daStampare.size();i++){
                current = this.daStampare.get(i);
            }
        }finally{
            this.mutex.unlock();
        }
    }
}
