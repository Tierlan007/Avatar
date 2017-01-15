package avatar.Attacken;

import java.util.*;
import java.io.*;
import java.net.*;
import avatar.*;

public class Fire{
  
  public double tmp;
  
  private Match match;
  private double Str_t; 
  private double Int_t;
  private double Ges_t;
  private int plNr_t;
  private int atkNr_t;
  
  public Fire (Match m, int Str, int Int, int Ges, int plNr, int atkNr){
    setMatch(m);
    setStr_t(Double.parseDouble(Str+""));
    setInt_t(Double.parseDouble(Int+""));
    setGes_t(Double.parseDouble(Ges+""));
    setplNr_t(plNr);
    setatkNr_t(atkNr);
    
    if (atkNr_t == 2) {Firepunch();} 
  }
    
  public void Firepunch(){    
    tmp = (((Str_t/100)*70) + ((Ges_t/100)*50));
    if (plNr_t == 1) {             
      match.setZusatzpl1(false); 
      match.setpl1Atk("Firepunch"); 
      match.setPl2curhp(match.getPl2curhp()-tmp);  
      match.absendenPL1_2("11",match.Pl1_name+" setzt "+match.pl1Atk+" ein."); 
      match.absendenPL1_2("11",match.Pl2_name+" erleidet "+tmp+" Schadenspunkte");
    } else {   
      match.setZusatzpl2(false); 
      match.setpl2Atk("Firepunch");   
      match.setPl1curhp(match.getPl1curhp()-tmp);  
      match.absendenPL1_2("11",match.Pl2_name+" setzt "+match.pl2Atk+" ein."); 
      match.absendenPL1_2("11",match.Pl1_name+" erleidet "+tmp+" Schadenspunkte");
    }          
  } 
  
  public void setMatch (Match m){
    match = m;
  }
  public void setStr_t (Double d){
    Str_t = d;
  }
  public void setInt_t (Double d){
    Int_t = d;
  }
  public void setGes_t (Double d){
    Ges_t = d;
  }
  public void setplNr_t (int i){
    plNr_t = i;
  } 
  public void setatkNr_t (int i){
    atkNr_t = i;
  }   
  
}