/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concertovasco;

/**
 *
 * @author marco
 */
public class Controllore extends Thread{
    private Varco myVarco;
    
    public Controllore(int i,Varco v){
        this.myVarco = v;
        this.setName("Controllore"+i);
    }
    
    @Override
    public void run(){
        boolean isAlive = true;
        try{
            while(isAlive){
                this.myVarco.controllaPersona();
            }
        }catch(InterruptedException e){
            System.out.println(e);
            System.out.println(this.getName()+" termina!!");
            isAlive = false;
        }
    }
}
