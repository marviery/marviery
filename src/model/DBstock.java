/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projekpbol.Koneksi;

/**
 *
 * @author ASUS
 */
public class DBstock {
     private modestock dt=new modestock();    
    public modestock getmodestock(){ return(dt);}
    public void setmodestock(modestock s){ dt=s;}
    
    public ObservableList<modestock>  Load() {
        try{
            ObservableList<modestock> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select code,namabarang,jumlah,tanggalmasuk from stock");
int i = 1;
            while (rs.next()) {
                modestock d=new modestock();
                d.setCode(rs.getString("code"));                
                d.setJumlah(rs.getString("namabarang"));
                d.setNamabarang(rs.getString("jumlah"));                
                d.setTanggalmasuk(rs.getString("tanggalmasuk"));            
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
 public int validasi(String id) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from stock where code = '" + id + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
   
 public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customer (code,namabarang, jumlah,tanggalmasuk) values (?,?,?,?)");
            con.preparedStatement.setString(1, getmodestock().getCode());           
            con.preparedStatement.setString(2, getmodestock().getJumlah());
            con.preparedStatement.setString(3, getmodestock().getNamabarang());
            con.preparedStatement.setString (4, getmodestock().getTanggalmasuk());  
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
   
public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from stock where code  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }

public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update stock set tanggalmasuk = ?, namabarang = ?, jumlah = ? where  code = ? ");
           con.preparedStatement.setString(1, getmodestock().getCode());           
            con.preparedStatement.setString(2, getmodestock().getJumlah());
            con.preparedStatement.setString(3, getmodestock().getNamabarang());
             con.preparedStatement.setString (4, getmodestock().getTanggalmasuk());  
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        
        }
public ObservableList<modestock>  CariStock(String id, String no) {
        try {    
            ObservableList<modestock> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from stock WHERE code LIKE '" + id + "%' OR nama LIKE '" + no + "%'");
        int i = 1;
        while(rs.next()){
            modestock d = new modestock();
            d.setCode(rs.getString("code"));
            d.setNamabarang(rs.getString("namabarang"));
            d.setJumlah(rs.getString("jumlah"));         
            d.setTanggalmasuk(rs.getString("tanggalmasuk"));    
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    

}
    
}
