package avatar;

import avatar.Character.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class ClientStart{

  BufferedReader reader;
  PrintWriter writer;
  Socket sock;
  
  public ClientMatch clMa;
  
  public int player_num;
  public int queue;
  private int winner = 0;                //1 = gewonnen 2 = verloren 0 = nicht zugewiesen
  public int warten = 0;
  public Avatare myAvatar;
  
  public void los(){       
    warten = 1;
    netzwerkVerbinden();  
    Thread eingehend = new Thread(new inReader());
    eingehend.start();
    writer.flush();
    
    boolean loop1 = true;
    boolean loop2 = true;
    int tmp = 0;
    Scanner scan = new Scanner(System.in);
    
    while (loop1 == true) { 
      String eingabe = "";  
      while (warten == 1) { 
      } 
      warten = 1;
      
      System.out.println("Waehlen sie ihren Avatar: ");
      System.out.println("<1> Aang      (Allrounder)");
      System.out.println("<2> Korra     (Inteligenz)");
      System.out.println("<3> Roku      (Staerke)");
      System.out.println("<4> Kyoshi    (Geschik)");
      System.out.println("Type 'exit' to exit the game");
      while (loop2 == true) { 
        eingabe = scan.next();
        if (eingabe.equals("1") || eingabe.equals("2") || eingabe.equals("3") || eingabe.equals("4")) {
          writer.println("21"+eingabe);
          writer.flush();
          loop2 = false;
          Avatar_stats(eingabe);
        }else if (eingabe.equals("exit")){
          System.exit(0);
        }else {
          System.out.println("Bitte geben sie einen richtigen Wert ein");
        } 
      } 
      queue = 2;
      tmp = Integer.parseInt(eingabe);
      writer.println("31"+tmp);
      writer.flush();
      System.out.println("Ein Gegner wird für sie gesucht, bitte warten");
      while (queue == 2) { 
      } 
      System.out.println("Match gefunden");
      queue = 1; 
      while (warten == 1) { 
      } 
      warten = 1; 
      System.out.println("player_num : "+player_num);
      
      if (player_num == 1) {
        outWriter("01",""+myAvatar.getSTR());
        outWriter("02",""+myAvatar.getINT());
        outWriter("03",""+myAvatar.getGES()); 
        outWriter("04",""+myAvatar.getNAME());
      } else {
        while (warten == 1) { 
        }            
        outWriter("11",""+myAvatar.getSTR());
        outWriter("12",""+myAvatar.getINT());
        outWriter("13",""+myAvatar.getGES());
        outWriter("14",""+myAvatar.getNAME());
      } 
      while (warten == 1) { 
      }
      warten= 1; 
      outWriter("82","");
      while (warten == 1) { 
      }
      warten= 1;
      clMa = new ClientMatch(this, player_num);
      while (warten == 1) { 
      }
      warten= 1;  
      System.out.println("Out of Match");
      loop1 = false;
    } 
  }
  
  public void Avatar_stats(String tmp){
    Random rng = new Random();
    
    if (tmp.equals("1")) {
      myAvatar = new Aang(this);
    }else if (tmp.equals("2")) { 
      myAvatar = new Korra(this);
    }else if (tmp.equals("3")) {
      myAvatar = new Roku(this);
    }else if (tmp.equals("4")) { 
      myAvatar = new Kyoshi(this);
    }
  }
  
  public void netzwerkVerbinden(){
    try {
      sock = new Socket("127.0.0.1",4242);
      InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
      reader = new BufferedReader(streamReader);
      writer = new PrintWriter(sock.getOutputStream());
      System.out.println("Verbindung steht");
    } catch(Exception e) {
      System.out.println("keine Verbindung zum Server möglich\nVersuchen sie es später erneut");
      System.exit(0);
    } 
  }
  
  public class inReader implements Runnable{
    public inReader(){
      
    }
      
    public void run(){
      String nachricht;
      try {
        while ((nachricht = reader.readLine()) != null) { 
          sort(nachricht);
        } 
        
      } catch(Exception e) {}   
    }
  }
  
  public void outWriter(String messageCode,String message){
    String s = messageCode + message;
    writer.println(s);
    writer.flush();
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
      case "01": 
        System.out.println(just_message);
        break;
      case "11":
        System.out.println(just_message);
        break;
      case "21":          
        writer.println(just_message);
        writer.flush();
        
        break;
      case "22":
        player_num = Integer.parseInt(just_message);
        break;
      case "23":
        warten = 0;
        break;  
      case "24":
        queue = 1;
        
        break; 
      case "81":
        clMa.setWarten(0);
        break; 
      case "82":
        
        break; 
      case "83":
        if (just_message.equals("10")) { 
          if (player_num == 1) {
            winner = 2;
          } else {
            winner = 1;
          }   
          clMa.setWarten(0);
        } else if (just_message.equals("10")) { 
          if (player_num == 1) {
            winner = 1;
          } else {
            winner = 2;
          } 
          clMa.setWarten(0);
        } else if (just_message.equals("11")) { 
          winner = 3;                 
          clMa.setWarten(0);
        } else if (just_message.equals("00")) {
          clMa.setWarten(0);
        } 
        break;
      case "90":
        winner = 3;
        break;  
      case "91":
        winner = 1;
        break;  
      case "92":  
        winner = 2;
        break;  
      default: 
        
    } 
    
  }
  
  public void setWinner(int i){winner = i;}
  public int getWinner(){return winner;}
  public Avatare getAvatar(){return myAvatar;}
  public ClientStart getClient(){return this;}
  
  public static void main(String[] args){
    new ClientStart().los();
  }
}