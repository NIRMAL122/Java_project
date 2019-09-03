package routinelogpackage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class RoutineLogViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> txtname;

    @FXML
    private ListView<String> txtaddress;

    @FXML
    private TextField txtcq;

    @FXML
    private TextField txtbq;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtcmq;

    @FXML
    private TextField txtbmq;
    
    @FXML
    private DatePicker datepick;

    @FXML
    private CheckBox chkskip;

    
    
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
			txtname.getItems().addAll(name);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
    
    
    void fillAddress(){
		ArrayList<String> address = new ArrayList<String>();
		try {
			pst = con.prepareStatement("select address from customerptable");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			 address.add(rs.getString("address"));
			txtaddress.getItems().addAll(address);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}


    
    
    
    
    
    @FXML
    void dodeleteothers(ActionEvent event) 
    {
    	int j=0;
    	ArrayList<Integer>list=new ArrayList<>();
    	ObservableList<String>name=txtname.getItems();
    	for(String string:name)
    	{
    		int indx=txtname.getItems().indexOf(string);
    		if(txtname.getSelectionModel().isSelected(indx))
    		{
    			j++;
    		}
    		else
    		{
    			list.add(indx);
    		}
    		
    		
    	}
    	int count=0;
    	for(int i=0;i<list.size();i++)
    	{
    		j=list.get(i)-count;
    		txtname.getItems().remove(j);
    		txtaddress.getItems().remove(j);
    		count++;
    	}
    	txtname.getSelectionModel().clearSelection();
    	txtaddress.getSelectionModel().clearSelection();
    	

    }

    @FXML
    void doinsert(ActionEvent event)
    {
    	LocalDate local=datepick.getValue();
    	int y=local.getYear();
    	int m=local.getMonthValue();
    	int d=local.getDayOfMonth();
    	
    	try
    	{
    		pst=con.prepareStatement("insert into routinelogtable values(?,?,?,?,?,?)");
    		pst.setString(1, txtmobile.getText());
    		pst.setFloat(2, Float.parseFloat(txtcmq.getText()));
    		pst.setFloat(3, Float.parseFloat(txtbmq.getText()));
    		pst.setInt(4, y);
    		pst.setInt(5, m);
    		pst.setInt(6, d);
    		
    		int count=pst.executeUpdate();
    		if(count==1)
    			doAlert("Record Inserted");
    		else
    			doAlert("Error");
    				
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    

    }

    @FXML
    void doshow(MouseEvent event) 
    {
    	if(event.getClickCount()==2)
    	{
    		String nam=txtname.getSelectionModel().getSelectedItem();
    		System.out.println(nam);
    		String mob="";
    		float bq=0f;
    		float cq=0f;
    		try 
    		{
				pst=con.prepareStatement("select mobileno,cowqty,bufqty from customerptable where name=?");
				pst.setString(1,nam);
				
				ResultSet rs=pst.executeQuery();
				
				System.out.println("hi");
				while(rs.next())
				{
				
				mob=rs.getString("mobileno");
				cq=rs.getFloat("cowqty");
				bq=rs.getFloat("bufqty");
				}
				
				System.out.println("hi");
				
				
				txtcq.setText(cq+"");
				txtbq.setText(bq+"");
				txtmobile.setText(mob);
			}
    		catch (SQLException e) 
    		{
				e.printStackTrace();
			}
    	}
    	

    }
    
    @FXML
    void doshowadd(MouseEvent event)
    {
    	if(event.getClickCount()==2)
    	{
    		String add=txtaddress.getSelectionModel().getSelectedItem();
    		System.out.println(add);
    		String mob="";
    		float bq=0f;
    		float cq=0f;
    		try 
    		{
				pst=con.prepareStatement("select mobileno,cowqty,bufqty from customerptable where address=?");
				pst.setString(1,add);
				
				ResultSet rs=pst.executeQuery();
				
				System.out.println("hi");
				while(rs.next())
				{
				
				mob=rs.getString("mobileno");
				cq=rs.getFloat("cowqty");
				bq=rs.getFloat("bufqty");
				}
				
				System.out.println("hi");
				
				
				txtcq.setText(cq+"");
				txtbq.setText(bq+"");
				txtmobile.setText(mob);
			}
    		catch (SQLException e) 
    		{
				e.printStackTrace();
			}
    	}

    }


    @FXML
    void doskip(ActionEvent event) 
    {
    	if(chkskip.isSelected())
    	{
    		txtcmq.setText("-"+txtcq.getText());
    		txtbmq.setText("-"+txtbq.getText());
    		
    	}
    	else
    	{
    		txtcmq.setText("");
    		txtbmq.setText("");
    	}
    	

    }
    
    @FXML
    void doload(ActionEvent event)
    {
    	txtname.getItems().clear();
    	txtaddress.getItems().clear();
    	fillNames();
    	fillAddress();
    	

    }

    @FXML
    void initialize() 
    {
    	doconnection();
    	fillNames();
    	fillAddress();
    	txtname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	txtaddress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    }
}
