/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabbricadicioccolato;

/**
 *
 * @author marco
 */
public class WillyWonka extends Thread {
    private Fabbrica myFabbrica;
    
    public WillyWonka(String name,Fabbrica f){
        super(name);
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
        System.out.println(super.getName()+" termina!!");
    }
}
