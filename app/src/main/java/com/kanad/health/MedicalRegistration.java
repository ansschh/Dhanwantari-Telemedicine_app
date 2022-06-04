package com.kanad.health;

public class MedicalRegistration {
    String Reg_Numer ;
    String Council_Name ;
    String Reg_Year ;
    String Doctor_Name;

    public String getReg_Numer() {
        return Reg_Numer;
    }

    public void setReg_Numer(String reg_Numer) {
        Reg_Numer = reg_Numer;
    }

    public String getCouncil_Name() {
        return Council_Name;
    }

    public void setCouncil_Name(String council_Name) {
        Council_Name = council_Name;
    }

    public String getReg_Year() {
        return Reg_Year;
    }

    public void setReg_Year(String reg_Year) {
        Reg_Year = reg_Year;
    }

    public String getDoctor_Name() {
        return Doctor_Name;
    }

    public void setDoctor_Name(String doctor_Name) {
        Doctor_Name = doctor_Name;
    }

    public MedicalRegistration(String reg_Numer, String council_Name, String reg_Year, String doctor_Name) {
        Reg_Numer = reg_Numer;
        Council_Name = council_Name;
        Reg_Year = reg_Year;
        Doctor_Name = doctor_Name;
    }

    public MedicalRegistration() {
    }
}
