/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekpbol;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuController implements Initializable {

    @FXML
    private Button saves;
    @FXML
    private Button tupdate;
    @FXML
    private Button tdelete;
    @FXML
    private Button texit;
    @FXML
    private TableView<modelmenu> tabelmenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showdata();
    }    
    
    public void showdata(){
        ObservableList<modelmenu> data=FXMLDocumentController.datamenu.Load();
        if(data!=null){            
            tabelmenu.getColumns().clear();            
            tabelmenu.getItems().clear();
            TableColumn col=new TableColumn("code");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, String>("code"));
            tabelmenu.getColumns().addAll(col);
            col=new TableColumn("namamakanan");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, String>("namamakanan"));
            tabelmenu.getColumns().addAll(col);
            col=new TableColumn("harga");
            col.setCellValueFactory(new PropertyValueFactory<modelmenu, String>("harga"));
            tabelmenu.getColumns().addAll(col);
        

            
            tabelmenu.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tabelmenu.getScene().getWindow().hide();;
        }                
    }

    @FXML
    private void saveclick(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Inputmenu.fxml"));    
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
    private void updateclick(ActionEvent event) {
    }

    @FXML
    private void deleteclick(ActionEvent event) {
         modelmenu s= new modelmenu();       
        s=tabelmenu.getSelectionModel().getSelectedItem();
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

    
