/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import paiement.entities.paiement;
import paiement.service.PaiementService;

/**
 * FXML Controller class
 *
 * @author SBS
 */

public class TBFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection cnx;
    public PreparedStatement pste;
    public ResultSet result;
    @FXML
    private AnchorPane AnchroPane;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private HBox HboxTop;

    @FXML
    private HBox HboxCenter;

    @FXML
    private HBox HboxCenterLeft;

    @FXML
    private AreaChart<?,?> AreaChart;

    @FXML
    private HBox HboxCenterRight;

    @FXML
    private BarChart<?, ?> BarChart;

    @FXML
    private HBox HBoxBot;

    @FXML
    private HBox HboxBotLeft;

    @FXML
    private HBox HboxBotCenter;

    @FXML
    private HBox HboxBotRight;
    
    
   /* @FXML
    public List<paiement>remplirArea() {
          List<paiement> paiements = new ArrayList<>();
          List<String> date = new ArrayList<>();
          List<String> prix = new ArrayList<>();
          XYChart.Series series = new XYChart.Series();
          
        String req = "SELECT prix,Date_paiement FROM paiement";       
        try{
        pste=cnx.prepareStatement(req);
        result=pste.executeQuery();
            while(result.next()){ 
                System.out.println("aaaaaaaaa");
                paiement p = new paiement();              
                p.setDate_paiement(result.getString(1));
                p.setPrix(result.getFloat(2));
                paiements.add(p);
               //series.getData().add(new XYChart.Data(result.getString("prix"),result.getString("Date_paiement")));
            }
            } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return paiements;
      //AreaChart.getData().add(series);
    }
*/
         @FXML
    public void remplirArea() {
        PaiementService PS=new PaiementService();
 List<paiement> paiements = new ArrayList<>(PS.remplirArea());
            // System.out.println("PAI : "+paiements);
             XYChart.Series series = new XYChart.Series();
             int i=0;
             for(paiement p:paiements)
                 
             {i++;
             String s=Integer.toString(i);
                 series.getData().add(new XYChart.Data(s,p.getPrix()));
                 System.out.println("PRIX  /  PAY : "+p.getPrix()+" "+p.getDate_paiement());
             }
              AreaChart.getData().add(series);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*
XYChart.Series series = new XYChart.Series();
series.getData().add(new XYChart.Data("1",21));
series.getData().add(new XYChart.Data("2",22));
series.getData().add(new XYChart.Data("3",23));
series.getData().add(new XYChart.Data("4",24));
series.getData().add(new XYChart.Data("5",25));
AreaChart.getData().add(series);
*///XYChart.Series series = new XYChart.Series();

        remplirArea();
        
    }   
}
