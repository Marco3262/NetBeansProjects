/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package concertovasco;

/**
 *
 * @author marco
 */
public class ConcertoVasco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Varco myVarco = new Varco();
        Controllore[] controllore = new Controllore[4];
        for(int i = 0;i < controllore.length;i++){
            controllore[i] = new Controllore(i,myVarco);
            controllore[i].start();
        }
        
        Persona[] persone = new Persona[60];
        for(int i = 0; i < persone.length; i++){
            if(i < 12)
                persone[i] = new Persona(false,i,myVarco);
            else
                persone[i] = new Persona(true,i,myVarco);
            persone[i].start();
        }
        
        try{
            for(int i = 0; i < persone.length; i++){
                persone[i].join();
            }
            
            for(int i = 0;i < controllore.length;i++){
                controllore[i].interrupt();
                controllore[i].join();
            }
            
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        System.out.println("Simulazione terminata!!");
    }
    
}
