package generatebilltable;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class BillViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private RadioButton paid;

    @FXML
    private ToggleGroup payment;

    @FXML
    private RadioButton pending;

    @FXML
    private TableView<BillViewBean> table;

    @FXML
    private TextField txtmobile;
    
    @FXML
    private Label lblinfo;

    ObservableList<BillViewBean> list;
    @FXML
    void doexport(ActionEvent event) 
    {
    	try{
    	writeExcel();
    	lblinfo.setText("exported to excel for print");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    	
    	//===================function to export=================================
    	public void writeExcel() throws Exception
    	{
    		Writer writer=null;
    		try
    		{
    		
    		FileChooser chooser=new FileChooser();
    		chooser.setTitle("select path");
    		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all files","*.*"));
    	
    	File file=chooser.showSaveDialog(null); 
    	String filepath=file.getAbsolutePath();
    	if(!(filepath.endsWith(".csv")||filepath.endsWith(".CSV")))
    	{
    		lblinfo.setText("file name should have .csv extension");
    		return;

    	}
    	file=new File(filepath);
    			
    			writer=new BufferedWriter(new FileWriter(file));
    			String text="mobileno,datestart,totcowqty,buffqty,cowbill,buffbill,total,month,year,recieve\n";
    			writer.write(text);
    			
    			for(BillViewBean c:list)
    			{
    				text=c.getMobileno()+","+c.getDatestart()+","+c.getTotcowqty()+","+c.getBuffqty()+"'"+c.getCowbill()+","+c.getBuffbill()+","+c.getTotal()+","+c.getMonth()+","+c.getYear()+","+c.getRecieve()+"\n";
    				 writer.write(text);		
    			}
    		}
    			catch (Exception ex)
    			{
    				ex.printStackTrace();
    			}
    			finally
    			{
    				writer.flush();
    				writer.close();
    			}
    		

    }

    @FXML
    void domobilesrch(ActionEvent event) 
    {
    	playsound();
    	statements();
    	 list=getPayRecMobFromTable();
    	table.setItems(list);

    }
    
    ObservableList<BillViewBean> getPayRecMobFromTable()
   	{
   		list=FXCollections.observableArrayList();
             
	try 
	{
		pst=con.prepareStatement("select * from generatebilltable where mobileno=?");
		pst.setString(1, txtmobile.getText());
		
		ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				String mobileno=rs.getString(1);
				int datestart=rs.getInt(2);
			float totcowqty=rs.getFloat(3);
			float buffqty=rs.getFloat(4);
			float cowbill=rs.getFloat(5);
			float buffbill=rs.getFloat(6);
			float total=rs.getFloat(7);
			String month=rs.getString(8);
			String year=rs.getString(9);
			int recieve=rs.getInt(10);
		
				
				
				
				BillViewBean bean=new BillViewBean(mobileno,datestart,totcowqty,buffqty,cowbill,buffbill,total,month,year,recieve);
				list.add(bean);
	} 
	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	return list;
}

    @FXML
    void domonyrfind(ActionEvent event) 
    {
    	playsound();
    	statements();
    	list=getPayRecMYFromTable();
    	table.setItems(list);

    }
    ObservableList<BillViewBean> getPayRecMYFromTable()
   	{
   		list=FXCollections.observableArrayList();
             
	try 
	{
		pst=con.prepareStatement("select * from generatebilltable where month=? and year=?");
		pst.setString(1, combomonth.getSelectionModel().getSelectedItem());
		pst.setString(2, comboyear.getSelectionModel().getSelectedItem());
		ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				String mobileno=rs.getString(1);
				int datestart=rs.getInt(2);
			float totcowqty=rs.getFloat(3);
			float buffqty=rs.getFloat(4);
			float cowbill=rs.getFloat(5);
			float buffbill=rs.getFloat(6);
			float total=rs.getFloat(7);
			String month=rs.getString(8);
			String year=rs.getString(9);
			int recieve=rs.getInt(10);
		
				
				
				
				BillViewBean bean=new BillViewBean(mobileno,datestart,totcowqty,buffqty,cowbill,buffbill,total,month,year,recieve);
				list.add(bean);
	} 
	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	return list;
}

    @FXML
    void dopayfind(ActionEvent event) 
    {
    	playsound();
    	statements();
    	list=getPayRecFromTable();
    	table.setItems(list);
    	 

    }
    ObservableList<BillViewBean> getPayRecFromTable()
   	{
   		list=FXCollections.observableArrayList();
             if(paid.isSelected()==true)
               {
	          int status=1;
	try 
	{
		pst=con.prepareStatement("select * from generatebilltable where recieve=? ");
		pst.setInt(1,status);
		
		ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				String mobileno=rs.getString(1);
				int datestart=rs.getInt(2);
			float totcowqty=rs.getFloat(3);
			float buffqty=rs.getFloat(4);
			float cowbill=rs.getFloat(5);
			float buffbill=rs.getFloat(6);
			float total=rs.getFloat(7);
			String month=rs.getString(8);
			String year=rs.getString(9);
			int recieve=rs.getInt(10);
		
				
				
				
				BillViewBean bean=new BillViewBean(mobileno,datestart,totcowqty,buffqty,cowbill,buffbill,total,month,year,recieve);
				list.add(bean);
	} 
	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	
  }
             
             else 
            	 if(pending.isSelected()==true)
            	 {
            		  int status=0;
            			try 
            			{
            				pst=con.prepareStatement("select * from generatebilltable where recieve=?");
            				pst.setInt(1,status);
            				ResultSet rs=  pst.executeQuery();
            					while(rs.next())
            					{
            						String mobileno=rs.getString(1);
            						int datestart=rs.getInt(2);
            					float totcowqty=rs.getFloat(3);
            					float buffqty=rs.getFloat(4);
            					float cowbill=rs.getFloat(5);
            					float buffbill=rs.getFloat(6);
            					float total=rs.getFloat(7);
            					String month=rs.getString(8);
            					String year=rs.getString(9);
            					int recieve=rs.getInt(10);
            				
            						
            						
            						
            						BillViewBean bean=new BillViewBean(mobileno,datestart,totcowqty,buffqty,cowbill,buffbill,total,month,year,recieve);
            						list.add(bean);
            			} 
            			}
            			catch (Exception e) 
            			{
            				
            				e.printStackTrace();
            			}
            			
            		 
            	 }
            	 else
            	 {
            		 doAlert("plz select your option");
            	 }
			return list;
 }

    

	

    
    @SuppressWarnings("unchecked")
	//===========================statements=======================================================
    void statements()
    {
    	TableColumn<BillViewBean, String> mobileno=new TableColumn<BillViewBean, String>("mobileno");
    	mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    	
    	TableColumn<BillViewBean, Integer> datestart=new TableColumn<BillViewBean, Integer>("datestart");
    	datestart.setCellValueFactory(new PropertyValueFactory<>("datestart"));
    	
    	TableColumn<BillViewBean, Float> totcowqty=new TableColumn<BillViewBean, Float>("cowqty");
    	totcowqty.setCellValueFactory(new PropertyValueFactory<>("totcowqty"));
    	
    	TableColumn<BillViewBean, Float> buffqty=new TableColumn<BillViewBean, Float>("buffqty");
    	buffqty.setCellValueFactory(new PropertyValueFactory<>("buffqty"));
    	
    	TableColumn<BillViewBean, Float> cowbill=new TableColumn<BillViewBean, Float>("cowbill");
    	cowbill.setCellValueFactory(new PropertyValueFactory<>("cowbill"));
    	
    	TableColumn<BillViewBean, Float> buffbill=new TableColumn<BillViewBean, Float>("buffbill");
    	buffbill.setCellValueFactory(new PropertyValueFactory<>("buffbill"));
    	
    	TableColumn<BillViewBean, Float> total=new TableColumn<BillViewBean, Float>("total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));
    	
    	TableColumn<BillViewBean, String> month=new TableColumn<BillViewBean, String>("month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));
    	
    	TableColumn<BillViewBean, String> year=new TableColumn<BillViewBean, String>("year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	
    	TableColumn<BillViewBean, Integer> recieve=new TableColumn<BillViewBean, Integer>("recieve");
    	recieve.setCellValueFactory(new PropertyValueFactory<>("recieve"));
    	
    	
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(mobileno,datestart,totcowqty,buffqty,cowbill,buffbill,total,month,year,recieve);
    	
    	
    }

    
    //==========================================================================================
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
    	
    			
    //=============================================================================
   
    URL url;
    AudioClip audio;
    
    void playsound()
    {
    	url=getClass().getResource("Button1.wav");
    	audio=new AudioClip(url.toString());
    	audio.play();
    	
    }
    
    //==========================================================================
    
  
    
    
    void doAlert(String msg)
    {
    	Alert a1=new Alert(AlertType.INFORMATION);
    	a1.setHeaderText("INFO");
    	a1.setContentText(msg);
    	a1.show();
    }
    
    //======================================================================================================
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
