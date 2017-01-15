package avatar;

import java.util.*;
import java.io.*;
import java.net.*;
import avatar.Attacken.*;

public class Attacken_identifikation{
  
  public Wasser water;  
  public Wasser_Zusatz water_extra;
  
  public Fire fire;  
  public Fire_Zusatz fire_extra;
  
  public Admin_atk admin;
  public Admin_Zusatz admin_extra;
  
  public Attacken_identifikation(){
    
  }
  
  public void identifikation(Match m, int nr,int Str, int Int, int Ges, int plNr){
    if (nr == 0) {}
    else if (nr == 1) {water = new Wasser(m, Str, Int, Ges, plNr, nr);} 
    else if (nr == 2) {fire = new Fire(m, Str, Int, Ges, plNr, nr);}    
    else if (nr == 900) {admin = new Admin_atk(m, Str, Int, Ges, plNr, nr);} 
    }                                                                      
    
    public void zusatz_identifikation (Match m, int nr,int Str, int Int, int Ges, int plNr){
    if (nr == 0) {}
    else if (nr == 1) {water_extra = new Wasser_Zusatz(m, Str, Int, Ges, plNr, nr);}  
    else if (nr == 2) {fire_extra = new Fire_Zusatz(m, Str, Int, Ges, plNr, nr);}   
    else if (nr == 900) {admin_extra = new Admin_Zusatz(m, Str, Int, Ges, plNr, nr);}  
    }
    }