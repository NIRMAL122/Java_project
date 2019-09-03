package routinelogtable;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class RoutineTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmobile;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private Button show;

    @FXML
    private TableView<RoutineTableBean> table;
    
    @FXML
    private Label lblinfo;

    ObservableList<RoutineTableBean> list;
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
    			String text="mobileno,cowqty,bufqty,month,year,day\n";
    			writer.write(text);
    			
    			for(RoutineTableBean c:list)
    			{
    				text=c.getMobileno()+","+c.getCowqty()+","+c.getBufqty()+","+c.getMonth()+","+c.getYear()+","+c.getDay()+"\n";
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
    void dochange(MouseEvent event) 
    {
    	

    }

    @FXML
    void dosame(MouseEvent event) 
    {

    }

    @FXML
    void doshow(ActionEvent event) 
    {
    	playsound();
    	statements();
    	list=getRoutineLogFromTable();
    	table.setItems(list);


    }
    
    @SuppressWarnings("unchecked")
	//=========================================================================================
    
    void statements()
    {
    	TableColumn<RoutineTableBean, String> mobileno=new TableColumn<RoutineTableBean, String>("mobileno");
    	mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    	mobileno.setMinWidth(200);
    	
    	
    	TableColumn<RoutineTableBean, Float> cowqty=new TableColumn<RoutineTableBean, Float>("cowqty");
    	cowqty.setCellValueFactory(new PropertyValueFactory<>("cowqty"));
    	cowqty.setMinWidth(100);
    	
    	TableColumn<RoutineTableBean, Float> bufqty=new TableColumn<RoutineTableBean, Float>("bufqty");
    	bufqty.setCellValueFactory(new PropertyValueFactory<>("bufqty"));
    	bufqty.setMinWidth(100);
    	
    	TableColumn<RoutineTableBean, Integer> year=new TableColumn<RoutineTableBean, Integer>("year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	year.setMinWidth(100);
    	
    	TableColumn<RoutineTableBean, Integer> month=new TableColumn<RoutineTableBean, Integer>("month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));
    	month.setMinWidth(100);
    	
    	TableColumn<RoutineTableBean, Integer> day=new TableColumn<RoutineTableBean, Integer>("day");
    	day.setCellValueFactory(new PropertyValueFactory<>("day"));
    	day.setMinWidth(100);
    	
    	
    	
    	
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(mobileno,cowqty,bufqty,month,year,day);
    	
    	
    }
    
    //========================================================================================
    
    
    ObservableList<RoutineTableBean> getRoutineLogFromTable()
   	{
   		list=FXCollections.observableArrayList();
   		int mon=Integer.parseInt( combomonth.getSelectionModel().getSelectedItem());
   	    int ye=Integer.parseInt( comboyear.getSelectionModel().getSelectedItem());
             
	try 
	{
		pst=con.prepareStatement("select * from routinelogtable where mobileno=? and month=? and year=? ");
		pst.setString(1,txtmobile.getText());
		pst.setInt(2, mon);
		pst.setInt(3, ye);
		System.out.println("java1");
		ResultSet rs=pst.executeQuery();
		System.out.println("java1");
			while(rs.next())
			{
				String mobileno=rs.getString(1);
				float cowqty=rs.getFloat(2);
				float bufqty=rs.getFloat(3);
				int year=rs.getInt(4);
				int month=rs.getInt(5);
				int day=rs.getInt(6);
		
				
				
				
				RoutineTableBean bean=new RoutineTableBean(mobileno,cowqty,bufqty,year,month,day);
				list.add(bean);
	} 
	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	return list;
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
    	url=getClass().getResource("Button.wav");
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
    	
    	ArrayList<String>months=new ArrayList<String>(Arrays.asList("select","1","2","3","4","5","6","7","8","9","10","11","12"));
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
