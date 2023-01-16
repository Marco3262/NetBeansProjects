/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package attraversaponte;

/**
 *
 * @author marco
 */
public class Orango extends Thread{
    private boolean isAlive;
    private Ponte myPonte;
    
    public Orango(Ponte p){
        this.isAlive = true;
        this.myPonte = p;
    }
    
    @Override
    public void run(){
        try{
            while(isAlive){
                this.myPonte.gestisciAttraversamento();
            }
        }catch(InterruptedException e){
            isAlive = false;
            System.out.println(e);
        }
        System.out.println("L'orango ha terminato!!");
    }
    
}
