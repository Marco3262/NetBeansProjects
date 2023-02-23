/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabbricawilly2;

/**
 *
 * @author marco
 */
public class WillyWonka extends Thread{
    private Fabbrica myFabbrica;
    
    public WillyWonka(Fabbrica f){
        this.myFabbrica = f;
    }
    
    @Override
    public void run(){
        boolean isAlive = true;
        while(isAlive){
            try{
                this.myFabbrica.gestisciRichieste();
            }catch(InterruptedException e){
                System.out.println(e);
                isAlive = false;
            }
        
        
        }
        System.out.println("Willy Wonka ha gestito tutte le richieste!!");
    }
}
