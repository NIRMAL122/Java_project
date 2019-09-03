package customertable;



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

import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class CustomerTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;
    
    

    @FXML
    private TextField txtname;
    
    @FXML
    private Button btn;

    @FXML
    private TableView<CustomerBean> table;
    
    @FXML
    private Label lblinfo;

    
    ObservableList<CustomerBean> list;
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
    			String text="mobileno,name,address,area,city,cowqty,cowprice,bufqty,bufprice,datestart,status\n";
    			writer.write(text);
    			System.out.println("java");
    			
    			for(CustomerBean c:list)
    			{
    				text=c.getMobileno()+","+c.getName()+","+c.getAddress()+","+c.getArea()+","+c.getCity()+","+c.getCowqty()+","+c.getCowprice()+","+c.getBufqty()+","+c.getBufprice()+","+c.getDatestart()+","+c.getStatus()+"\n";
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
    void doareafetch(ActionEvent event) 
    {
    	playsound();
    	statements();
    	 list=getAddressFromTable();
    	table.setItems(list);

    }
    
    ObservableList<CustomerBean> getAddressFromTable()
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customerptable where address=?");
   			 pst.setString(1, comboarea.getSelectionModel().getSelectedItem());
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobileno=rs.getString(1);
   				String name=rs.getString(2);
    			String address=rs.getString(3);
    			String area=rs.getString(4);
    			String city=rs.getString(5);
    			float cowqty=rs.getFloat(6);
    			float cowprice=rs.getFloat(7);
    			float bufqty=rs.getFloat(8);
    			float bufprice=rs.getFloat(9);
    			java.sql.Date datestartt=rs.getDate(10);
    			int status=rs.getInt(11);
   				
   				String datestart=datestartt.toString();
   				
   				CustomerBean bean=new CustomerBean(mobileno,name,address,area,city,cowqty,cowprice,bufqty,bufprice,datestart,status);
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
    void dofetchall(ActionEvent event) 
    {
    	playsound();
    	statements();
    	 list=getRecordsFromTable();
    	table.setItems(list);

    }

    @FXML
    void donamesrch(ActionEvent event) 
    {
    	playsound();
    	statements();
    	list=getNameSrchFromTable();
    	table.setItems(list);

    }
    
    
    ObservableList<CustomerBean> getNameSrchFromTable()
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customerptable where name like '"+txtname.getText()+"%' ");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobileno=rs.getString(1);
   				String name=rs.getString(2);
    			String address=rs.getString(3);
    			String area=rs.getString(4);
    			String city=rs.getString(5);
    			float cowqty=rs.getFloat(6);
    			float cowprice=rs.getFloat(7);
    			float bufqty=rs.getFloat(8);
    			float bufprice=rs.getFloat(9);
    			java.sql.Date datestartt=rs.getDate(10);
    			int status=rs.getInt(11);
   				
   				String datestart=datestartt.toString();
   				
   				CustomerBean bean=new CustomerBean(mobileno,name,address,area,city,cowqty,cowprice,bufqty,bufprice,datestart,status);
   				list.add(bean);
   			}
   			
   			} 
   		catch (Exception e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    
    

    @FXML
    void dozoomin(MouseEvent event) 
    {
    	
    	

    }

    @FXML
    void dozoomout(MouseEvent event) 
    {

    }
    
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
    	
    			
    
    URL url;
    AudioClip audio;
    
    void playsound()
    {
    	url=getClass().getResource("Button1.wav");
    	audio=new AudioClip(url.toString());
    	audio.play();
    	
    }
    void doAlert(String msg)
    {
    	Alert a1=new Alert(AlertType.INFORMATION);
    	a1.setHeaderText("INFO");
    	a1.setContentText(msg);
    	a1.show();
    }

    
    ObservableList<CustomerBean> getRecordsFromTable()
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customerptable");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String mobileno=rs.getString(1);
   				String name=rs.getString(2);
    			String address=rs.getString(3);
    			String area=rs.getString(4);
    			String city=rs.getString(5);
    			float cowqty=rs.getFloat(6);
    			float cowprice=rs.getFloat(7);
    			float bufqty=rs.getFloat(8);
    			float bufprice=rs.getFloat(9);
    			java.sql.Date datestartt=rs.getDate(10);
    			int status=rs.getInt(11);
   				
   				String datestart=datestartt.toString();
   				
   				CustomerBean bean=new CustomerBean(mobileno,name,address,area,city,cowqty,cowprice,bufqty,bufprice,datestart,status);
   				list.add(bean);
   			}
   			
   			} 
   		catch (Exception e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    
    @SuppressWarnings("unchecked")
	void statements()
    {
    	TableColumn<CustomerBean, String> mobileno=new TableColumn<CustomerBean, String>("mobileno");
    	mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    	
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<CustomerBean, String> area=new TableColumn<CustomerBean, String>("area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	TableColumn<CustomerBean, Float> cowqty=new TableColumn<CustomerBean, Float>("cowqty");
    	cowqty.setCellValueFactory(new PropertyValueFactory<>("cowqty"));
    	
    	TableColumn<CustomerBean, Float> cowprice=new TableColumn<CustomerBean, Float>("cowprice");
    	cowprice.setCellValueFactory(new PropertyValueFactory<>("cowprice"));
    	
    	TableColumn<CustomerBean, Float> bufqty=new TableColumn<CustomerBean, Float>("bufqty");
    	bufqty.setCellValueFactory(new PropertyValueFactory<>("bufqty"));
    	
    	TableColumn<CustomerBean, Float> bufprice=new TableColumn<CustomerBean, Float>("bufprice");
    	bufprice.setCellValueFactory(new PropertyValueFactory<>("bufprice"));
    	
    	TableColumn<CustomerBean, String> datestart=new TableColumn<CustomerBean, String>("datestart");
    	datestart.setCellValueFactory(new PropertyValueFactory<>("datestart"));
    	
    	TableColumn<CustomerBean, Integer> status=new TableColumn<CustomerBean, Integer>("status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(mobileno,name,address,area,city,cowqty,cowprice,bufqty,bufprice,datestart,status);
    	
    	
    }
   		
   		
    
    
    @FXML
    void initialize()
    {
    	doconnection();
    	
    	
    	try {
 			  pst=con.prepareStatement("select distinct address from customerptable");
 			 
 			ResultSet rs=  pst.executeQuery();
 			ArrayList<String> ary=new ArrayList<String>();
 			while(rs.next())
 			{
 				
  			
 				String address = new String(rs.getString("address"));
 				ary.add(address);
  			
 				 
 				
 				
 				
 			}
 			 comboarea.getItems().addAll(ary);
		        comboarea.getSelectionModel().select(0);		
 			} 
 		catch (Exception e) 
 		{
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	//ArrayList<String> ary=new ArrayList<String>(Arrays.asList("Mouse","Laptop","Keyboard","Books"));
        //comboarea.getItems().addAll(ary);
       //comboarea.getSelectionModel().select(0);
        
    }
}
