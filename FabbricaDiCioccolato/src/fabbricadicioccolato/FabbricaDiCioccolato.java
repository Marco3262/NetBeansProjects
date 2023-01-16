/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fabbricadicioccolato;

/**
 *
 * @author marco
 */
public class FabbricaDiCioccolato {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Fabbrica myFabbrica = new Fabbrica();
        WillyWonka Willy = new WillyWonka("Willy Wonka",myFabbrica);
        Willy.start();
        UmpaLumpa myUmpa[] = new UmpaLumpa[40];
        for(int i = 0; i < myUmpa.length;i++){
            myUmpa[i] = new UmpaLumpa(myFabbrica,"Umpalumpa_"+i);
            myUmpa[i].start();
        }
        
        try{
            for(int i = 0; i < myUmpa.length;i++)
                myUmpa[i].join();
            
        }catch(InterruptedException e){
            System.out.println(e);
        }
        Willy.interrupt();
        try{
            Willy.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Simulazione terminata!!");
    }
    
}
