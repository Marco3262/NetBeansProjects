/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcodivertimenti;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author marco
 */
public class Persona extends Thread{
    // Attributi funzionali
    private Giostra myGiostra;
    private Random rnd;
    
    
    public Persona(Giostra b, String nome){
        super(nome);
        this.myGiostra = b;
        this.rnd = new Random();
    }
    
    @Override
    public void run(){
        for(int i = 0;i < 5;i++){
            // simulo il tempo di arrivo
            try{
                
                Thread.sleep(rnd.nextInt(701)+100);
                System.out.println(super.getName()+" è arrivato!!");
            }catch(InterruptedException e){
                System.out.println(e);
            }
            
            this.myGiostra.cercaPosto(this);
            System.out.println(super.getName()+" ha trovato il posto");
            // se arrivo qui vuol dire che c'è posto
            // ora simulo il tempo della discesa
            try{
                Thread.sleep(200);
                System.out.println(super.getName()+" ha fatto un giro!!");
            }catch(InterruptedException e){
                System.out.println(e);
            }
            
            this.myGiostra.liberaPosto(this);
        }
        System.out.println(super.getName()+" ha terminato i giri!!");
    }
}
