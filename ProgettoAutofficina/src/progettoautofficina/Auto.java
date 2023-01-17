/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettoautofficina;

import java.util.Random;

/**
 *
 * @author marco
 */
public class Auto extends Thread{
    
    // true in panne // false lieve
    private boolean stato;
    private Autofficina myAutofficina;
    private Random rnd;
    
    public Auto(boolean t, String name, Autofficina a){
        this.stato = t;
        this.setName(name+ (this.getStato() ? " in panne":" con danno lieve"));
        this.rnd = new Random();
        this.myAutofficina = a;
    }
    
    public boolean getStato() {
        return stato;
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep(this.rnd.nextInt(501)+100);
            this.myAutofficina.richiediRiparazione(this);
            System.out.println(this.getName() + " termina!!");
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
}
