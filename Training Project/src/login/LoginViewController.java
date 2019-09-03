package login;

import java.net.URL;
import java.util.ResourceBundle;

import generatebill.SST_SMS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView id;

    @FXML
    private PasswordField txtkey;

    @FXML
    private ImageView enter;
    
    @FXML
    private Label lblinfo;

    @FXML
    void doenter(MouseEvent event) 
    {
    	if(txtkey.getText().equals("1227"))
    	{
    		try{
    			
    			//to hide the opened window
   			 
  			   Scene scene1=(Scene)enter.getScene();
  			   scene1.getWindow().hide();
        		
    			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/DashboardView.fxml")); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.show();
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	else
    	{
    		doAlert("Please Enter correct KEY");
    	}

    }

    @FXML
    void dorecover(ActionEvent event) 
    {
    	sendsms();

    }

    @FXML
    void dozoomin(MouseEvent event) 
    {
    	enter.setFitWidth(74);
    	enter.setFitHeight(59);

    }

    @FXML
    void dozoomout(MouseEvent event) 
    {
    	enter.setFitWidth(81);
    	enter.setFitHeight(65);

    }
    

    @FXML
    void dozoominid(MouseEvent event) 
    {
    	id.setFitWidth(236);
    	id.setFitHeight(218);

    }

   

    @FXML
    void dozoomoutid(MouseEvent event) 
    {
    	id.setFitWidth(250);
    	id.setFitHeight(230);

    }
    //===============  Do Alert  =======================================
    void doAlert(String msg)
    {
    	Alert a1=new Alert(AlertType.INFORMATION);
    	a1.setHeaderText("INFO");
    	a1.setContentText(msg);
    	a1.show();
    }
    //====================== send sms  for recovery of admin key ======================================
    
    void sendsms()
    {
          String m="your ADMIN KEY is 1227 ";
    	
    	  String resp=SST_SMS.bceSunSoftSend("7009730966", m);
    	  System.out.println(resp);
    	
    	if(resp.indexOf("Exception")!=-1)
    	{
    		System.out.println("Check Internet Connection");
    	    lblinfo.setText("*check INTERNET connection");
    	}
    	
    	else
    		if(resp.indexOf("successfully")!=-1)
    		{
        		System.out.println("Sent");
    	         lblinfo.setText("*SENT");
    		}
    		else
    		{
    		System.out.println( "Invalid Number");
    	    lblinfo.setText("*Invalid Number");
    		}

    }
    //==========================================================================================

    @FXML
    void initialize() {
       
    }
}
