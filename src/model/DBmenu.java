/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DBmenu {
    private modelmenu dt=new modelmenu();    
    public modelmenu getmodelmenu(){ return(dt);}
    public void setmodelmenu(modelmenu s){ dt=s;}
    
    public ObservableList<modelmenu>  Load() {
        try{
            ObservableList<modelmenu> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select code,namamakanan,harga from menu");
int i = 1;
            while (rs.next()) {
                modelmenu d=new modelmenu();
                d.setCode(rs.getString("code"));                
                d.setHarga(rs.getString("namamakanan"));
                d.setNamamakanan(rs.getString("harga"));                
                          
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from menu where code = '" + id + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into menu (code,namamakanan,harga) values (?,?,?)");
            con.preparedStatement.setString(1, getmodelmenu().getCode());           
            con.preparedStatement.setString(2, getmodelmenu().getHarga());
            con.preparedStatement.setString(3, getmodelmenu().getNamamakanan());                  
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from menu where code  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update menu set harga = ?, namamakanan = ?  where  code = ? ");
            con.preparedStatement.setString(1, getmodelmenu().getCode());
            con.preparedStatement.setString(2, getmodelmenu().getHarga());
            con.preparedStatement.setString(3, getmodelmenu().getNamamakanan());
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
public ObservableList<modelmenu>  CariMenu(String id, String no) {
        try {    
            ObservableList<modelmenu> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from nama WHERE code LIKE '" + id + "%' OR namamakanan LIKE '" + no + "%'");
        int i = 1;
        while(rs.next()){
            modelmenu d = new modelmenu();
            d.setCode(rs.getString("code"));
            d.setHarga(rs.getString("namamakanan"));
            d.setNamamakanan(rs.getString("harga"));         
            
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
