package projectpackage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.Date;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import javax.swing.text.AbstractDocument.LeafElement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CustomerProfileViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtarea;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtcq;

    @FXML
    private TextField txtcp;

    @FXML
    private TextField txtbq;

    @FXML
    private TextField txtbp;
    
    @FXML
    private DatePicker txtdate;
    
    @FXML
    private ImageView errmobile;

    @FXML
    private ImageView errname;

    @FXML
    private ImageView erraddress;

    @FXML
    private ImageView errarea;

    @FXML
    private ImageView errcity;

    @FXML
    private ImageView errdate;
    
    @FXML
    private Button close;

    @FXML
    private TextField txtstatus;
    
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
    	
    			
    
    void playsound(File sound)      //to add sound on the click of button
    {
    	try 
    	{
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			//Thread.sleep(clip.getMicrosecondLength()/1000);          //to set the length of audio
		} 
    	catch (Exception e)
    	{
			
			e.printStackTrace();
		}
    	
    	
    }
    void doAlert(String msg)
    {
    	Alert a1=new Alert(AlertType.INFORMATION);
    	a1.setHeaderText("INFO");
    	a1.setContentText(msg);
    	a1.show();
    }

    @FXML
    void doclose(ActionEvent event)
    {
    	Scene scene1=(Scene)close.getScene();
		   scene1.getWindow().hide();

    }

    @FXML
    void donew(ActionEvent event) 
    {
    	txtmobile.setText("");
    	txtname.setText("");
		txtaddress.setText("");
		txtarea.setText("");
		txtcity.setText("");
		txtcq.setText("");
		txtcp.setText("");
		txtbq.setText("");
		txtbp.setText("");
		txtdate.setValue(null);  
		errmobile.setVisible(false);
		errname.setVisible(false);
		erraddress.setVisible(false);
		errarea.setVisible(false);
		errcity.setVisible(false);
		errdate.setVisible(false);
		//txtstatus.setText("");

    }

    @FXML
    void dosave(ActionEvent event) 
    {
    	if(txtmobile.getText().length()!=10)
    	{
    		errmobile.setVisible(true);
    	}
    	else
    		errmobile.setVisible(false);
    	
    	
    	
    	
    	  if(txtname.getText().equals(""))
    	{
    		errname.setVisible(true);
    	}
    	else
    		errname.setVisible(false);
    	
    	
    	
    	if(txtaddress.getText().equals(""))
    	{
    		erraddress.setVisible(true);
    	}
    	else
    		erraddress.setVisible(false);
    	
    	
    	
    	if(txtarea.getText().equals(""))
    	{
    		errarea.setVisible(true);
    	}
    	else
    		errarea.setVisible(false);
    	
    	
    	
    	if(txtcity.getText().equals(""))
    	{
    		errcity.setVisible(true);
    	}
    	else
    		errcity.setVisible(false);
    	
    	
    	
    	if(txtdate.getValue()==null)
    	{
    		errdate.setVisible(true);
    	}
    	else
    		errdate.setVisible(false);
    	
    	
    	
    	
    	if(txtmobile.getText().length()==10 && !txtname.getText().equals("") && !txtaddress.getText().equals("") && !txtarea.getText().equals("") && !txtcity.getText().equals("") && !txtdate.getValue().equals(""))
    	
    	{
    	try
    	{
    		pst=con.prepareStatement("insert into customerptable values(?,?,?,?,?,?,?,?,?,?,1)");
    		
    		pst.setString(1, txtmobile.getText());
    		pst.setString(2, txtname.getText());
    		pst.setString(3, txtaddress.getText());
    		pst.setString(4, txtarea.getText());
    		pst.setString(5, txtcity.getText());
    		pst.setFloat(6, Float.parseFloat(txtcq.getText()));
    		pst.setFloat(7, Float.parseFloat(txtcp.getText()));
    		pst.setFloat(8, Float.parseFloat(txtbq.getText()));
    		pst.setFloat(9, Float.parseFloat(txtbp.getText()));
    		
    		LocalDate local=txtdate.getValue();
    		java.sql.Date d=java.sql.Date.valueOf(local);
    		pst.setDate(10, d);
    		
    		int count=pst.executeUpdate();
    		
    		if(count==1)
    			doAlert("Record SAVED Sucessfully.....");
    		else
    			doAlert("ERROR.....");
    				
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	
    	
    	}

    }

    @FXML
    void dosearch(ActionEvent event) 
    {
    	try
    	{
    		pst=con.prepareStatement("select * from customerptable where mobileno=?");
    		pst.setString(1, txtmobile.getText());
    		
    		ResultSet rs=pst.executeQuery();
    		boolean sherlock=true;
    		while(rs.next())
    		{
    			sherlock=false;
    			String name=rs.getString(2);
    			String add=rs.getString(3);
    			String area=rs.getString(4);
    			String city=rs.getString(5);
    			float cq=rs.getFloat(6);
    			float cp=rs.getFloat(7);
    			float bq=rs.getFloat(8);
    			float bp=rs.getFloat(9);
    			java.sql.Date d=rs.getDate(10);
    			int sts=rs.getInt(11);
    			
    			txtname.setText(name);
    			txtaddress.setText(add);
    			txtarea.setText(area);
    			txtcity.setText(city);
    			txtcq.setText(cq+"");
    			txtcp.setText(cp+"");
    			txtbq.setText(bq+"");
    			txtbp.setText(bp+"");
    			txtdate.setValue(d.toLocalDate());  
    			txtstatus.setText(sts+"");
    			
    			
    		}
    		if(sherlock==true)
    		{
    			doAlert("Invalid mobile number .....");
    		}	
    		
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}

    }

    @FXML
    void doupdate(ActionEvent event) 
    {
    	if(txtmobile.getText().equals(""))
    	{
    		errmobile.setVisible(true);
    	}
    	else
    		errmobile.setVisible(false);
    	
    	
    	
    	
    	  if(txtname.getText().equals(""))
    	{
    		errname.setVisible(true);
    	}
    	else
    		errname.setVisible(false);
    	
    	
    	
    	if(txtaddress.getText().equals(""))
    	{
    		erraddress.setVisible(true);
    	}
    	else
    		erraddress.setVisible(false);
    	
    	
    	
    	if(txtarea.getText().equals(""))
    	{
    		errarea.setVisible(true);
    	}
    	else
    		errarea.setVisible(false);
    	
    	
    	
    	if(txtcity.getText().equals(""))
    	{
    		errcity.setVisible(true);
    	}
    	else
    		errcity.setVisible(false);
    	
    	
    	
    	if(txtdate.getValue().equals(""))
    	{
    		errdate.setVisible(true);
    	}
    	else
    		errdate.setVisible(false);
    	
    	
    	
    	
    	if(!txtmobile.getText().equals("") && !txtname.getText().equals("") && !txtaddress.getText().equals("") && !txtarea.getText().equals("") && !txtcity.getText().equals("") && !txtdate.getValue().equals(""))
    	
    	{
    	
    	
    	try {
			pst=con.prepareStatement("update customerptable set name=?,address=?,area=?,city=?,cowqty=?,cowprice=?,bufqty=?,bufprice=?,datestart=?,status=? where mobileno=?");
			pst.setString(11,txtmobile.getText());
			
			pst.setString(1, txtname.getText());
    		pst.setString(2, txtaddress.getText());
    		pst.setString(3, txtarea.getText());
    		pst.setString(4, txtcity.getText());
    		pst.setFloat(5, Float.parseFloat(txtcq.getText()));
    		pst.setFloat(6, Float.parseFloat(txtcp.getText()));
    		pst.setFloat(7, Float.parseFloat(txtbq.getText()));
    		pst.setFloat(8, Float.parseFloat(txtbp.getText()));
    		
    		LocalDate local=txtdate.getValue();
    		java.sql.Date d=java.sql.Date.valueOf(local);
    		pst.setDate(9, d);
    		
    		pst.setInt(10, Integer.parseInt(txtstatus.getText()));
    		
    		int count=pst.executeUpdate();
			if(count==0)
			    doAlert("Invalid ID");
			else
				doAlert("Record  UPDATED ");
    		
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    	}

    }
    
    @FXML
    void dodelete(ActionEvent event)
    {
    	try 
    	{
			pst=con.prepareStatement("delete from customerptable where mobileno=?");
			pst.setString(1, txtmobile.getText());
			
			int count=pst.executeUpdate();
			
			txtmobile.setText("");
	    	txtname.setText("");
			txtaddress.setText("");
			txtarea.setText("");
			txtcity.setText("");
			txtcq.setText("");
			txtcp.setText("");
			txtbq.setText("");
			txtbp.setText("");
			txtdate.setValue(null);  
			txtstatus.setText("");
			
			if(count==0)
				doAlert("Invalid Id");
			else
				doAlert(count+" Record Deleted");
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	

    }

    @FXML
    void initialize() 
    {
       doconnection();
    }
}
