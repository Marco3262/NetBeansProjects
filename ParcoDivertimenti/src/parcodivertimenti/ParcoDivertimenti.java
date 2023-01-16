/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcodivertimenti;

/**
 *
 * @author marco
 */
public class ParcoDivertimenti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Giostra myGiostra = new Giostra();
        Adulti gruppoAdulti[] = new Adulti[10];
        Bambini gruppoBambini[] = new Bambini[30];
        
        for(int i = 0; i < gruppoAdulti.length;i++){
            gruppoAdulti[i] = new Adulti("Adulto_"+i,myGiostra);
            gruppoAdulti[i].start();
        }
        
        for(int i = 0;i < gruppoBambini.length;i++){
            gruppoBambini[i] = new Bambini("Bambino_"+i,myGiostra);
            gruppoBambini[i].start();
        }
        
        try{
            for(int i = 0; i < gruppoAdulti.length;i++){
                gruppoAdulti[i].join();
            }
            for(int i = 0; i < gruppoBambini.length;i++){
                gruppoBambini[i].join();
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Simulazione Terminata!!");
    }
    
}
