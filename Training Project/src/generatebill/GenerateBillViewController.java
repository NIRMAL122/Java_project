package generatebill;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import generatebill.SST_SMS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
//import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class GenerateBillViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listmobile;

    @FXML
    private ListView<String> listname;

    @FXML
    private TextField txtldate;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private Label lblcr;

    @FXML
    private Label lblbr;

    @FXML
    private Label lblcq;

    @FXML
    private Label lblbq;

    @FXML
    private Label lblcbill;

    @FXML
    private Label lblbbill;
    
    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txttotbill;
    
    @FXML
    private Label lblinfo;

    @FXML
    private ImageView sendmsg;
    
    String name;
    String mobile;
    java.sql.Date sdate;
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
    
    
    void fillNames(){
		ArrayList<String> name = new ArrayList<String>();
		try {
			pst = con.prepareStatement("select name from customerptable");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
				name.add(rs.getString("name"));
		     listname.getItems().addAll(name);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
    
    
    void fillmobile(){
		ArrayList<String> mobileno = new ArrayList<String>();
		try {
			pst = con.prepareStatement("select mobileno from customerptable");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			 mobileno.add(rs.getString("mobileno"));
			listmobile.getItems().addAll(mobileno);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

    float cq;
    float vcq;
    float cp;
    float bq;
    float vbq;
    float bp;
    int startdate;
    
    
    

    @FXML
    void dogenerate(ActionEvent event) 
    {
    	//boolean count;
    	try {
			pst=con.prepareStatement("select cowqty,cowprice,bufqty,bufprice from customerptable where mobileno=? or name=?");
			pst.setString(1, mobile);
			pst.setString(2, name);
			
			ResultSet rs=pst.executeQuery();
			System.out.println("hiiiiiiiijava");
			while(rs.next())
			{
			 cq=rs.getFloat("cowqty");
			 cp=rs.getFloat("cowprice");
			 bq=rs.getFloat("bufqty");
			 bp=rs.getFloat("bufprice");
			
			lblcq.setText(cq+"");
			lblcr.setText(cp+"");
			lblbq.setText(bq+"");
			lblbr.setText(bp+"");
			System.out.println(cq+""+cp+""+bq+""+bp+"");
			}
			
			pst=con.prepareStatement("select datestart from generatebilltable where mobileno=?  ");
			pst.setString(1, mobile);
			ResultSet rs1=pst.executeQuery();
			if((rs1.next())){
			
			{
				int ssdate=rs1.getInt("datestart");
				System.out.println(ssdate+"");
				System.out.println("---------- from generatebill table --------------");
				startdate=ssdate;
			}
			}
			else
			{
				pst=con.prepareStatement("select datestart from customerptable where mobileno=? or name=?");
				pst.setString(1, mobile);
				pst.setString(2, name);
				
				ResultSet rs2=pst.executeQuery();
				System.out.println("hiiiiiiiihelo");
				while(rs2.next())
				{
					 sdate=rs2.getDate("datestart");
					System.out.println(sdate+"");
					System.out.println("******** from customerptable *******");
				}
				System.out.println(sdate);
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd");  
			    String strDate = formatter.format(sdate);  
			    System.out.println("Date Format with MM/dd/yyyy : "+strDate);
			    
			    startdate=Integer.parseInt(strDate);
				
			}
			pst=con.prepareStatement("select sum(cowqty),sum(bufqty) from routinelogtable where mobileno=? and month=?");
			pst.setString(1, mobile);
			pst.setInt(2,mmm-1 );
			
			ResultSet rs3=pst.executeQuery();
			System.out.println("hiiiiiiiijava");
			while(rs3.next())
			{
			 vcq=rs3.getFloat(1);
			
			 vbq=rs3.getFloat(2);
			
			
			
			System.out.println(vcq+"    "+vbq+"   ");
			System.out.println(vcq+"    ");
			System.out.println(vbq+"   ");
			
			
			
			}
			float lastdate=Float.parseFloat(txtldate.getText());
			System.out.println(lastdate);
			
			
		    System.out.println(startdate);
	        
	       
		    
		    float ndays=lastdate-startdate;
	        
	        
	        float cowbill=((cq*ndays)+vcq)*cp;
	        lblcbill.setText(cowbill+"");
			
	        
	        float bufbill=((bq*ndays)+vbq)*bp;
	        lblbbill.setText(bufbill+"");
			
	        float totcq=(cq*ndays)+vcq;
	        float totbq=(bq*ndays)+vbq;
	        
	        System.out.println(totcq+"  "+totbq);
	        
	        float totbill=cowbill+bufbill;
	        txttotbill.setText("your milk bill is  "+totbill+" ");
	        
	        String month=combomonth.getSelectionModel().getSelectedItem();
	        String year=comboyear.getSelectionModel().getSelectedItem();
	        
	        System.out.println(month+"   "+year);
	        
	        pst=con.prepareStatement("insert into generatebilltable values (?,?,?,?,?,?,?,?,?,0)");
	        pst.setString(1, mobile);
	        pst.setInt(2, 1);
	        pst.setFloat(3, totcq);
	        pst.setFloat(4, totbq);
	        pst.setFloat(5, cowbill);
	        pst.setFloat(6,bufbill);
	        pst.setFloat(7, totbill);
	        pst.setString(8, month);
	        pst.setString(9, year);
	        
	        pst.executeUpdate();
	        
	        System.out.println("mission successful......");
    	
    	}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			
		

    }

    @FXML
    void doselect(MouseEvent event) 
    {
    	if(event.getClickCount()==2)
    	{
    		mobile=listmobile.getSelectionModel().getSelectedItem();
    		System.out.println(mobile);
    		txtmobile.setText(mobile);
    	}


    }
    
    

    @FXML
    void doselectname(MouseEvent event) 
    {
    	if(event.getClickCount()==1)
    	{
    		name=listname.getSelectionModel().getSelectedItem();
    		System.out.println(name);
    	}

    }
    int mmm;
    
    @FXML
    void showmonth(ActionEvent event) 
    {
    	if(combomonth.getSelectionModel().getSelectedItem().equals("Jan"))
    	{
    		txtldate.setText("31");
    		mmm=1;
    	}
    	else
    		if(combomonth.getSelectionModel().getSelectedItem().equals("Feb")&&comboyear.getSelectionModel().getSelectedItem().equals("2020"))
    		{
    			txtldate.setText("29");
    			mmm=2;
    		}
    		else if(combomonth.getSelectionModel().getSelectedItem().equals("Feb"))
    			{
    			txtldate.setText("28");
    			mmm=2;
    			}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("March"))
    	{
    		txtldate.setText("31");
    		mmm=3;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("April"))
    	{
    		txtldate.setText("30");
    		mmm=4;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("May"))
    	{
    		txtldate.setText("31");
    		mmm=5;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("June"))
    	{
    		txtldate.setText("30");
    		mmm=6;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("July"))
    	{
    		txtldate.setText("31");
    		mmm=7;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("August"))
    	{
    		txtldate.setText("31");
    		mmm=8;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("Sept"))
    	{
    		txtldate.setText("30");
    		mmm=9;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("Oct"))
    	{
    		txtldate.setText("31");
    		mmm=10;
    	}
    	else if(combomonth.getSelectionModel().getSelectedItem().equals("Nov"))
    	{
    		txtldate.setText("30");
    		mmm=11;
    	}
    	else
    	{
    		txtldate.setText("31");
    		mmm=12;
    	}

    }

    @FXML
    void showyear(ActionEvent event) 
    {
    	

    }
    
    @FXML
    void dosend(MouseEvent event) 
    {
String m=txttotbill.getText();
    	
    	String resp=SST_SMS.bceSunSoftSend(txtmobile.getText(), m);
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
    	lblinfo.setText("*Sent  ");
    		}
    		else
    		{
    		System.out.println( "Invalid Number");
    	lblinfo.setText("*Invalid Number  ");
    		}

    }

    @FXML
    void dozoomin(MouseEvent event) 
    {
    	sendmsg.setFitWidth(64);
    	sendmsg.setFitHeight(50);

    }

    @FXML
    void dozoomout(MouseEvent event)
    {
    	sendmsg.setFitWidth(70);
    	sendmsg.setFitHeight(60);

    }


    @FXML
    void initialize() 
    {
    	doconnection();
    	fillNames();
    	fillmobile();
    	//listname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	//listmobile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
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
