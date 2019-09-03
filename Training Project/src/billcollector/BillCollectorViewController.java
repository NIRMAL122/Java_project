package billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;

public class BillCollectorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtbill;
    
    @FXML
    private ImageView paise;
    
    @FXML
    private Button btnupdate;
    
    @FXML
    private ImageView errmobile;

    String totalbill;
    @FXML
    void doshowamount(ActionEvent event) 
    {
    	if(txtmobile.getText().equals(""))
    	{
    		errmobile.setVisible(true);
    	}
    	else
    	{
    		errmobile.setVisible(false);
    	}
    	
    	if(!txtmobile.getText().equals(""))
    	{
    	playsound();
    	try {
			pst=con.prepareStatement("select total from generatebilltable where month=? and year=? and mobileno=?");
			pst.setString(1, combomonth.getSelectionModel().getSelectedItem());
			pst.setString(2, comboyear.getSelectionModel().getSelectedItem());
			pst.setString(3, txtmobile.getText());
			
			ResultSet rs=  pst.executeQuery();
			
			while(rs.next())
			{
				 totalbill=rs.getString(1);
			}
			txtbill.setText(totalbill);
			
			if(!txtbill.getText().equals(""))
	    	{
	    		btnupdate.setDisable(false);
	    		
	    	}
			
		} 
    	catch (SQLException e)
    	{
			
			e.printStackTrace();
		}
    	}

    }

    @FXML
    void doupdate(ActionEvent event) 
    {
    	
    	
    	
    	
    	playsound();
    	try {
			pst=con.prepareStatement("update generatebilltable set recieve='1' where month=? and year=? and mobileno=?");
			pst.setString(1, combomonth.getSelectionModel().getSelectedItem());
			pst.setString(2, comboyear.getSelectionModel().getSelectedItem());
			pst.setString(3, txtmobile.getText());
			
			pst.executeUpdate();
			System.out.println("done");
			doAlert("sucessfully ---UPDATED---");
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    	

    }
    
    @FXML
    void dozoomin(MouseEvent event) 
    {
    	paise.setFitWidth(200);
    	paise.setFitHeight(150);

    }

    @FXML
    void dozoomout(MouseEvent event)
    {
    	paise.setFitWidth(230);
    	paise.setFitHeight(180);

    }
    
    //========================= establish connection =============================
    Connection con;
    PreparedStatement pst;
    void doconnection()
    {
    	try 
    	{
			Class.forName("com.mysql.jdbc.Driver");    //load driver
			System.out.println("driver loaded");
	    	con=DriverManager.getConnection("jdbc:mysql://localhost/milkmandb","root","1227");   //establish connection
	    	System.out.println("connection established");
		} 
    	catch (Exception e) 
    	{
			
			e.printStackTrace();
		}
    }	
    	
   //============================ click sound ===================================== 			
    
    URL url;
    AudioClip audio;
    
    void playsound()
    {
    	url=getClass().getResource("Button1.wav");
    	audio=new AudioClip(url.toString());
    	audio.play();
    	
    }
    
    //=============================== do alert msg show =================================
    void doAlert(String msg)
    {
    	Alert a1=new Alert(AlertType.INFORMATION);
    	a1.setHeaderText("INFO");
    	a1.setContentText(msg);
    	a1.show();
    }
    //==========================================================================

    @FXML
    void initialize()
    {
    	
    	doconnection();
    	
    	ArrayList<String>months=new ArrayList<String>(Arrays.asList("select","Jan","Feb","March","April","May","June","July","August","Sept","Oct","Nov","Dec"));
    	combomonth.getItems().addAll(months);
    	combomonth.getSelectionModel().select(0);
    	
    	ArrayList<String>year=new ArrayList<String>(Arrays.asList("select","2018","2019","2020","2021"));
    	comboyear.getItems().addAll(year);
    	comboyear.getSelectionModel().select(0);
    	
    	Date today=new Date();
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(today);
    	
    	int y=cal.get(Calendar.YEAR);
    	comboyear.getSelectionModel().select(y+"");
        
    }
}
