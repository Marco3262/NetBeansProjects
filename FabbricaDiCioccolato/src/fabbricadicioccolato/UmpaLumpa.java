/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabbricadicioccolato;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author marco
 */
public class UmpaLumpa extends Thread {
    
    private int semi;
    private Fabbrica myFabbrica;
    private Random rnd;
    private Condition myCon;
    // costruttore
    
    public UmpaLumpa(Fabbrica f,String name){
        super(name);
        this.myFabbrica = f;
        this.rnd = new Random();
        semi = this.rnd.nextInt(11)+10;
    }
    
    public int getSemi(){
        return semi;
    }
    
    public int getTempoNecessario(){
        return(this.getSemi()*10);
    }

    public void attende() {
        try{
            this.myCon.await();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }

    public void sveglia() {
        this.myCon.signal();
    }
    
    @Override
    public void run(){
        myFabbrica.richiediForno(this);
        
        // se sono qui è arrivato il mio turno
        // ora simulo il tempo di utilizzo
        try{
            Thread.sleep(this.getTempoNecessario());
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        myFabbrica.liberaForno();
        System.out.println(super.getName()+" termina!!");
        
    }
    
}
