/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fabbricawilly2;

/**
 *
 * @author marco
 */
public class FabbricaWilly2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Fabbrica myFabbrica = new Fabbrica();
        WillyWonka willyWonka = new WillyWonka(myFabbrica);
        willyWonka.start();
        
        UmpaLumpa myUmpaLumpa[] = new UmpaLumpa[40];
        for(int i = 0;i < myUmpaLumpa.length;i++){
            myUmpaLumpa[i] = new UmpaLumpa(i,myFabbrica);
            myUmpaLumpa[i].start();
        }
        
        try{
            for(int i = 0;i < myUmpaLumpa.length;i++){
                myUmpaLumpa[i].join();
            }    
            
            willyWonka.interrupt();
            willyWonka.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }    
        System.out.println("Simulazione terminata!!!");
    }
    
}
