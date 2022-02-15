/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facture.entities;
import java.sql.*;
/**
 *
 * @author ahmed
 */
public class facture {
    private int num_fac;
    private String Date_fac;
    private String file;

    public facture(int num_fac, String file) {
        this.num_fac = num_fac;
        this.file = file;
    }

    public facture(String file) {
        this.file = file;
    }

    public facture() {
    }

    public int getNum_fac() {
        return num_fac;
    }

    public void setNum_fac(int num_fac) {
        this.num_fac = num_fac;
    }

    public String getDate_fac() {
        return Date_fac;
    }

    public void setDate_fac(String Date_fac) {
        this.Date_fac = Date_fac;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "facture{" + "num_fac=" + num_fac + ", Date_fac=" + Date_fac + ", file=" + file + '}';
    }
    
    
    
    
    
   
}
