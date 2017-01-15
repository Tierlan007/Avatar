package avatar;

import java.util.*;
import java.io.*;
import java.net.*;
import avatar.Attacken.*;

public class Match{

  ArrayList<PrintWriter> clientWriter;
  ArrayList<InetAddress> inetAdrr;
  ArrayList<Integer> clientAvatar;
  ArrayList<Integer> queueArray;
  
  BufferedReader reader;      
  public Attacken_identifikation atkIden = new Attacken_identifikation(); 
  
  public boolean warten = true;
  public boolean match_rounds;
  
  public ClientStart Client1;
  public ClientStart Client2;
  public PrintWriter Player1_wri;
  public PrintWriter Player2_wri;
  public int Player1_num;
  public int Player2_num;
  public int MatchNummer;  
  public int Round = 0;
  
  public String Pl1_name;
  public int Pl1_str;
  public int Pl1_int;
  public int Pl1_ges;  
  public String Pl2_name;
  public int Pl2_str;
  public int Pl2_int;
  public int Pl2_ges;
  public int synchro_wait = 0;
  
  public boolean zusatz_effekt_pl1 = false;
  public boolean zusatz_effekt_pl2 = false;
  
  public double pl1schaden = 0;
  public double pl2schaden = 0;
  public double pl1extra = 0;  
  public double pl2extra = 0;
  public int pl1_atk_nr = 0; 
  public int pl2_atk_nr = 0;
  public String pl1Atk = "MissingNo.";   
  public String pl2Atk = "MissingNo.";
  public int Player1_aufg = 0;    
  public int Player2_aufg = 0;
  
  public double Pl1maxhp;
  public double Pl1curhp;
  
  public double Pl2maxhp;
  public double Pl2curhp;
                            
  public Thread m_Thread = new Thread(new Match_vorbereitung());
    
  public Match (){
    
  }
  
  public void atk_identifier(int pl, int atk){               
    if (pl == 1) {
      atkIden.identifikation(this, atk, Pl1_str, Pl1_int, Pl1_ges, pl);
    } else {
      atkIden.identifikation(this, atk, Pl2_str, Pl2_int, Pl2_ges, pl);
    }
  }
  
  public void zusatz_identifier(int pl, int atk){     
    if (pl == 1) {
      atkIden.zusatz_identifikation(this, atk, Pl1_str, Pl1_int, Pl1_ges, pl);
    } else {
      atkIden.zusatz_identifikation(this, atk, Pl2_str, Pl2_int, Pl2_ges, pl);
    } 
  }
  
  public void start(PrintWriter player1_wri, PrintWriter player2_wri, int player1_num, int player2_num,int matchNummer){
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
      
      
      absendenPL1("22","1");
      absendenPL2("22","2");
      absendenPL1_2("23","");
      
      while (synchro_wait <2) {}
      synchro_wait = 0;         
      
      Pl1maxhp = Pl1_str*2; 
      Pl1curhp = Pl1maxhp;
      Pl2maxhp = Pl2_str*2; 
      Pl2curhp = Pl2maxhp;
       
      absendenPL1_2("23","");  
      statsAnzeigen();
      Matchbeginn();
    }
  }
  
  
  public void Matchbeginn(){
    match_rounds = true;
    synchro_wait = 0;
    while (match_rounds == true) { 
      Round++;
      zusatz_effekt_pl1 = false;
      zusatz_effekt_pl2 = false;
      while (synchro_wait <2) { 
      }
      synchro_wait = 0;  
      absendenPL1_2("81","");               
      while (synchro_wait <2) { 
      }
      synchro_wait = 0; 
      
      if (Player1_aufg == 1 && Player2_aufg == 0) {    
        absendenPL1_2("11",Pl1_name+" gab auf");  
        absendenPL1("92","");   
        absendenPL2("91",""); 
        match_rounds = false;
        
      } else if (Player1_aufg == 0 && Player2_aufg == 1){  
        absendenPL1_2("11",Pl2_name+" gab auf");  
        absendenPL1("91","");   
        absendenPL2("92","");  
        match_rounds = false;
        
      } else if (Player1_aufg == 1 && Player2_aufg == 1){
        absendenPL1_2("11","Unentschieden");  
        absendenPL1("90","");   
        absendenPL2("90",""); 
      } else {                                                                          
        
        if (Pl1_ges >= Pl2_ges) {
          atk_identifier(1, pl1_atk_nr);
          zusatz_identifier(1, pl1_atk_nr);
          
          if (Pl2curhp <= 0) {
            absendenPL1_2("11",Pl1_name+" hat gewonnen.");
            absendenPL1("91","");   
            absendenPL2("92",""); 
            match_rounds = false;
          } else if (Pl1curhp <= 0) {
            absendenPL1_2("11",Pl2_name+" hat gewonnen.");  
            absendenPL1("92","");   
            absendenPL2("91","");   
            match_rounds = false;
          } else {
            atk_identifier(2, pl2_atk_nr);
            zusatz_identifier(2, pl2_atk_nr);
            
            if (Pl2curhp <= 0) {
              absendenPL1_2("11",Pl1_name+" hat gewonnen.");
              absendenPL1("91","");   
              absendenPL2("92","");
              match_rounds = false;
            } else if (Pl1curhp <= 0) {
              absendenPL1_2("11",Pl2_name+" hat gewonnen.");  
              absendenPL1("92","");   
              absendenPL2("91",""); 
              match_rounds = false;
            } else {   
              absendenPL1_2("11","Runde "+Round);   
            } 
          } 
        } else {
          atk_identifier(2, pl2_atk_nr); 
          zusatz_identifier(2, pl2_atk_nr);
          
          if (Pl2curhp <= 0) {
            absendenPL1_2("11",Pl1_name+" hat gewonnen.");
            absendenPL1("91","");   
            absendenPL2("92","");  
            match_rounds = false;
          } else if (Pl1curhp <= 0) {
            absendenPL1_2("11",Pl2_name+" hat gewonnen.");  
            absendenPL1("92","");   
            absendenPL2("91",""); 
            match_rounds = false;
          } else {
            atk_identifier(1, pl1_atk_nr);
            zusatz_identifier(1, pl1_atk_nr);
            
            if (Pl2curhp <= 0) {
              absendenPL1_2("11",Pl1_name+" hat gewonnen.");
              absendenPL1("91","");   
              absendenPL2("92","");      
              match_rounds = false;
            } else if (Pl1curhp <= 0) {
              absendenPL1_2("11",Pl2_name+" hat gewonnen.");  
              absendenPL1("92","");   
              absendenPL2("91","");  
              match_rounds = false;
            } else {   
              absendenPL1_2("11","Runde "+Round);   
            } 
          } 
        } 
      }       
      absendenPL1_2("83",Player1_aufg+""+Player2_aufg); 
      while (synchro_wait <2) { 
      }
      synchro_wait = 0;  
      absendenPL1_2("81",""); 
      while (synchro_wait <2) { 
      }
      synchro_wait = 0;
    } 
    absendenPL1_2("23","");  
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
        break; 
      case "04": 
        Pl1_name = just_message;
        absendenPL2("23","");
        break;   
      case "11": 
        Pl2_str = Integer.parseInt(just_message);
        break;
      case "12": 
        Pl2_int = Integer.parseInt(just_message);
        break;
      case "13": 
        Pl2_ges = Integer.parseInt(just_message);
        break; 
      case "14": 
        Pl2_name = just_message;
        absendenPL1("23",""); 
        warten = false;
        break; 
      case "81":  
        if (just_message.equals("1")) {
          absendenPL1("11","Avatar : "+Pl1_name);
          absendenPL1("11","Statuswerte: ");    
          absendenPL1("11","HP  : "+Pl1curhp+" / "+Pl1maxhp);
          absendenPL1("11","STR : "+Pl1_str);
          absendenPL1("11","INT : "+Pl1_int);
          absendenPL1("11","GES : "+Pl1_ges); 
          absendenPL1("81","");
        } else {
          absendenPL2("11","Avatar : "+Pl2_name);
          absendenPL2("11","Statuswerte: ");    
          absendenPL2("11","HP  : "+Pl2curhp+" / "+Pl2maxhp);
          absendenPL2("11","STR : "+Pl2_str);
          absendenPL2("11","INT : "+Pl2_int);
          absendenPL2("11","GES : "+Pl2_ges);
        } 
        break;
      case "82": 
        synchro_wait++;
        break;
      case "83": 
        pl1_atk_nr = Integer.parseInt(just_message);
        break;
      case "84": 
        pl2_atk_nr = Integer.parseInt(just_message); 
        break;
      case "85": 
        pl1Atk = just_message;
        break;
      case "86": 
        pl2Atk = just_message;
        break;
      case "87":
        Player1_aufg = 1;
        break; 
      case "88":
        Player2_aufg = 1;
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
  
  public void setpl1schaden(double tmp){
    pl1schaden = tmp;
  }
  public void setZusatzpl1(boolean tmp){    
    zusatz_effekt_pl1 = tmp;
  }
 
  public void setpl1Atk(String tmp){
    pl1Atk = tmp;
  }
  
  public void healHP(int pl,double tmp){
    if (pl == 1) {
      Pl1curhp += tmp;
      if (Pl1curhp > Pl1maxhp) {
        Pl1curhp = Pl1maxhp;
      }
    } else {
      Pl2curhp += tmp;
      if (Pl2curhp > Pl2maxhp) {
        Pl2curhp = Pl2maxhp;
      }
    } 
  } 
    
   
  public String getPl1_name(){
    return Pl1_name;
  }
  public String getPl2_name(){
    return Pl2_name;
  }
  public double getPl1curhp(){
    return Pl1curhp;
  }
  public double getPl2curhp(){
    return Pl2curhp;
  }
  
  public void setpl2schaden(double tmp){
    pl2schaden = tmp;
  }
  public void setZusatzpl2(boolean tmp){    
    zusatz_effekt_pl2 = tmp;
  }  
  public void setpl2Atk(String tmp){
    pl2Atk = tmp;
  } 
  public void setPl1curhp(double d){
    Pl1curhp = d;
  }
  public void setPl2curhp(double d){
    Pl2curhp = d;
  }
  public void toggleWarten(){
    warten = false;
  }
}