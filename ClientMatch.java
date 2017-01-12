package avatar;

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
  
  public int warten = 1;
  
  public ClientMatch(ClientStart clSt, int str, int inte, int ges, int playNum){  
    CLIENT = clSt;
    
    double schaden;
    
    Str_ava = str;
    Int_ava = inte;
    Ges_ava = ges;
    
    
    boolean loop1 = true;
    boolean loop2 = true;
    
    Atk ava;
    ava = new Aang();
    if (CLIENT.NAME_AVA == "Aang") {
      ava = new Aang();
    } 
    
    int winner = 0;           //1 = gewonnen 2 = verloren 0 = nicht zugewiesen
    
    Scanner scan = new Scanner(System.in);
    
    String eingabehaupt;
    String eingabeSekun;
    
    while (loop1 == true) { 
      System.out.println("\n\n\n\n------------------------------------------------------------------------------\n\n");
      System.out.println("Was möchten sie machen?");
      System.out.println("<1> Attacken");
      System.out.println("<2> Verteidigen");
      System.out.println("<0> Aufgeben");
      
      while (loop2 == true) { 
        System.out.print("Eingabe : ");
        eingabehaupt = scan.next();
        System.out.println();
        if (eingabehaupt.equalsIgnoreCase("1")) {
          schaden = ava.Aang().Attacken(Str_ava,Int_ava, Ges_ava);
        }else if(eingabehaupt.equalsIgnoreCase("2")){
          
        }else if(eingabehaupt.equalsIgnoreCase("0")){
          System.out.println("Sind sie sicher das sie aufgeben möchten?");
          eingabeSekun = scan.next();
          if (eingabeSekun.equalsIgnoreCase("ja")){
            loop1 = false;
            winner = 2;
          }
          loop2 = false;  
        }
      } 
      warten = 1;
      
      while (warten == 1) { 
      }   
      warten = 1;
      
    } 
    
    if (winner == 1) {                //gewonnen
      
    }else if (winner == 2){           //verloren
      CLIENT.setWinner(2);
    }else {                           //ungültig
      
    }   
    
  }
    
  public void setWarten(int i){
    warten = i;
    }  
    
}