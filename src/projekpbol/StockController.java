/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekpbol;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StockController implements Initializable {

    @FXML
    private TableView<modestock> tabelstock;
    @FXML
    private Button ttambah;
    @FXML
    private Button tubah;
    @FXML
    private Button tdelete;
    @FXML
    private Button texit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     showdata();
    }    
    
    public void showdata(){
        ObservableList<modestock> data=FXMLDocumentController.datastock.Load();
        if(data!=null){            
            tabelstock.getColumns().clear();            
            tabelstock.getItems().clear();
            TableColumn col=new TableColumn("code");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, String>("code"));
            tabelstock.getColumns().addAll(col);
            col=new TableColumn("namabarang");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, String>("namabarang"));
            tabelstock.getColumns().addAll(col);
            col=new TableColumn("jumlah");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, String>("jumlah"));
            tabelstock.getColumns().addAll(col);
            col=new TableColumn("tanggalmasuk");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, Date>("tanggalmasuk"));
            tabelstock.getColumns().addAll(col);
        

            
            tabelstock.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tabelstock.getScene().getWindow().hide();;
        }                
    }

    @FXML
    private void tambahclick(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Inputstock.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
    }

    @FXML
    private void ubahclick(ActionEvent event) {
    }

    @FXML
    private void deleteclick(ActionEvent event) {
         modestock s= new modestock();       
        s=tabelstock.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.datacustomer.delete(s.getCode())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
    }
    }

    @FXML
    private void exitclick(ActionEvent event) {
        texit.getScene().getWindow().hide();
    }
    
}
