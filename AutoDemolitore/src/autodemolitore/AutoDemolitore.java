/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package autodemolitore;

/**
 *
 * @author marco
 */
public class AutoDemolitore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Autodemolizione myPlace = new Autodemolizione();
        Rottamatore rottamatore = new Rottamatore(myPlace);
        rottamatore.start();
        Autovettura auto[] = new Autovettura[60];
        for(int i = 0;i < auto.length;i++){
            auto[i] = new Autovettura(myPlace,i);
            auto[i].start();
        }
        try{
            for(int i = 0;i < auto.length;i++){

                auto[i].join();
            }
            rottamatore.interrupt();
            rottamatore.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Simulazione terminata!!!");
    }
    
}
