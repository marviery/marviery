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
import projekpbol.*;



/**
 *
 * @author ASUS
 */
public class DBcustomer {
    private modelcustomer dt=new modelcustomer();    
    public modelcustomer getmodelcustomer(){ return(dt);}
    public void setmodelcustomer(modelcustomer s){ dt=s;}
    
    public ObservableList<modelcustomer>  Load() {
        try{
            ObservableList<modelcustomer> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idpelangganan,pesanan,nomeja from customer");
int i = 1;
            while (rs.next()) {
                modelcustomer d=new modelcustomer();
                d.setIdpelangganan(rs.getString("idpelangganan"));                
                d.setNomeja(rs.getString("pesanan"));
                d.setPesanan(rs.getString("nomeja"));                
                          
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from customer where idpelangganan = '" + id + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customer (idpelangganan,pesanan, nomeja) values (?,?,?)");
            con.preparedStatement.setString(1, getmodelcustomer().getIdpelangganan());           
            con.preparedStatement.setString(2, getmodelcustomer().getNomeja());
            con.preparedStatement.setString(3, getmodelcustomer().getPesanan());                  
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from customer where idpelangganan  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update customer set pesanan = ?, nomeja = ?  where  idpelangganan = ? ");
            con.preparedStatement.setString(1, getmodelcustomer().getIdpelangganan());
            con.preparedStatement.setString(2, getmodelcustomer().getNomeja());
            con.preparedStatement.setString(3, getmodelcustomer().getPesanan());
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
public ObservableList<modelcustomer>  CariCust(String id, String no) {
        try {    
            ObservableList<modelcustomer> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from customer WHERE idmember LIKE '" + id + "%' OR nama LIKE '" + no + "%'");
        int i = 1;
        while(rs.next()){
            modelcustomer d = new modelcustomer();
            d.setIdpelangganan(rs.getString("idpelangganan"));
            d.setNomeja(rs.getString("pesanan"));
            d.setPesanan(rs.getString("nomeja"));         
            
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
