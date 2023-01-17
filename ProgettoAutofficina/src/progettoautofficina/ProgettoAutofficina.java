/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progettoautofficina;

/**
 *
 * @author marco
 */
public class ProgettoAutofficina {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Autofficina myAutofficina = new Autofficina();
        
        Meccanico meccanico[] = new Meccanico[5];
        for(int i = 0;i < 5;i++){
            meccanico[i] = new Meccanico(myAutofficina,"Meccanico"+i);
            meccanico[i].start();
        }
        
        Auto[] autoLievi = new Auto[45];
        for(int i = 0; i < 45;i++){
            autoLievi[i] = new Auto(false,"Auto_"+i,myAutofficina);
            autoLievi[i].start();
        }
        
        Auto autoInPanne[] = new Auto[15];
        for(int i = 0; i < 15;i++){
            autoInPanne[i] = new Auto(true,"Auto_"+i,myAutofficina);
            autoInPanne[i].start();
        }
        
        try{
            for(int i = 0; i < 45;i++){
                autoLievi[i].join();
            }
            
            for(int i = 0; i < 15;i++){
                autoInPanne[i].join();
            }
            
            for(int i = 0;i < 5;i++){
                meccanico[i].interrupt();
                meccanico[i].join();
            }
            
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        System.out.println("Simulazione terminata!!!");
    }
    
}
