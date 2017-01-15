package avatar;

import avatar.Character.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class ClientMatch{

  ClientStart CLIENT; 

  public int Str_ava;
  public int Int_ava;
  public int Ges_ava; 
  public int player_num;
  public int queue;
  public int fight = 1;
  
  public int warten = 1;
  
  public ClientMatch(ClientStart clSt, int playNum){  
    CLIENT = clSt;
    Str_ava = CLIENT.getAvatar().getSTR();
    Int_ava = CLIENT.getAvatar().getINT();
    Ges_ava = CLIENT.getAvatar().getGES();
    player_num = playNum;
    
    Thread t = new Thread(new Match_ablauf());
    t.start();
    
  }
  
  public class Match_ablauf implements Runnable{
    
    boolean loop1 = true;
    boolean loop2 = true;  
    int atk_nr = 0;
    int aufgegeben = 0;
    
    public Match_ablauf(){
      
    }
      
    public void run(){
      int winner = 0;           //1 = gewonnen 2 = verloren 0 = nicht zugewiesen
      
      while (fight == 1) { 
        
        Scanner scan = new Scanner(System.in);
        
        String eingabehaupt;
        String eingabeSekun;
        
        while (loop1 == true) {
          loop2 = true; 
          System.out.println("\n\n------------------------------------------------------------------------------\n\n");
          System.out.println("Was möchten sie machen?");
          System.out.println("<1> Attacken");
          System.out.println("<2> Verteidigen");
          System.out.println("<3> Stats erneut sehen");
          System.out.println("<0> Aufgeben"); 
          
          while (loop2 == true) { 
            System.out.print("Eingabe : ");
            eingabehaupt = scan.next();
            System.out.println();
            if (eingabehaupt.equalsIgnoreCase("1")) {
              atk_nr = CLIENT.getAvatar().Attacken();  
              if (atk_nr == 0) {
                System.out.println("\n\n------------------------------------------------------------------------------\n\n");
                System.out.println("Was möchten sie machen?");
                System.out.println("<1> Attacken");
                System.out.println("<2> Verteidigen");
                System.out.println("<3> Stats erneut sehen");
                System.out.println("<0> Aufgeben"); 
              } else {
                if (player_num == 1) {
                  CLIENT.outWriter("83",""+atk_nr);
                } else {
                  CLIENT.outWriter("84",""+atk_nr);
                }        
                loop2 = false;
              } 
              
            }else if(eingabehaupt.equalsIgnoreCase("2")){
              
              loop2 = false;
            }else if(eingabehaupt.equalsIgnoreCase("3")){
              CLIENT.outWriter("81",String.valueOf(player_num));
              while (warten == 1) { 
              }   
              warten = 1;
            }else if(eingabehaupt.equalsIgnoreCase("0")){
              System.out.println("Sind sie sicher das sie aufgeben möchten?");
              eingabeSekun = scan.next();
              if (eingabeSekun.equalsIgnoreCase("y")){
                loop1 = false;
                System.out.println("Sie haben aufgegeben");
                aufgegeben = 1;
              }
              loop2 = false;  
              
            }  
          }
          System.out.println("Ihr Gegner wählt seine Attacke");
          System.out.println("\n\n------------------------------------------------------------------------------\n\n");
          CLIENT.outWriter("82","");
          while (warten == 1) { 
          }   
          warten = 1;
          if (aufgegeben == 0) {  
          } else {
            if (player_num == 1) {
              CLIENT.outWriter("87","");
            } else {
              CLIENT.outWriter("88","");
            }
          }   
          CLIENT.outWriter("82","");
          while (warten == 1) { 
            
          } 
          warten = 1;                  
          CLIENT.outWriter("82","");
          do{ 
            if (CLIENT.getWinner() == 1 || CLIENT.getWinner() == 2|| CLIENT.getWinner() == 3) {
              fight = 2;
              loop1 = false;
            } 
          }while (warten == 1); 
          warten=1;       
          CLIENT.outWriter("82","");
          
          
          
          
          
        } 
        
        if (CLIENT.getWinner() == 1) {                //gewonnen
          System.out.println("Du hast Gewonnen");
        }else if (CLIENT.getWinner() == 2){           //verloren
          System.out.println("Du hast Verloren");
        }else {                                     //ungültig
          System.out.println("Unentschieden");
        }   
        
        CLIENT.outWriter("82","");
      }
      
    }
       
    
  } 
  
  public void setWarten(int i){
    warten = i;
  }
}