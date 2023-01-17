/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concertovasco;

import java.util.Random;

/**
 *
 * @author marco
 */
public class Persona extends Thread {
    // se è vero senza foto / se è falso con foto
    private boolean stato;
    private Random rnd;
    private Varco myVarco;
    
    public Persona(boolean t,int i,Varco v){
        this.stato = t;
        this.setName("Persona_"+i+(this.getStato()?" senza biglietti":" con biglietti"));
        this.rnd = new Random();
        this.myVarco = v;
    }
    
    public boolean getStato(){
        return stato;
    }
    
    @Override
    public void run(){
        try{
            if(this.getStato())
                Thread.sleep(this.rnd.nextInt(501)+100);
            else
                Thread.sleep(this.rnd.nextInt(401)+200);
            this.myVarco.richiediAccesso(this);
            System.out.println(this.getName()+" è entrato!!");
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
}
