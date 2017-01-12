package avatar;

import java.util.*;
import java.io.*;
import java.net.*;

public class Match{

  ArrayList<PrintWriter> clientWriter;
  ArrayList<InetAddress> inetAdrr;
  ArrayList<Integer> clientAvatar;
  ArrayList<Integer> queueArray;
  
  BufferedReader reader;  
  
  public boolean warten = true;
  
  public int Player1_ava;
  public int Player2_ava;
  public PrintWriter Player1_wri;
  public PrintWriter Player2_wri;
  public int Player1_num;
  public int Player2_num;
  public int MatchNummer;
  
  public String Pl1_name;
  public int Pl1_str;
  public int Pl1_int;
  public int Pl1_ges;  
  public String Pl2_name;
  public int Pl2_str;
  public int Pl2_int;
  public int Pl2_ges;
  
  
  public double Pl1maxhp;
  public double Pl1curhp;
  
  public double Pl2maxhp;
  public double Pl2curhp;
                            
  public Thread m_Thread = new Thread(new Match_vorbereitung());
    
  public Match (){
    
  }
  
  public void start(int player1_ava, int player2_ava, PrintWriter player1_wri, PrintWriter player2_wri, int player1_num, int player2_num,int matchNummer){
    this.Player1_ava = player1_ava;      
    this.Player2_ava = player2_ava;
    this.Player1_wri = player1_wri;
    this.Player2_wri = player2_wri;
    this.Player1_num = player1_num;
    this.Player2_num = player2_num;
    this.MatchNummer = matchNummer;
    m_Thread.start();
  }
  
  public class Match_vorbereitung implements Runnable{
    public Match_vorbereitung(){
      
    }
    
    public void run(){
      try {          
        m_Thread.sleep(300);
      } catch(Exception e) {
        e.printStackTrace();
      } 
      
      Player1_wri.flush();
      Player1_wri.println("221");
      Player1_wri.flush();        
      Player2_wri.flush();
      Player2_wri.println("222");
      Player2_wri.flush();
      
      Player1_wri.println("23"); 
      Player1_wri.flush();   
      Player2_wri.println("23");
      Player2_wri.flush(); 
      switch (Player2_ava) {
        case 1: 
          Pl2_name = "Aang";
          break;
        case 2: 
          Pl2_name = "Korra";
          break; 
        case 3: 
          Pl2_name = "Roku";
          break;
        case 4: 
          Pl2_name = "Kyoshi";
          break;
        default: 
          
      }
      
      switch (Player1_ava) {
        case 1: 
          Pl1_name = "Aang";
          break;
        case 2: 
          Pl1_name = "Korra";
          break;
        case 3: 
          Pl1_name = "Roku";
          break;
        case 4: 
          Pl1_name = "Kyoshi";
          break;
        default: 
          
      } // end of switch
      
      while (warten == true) { 
      
      }     
      Pl1maxhp = Pl1_str*2; 
      Pl1curhp = Pl1maxhp;
      Pl2maxhp = Pl2_str*2; 
      Pl2curhp = Pl2maxhp;
      statsAnzeigen();
    }
  }
  
  public void sort(String s){
    
    char[] comp_message = s.toCharArray();
    String just_message = "";
    String messageCode = "";
    messageCode += comp_message[0];
    messageCode += comp_message[1]; 
    for (int i = 0;i<comp_message.length-2 ;i++ ) {
      just_message += comp_message[i+2];
    } 
    switch (messageCode) {
      case "00":
        System.out.println(s);
        break;
      case "01": 
        Pl1_str = Integer.parseInt(just_message);
        break;
      case "02": 
        Pl1_int = Integer.parseInt(just_message);
        break; 
      case "03": 
        Pl1_ges = Integer.parseInt(just_message);
        Player2_wri.println("23");
        Player2_wri.flush();
        break; 
      case "11": 
        Pl2_str = Integer.parseInt(just_message);
        break;
      case "12": 
        Pl2_int = Integer.parseInt(just_message);
        break;
      case "13": 
        Pl2_ges = Integer.parseInt(just_message);
        Player1_wri.println("23");
        Player1_wri.flush();
        warten = false;
        break;
      case "91":
        absendenPL2("91","");
        break;
      case "92":    
        absendenPL1("91","");
        break;
      default: 
        
    } 
  }
  
  public void absendenPL1(String messageCode, String message){
    String s = messageCode+message;
    Player1_wri.println(s);
    Player1_wri.flush();
  }
    
  public void absendenPL2(String messageCode, String message){
    String s = messageCode+message;
    Player2_wri.println(s);
    Player2_wri.flush();
  }
  
  public void absendenPL1_2(String messageCode, String message){
    String s = messageCode+message; 
    Player1_wri.println(s);
    Player1_wri.flush();
    Player2_wri.println(s);
    Player2_wri.flush();
    }
  
  public void statsAnzeigen(){
    absendenPL1("11","Avatar : "+Pl1_name);
    absendenPL1("11","Statuswerte: ");    
    absendenPL1("11","HP  : "+Pl1curhp+" / "+Pl1maxhp);
    absendenPL1("11","STR : "+Pl1_str);
    absendenPL1("11","INT : "+Pl1_int);
    absendenPL1("11","GES : "+Pl1_ges); 
    absendenPL2("11","Avatar : "+Pl2_name);
    absendenPL2("11","Statuswerte: ");    
    absendenPL2("11","HP  : "+Pl2curhp+" / "+Pl2maxhp);
    absendenPL2("11","STR : "+Pl2_str);
    absendenPL2("11","INT : "+Pl2_int);
    absendenPL2("11","GES : "+Pl2_ges);
    System.out.println("Match "+MatchNummer+" hat begonnen.");
    absendenPL1_2("23","");
  }
    
}