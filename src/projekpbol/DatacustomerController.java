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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DatacustomerController implements Initializable {

    @FXML
    private TableView<modelcustomer> tabelcustomer;
    @FXML
    private Button ttambah;
    @FXML
    private Button tupdate;
    @FXML
    private Button thapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showdata();
    }    
    
    public void showdata(){
        ObservableList<modelcustomer> data=FXMLDocumentController.datacustomer.Load();
        if(data!=null){            
            tabelcustomer.getColumns().clear();            
            tabelcustomer.getItems().clear();
            TableColumn col=new TableColumn("idpelangganan");
            col.setCellValueFactory(new PropertyValueFactory<modelcustomer, String>("idpelangganan"));
            tabelcustomer.getColumns().addAll(col);
            col=new TableColumn("pesanan");
            col.setCellValueFactory(new PropertyValueFactory<modelcustomer, String>("pesanan"));
            tabelcustomer.getColumns().addAll(col);
            col=new TableColumn("nomeja");
            col.setCellValueFactory(new PropertyValueFactory<modelcustomer, String>("nomeja"));
            tabelcustomer.getColumns().addAll(col);
        

            
            tabelcustomer.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tabelcustomer.getScene().getWindow().hide();;
        }                
    }
    

    @FXML
    private void tambahclick(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Inputcostumer.fxml"));    
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
    private void hapusclick(ActionEvent event) {
         modelcustomer s= new modelcustomer();       
        s=tabelcustomer.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.datacustomer.delete(s.getIdpelangganan())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
    }
    
}

}
