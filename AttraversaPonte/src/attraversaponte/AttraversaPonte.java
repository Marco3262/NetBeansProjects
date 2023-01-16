/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package attraversaponte;

/**
 *
 * @author marco
 */
public class AttraversaPonte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ponte myPonte = new Ponte();
        
        Orango orango = new Orango(myPonte);
        orango.start();
        Babbuino[] babbuini = new Babbuino[40];
        
        for(int i = 0;i < babbuini.length;i++){
            babbuini[i] = new Babbuino(myPonte,i);
            babbuini[i].start();
        }
        
        try{
            for(int i = 0;i < babbuini.length;i++){
                babbuini[i].join();
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        orango.interrupt();
        try{
            orango.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Simulazione terminata!!");
    }
    
}
