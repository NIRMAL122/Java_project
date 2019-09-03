package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addcustomer;

    @FXML
    private ImageView routinelog;

    @FXML
    private ImageView generatebill;

    @FXML
    private ImageView customertable;

    @FXML
    private ImageView Paymentlog;

    @FXML
    private ImageView about;
    
    @FXML
    private ImageView checkvariations;

    @FXML
    private ImageView billcollector;
    
    @FXML
    private ImageView music;
    
    @FXML
    private ImageView music1;


    @FXML
    void doabout(MouseEvent event) 
    {
    	playsound();
    	try{
    	    		
    				Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("aboutdeveloper/AboutView.fxml")); 
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

    @FXML
    void doaddcustomer(MouseEvent event) 
    {
    	playsound();
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("projectpackage/CustomerProfileView.fxml")); 
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

    

    @FXML
    void docustomertable(MouseEvent event) 
    {
    	playsound();
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customertable/CustomerTableView.fxml")); 
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

    

    @FXML
    void dogeneratebill(MouseEvent event)
    {
    	playsound();
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("generatebill/GenerateBillView.fxml")); 
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

    @FXML
    void dopaymentlog(MouseEvent event) 
    {
    	playsound();
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("generatebilltable/BillView.fxml")); 
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

    @FXML
    void doroutinelog(MouseEvent event)
    {
    	playsound();
try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routinelogpackage/RoutineLogView.fxml")); 
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
    
    @FXML
    void docollectbill(MouseEvent event) 
    {
    	playsound();
    	try{
    	    		
    				Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billcollector/BillCollectorView.fxml")); 
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
    
    @FXML
    void dovariations(MouseEvent event) 
    {
    	playsound();
    	try{
    	    		
    				Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routinelogtable/RoutineTableView.fxml")); 
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
    
    @FXML
    void domusic(MouseEvent event) 
    {
    	
    	
    		playSong();
    		
    	
      }
    
    @FXML
    void dostop(MouseEvent event)
    {
    	mediaplayer.stop();

    }

    

    @FXML
    void dozoomin(MouseEvent event) 
    {
    	addcustomer.setFitWidth(170);
    	addcustomer.setFitHeight(150);

    }

    @FXML
    void dozoomin1(MouseEvent event) 
    {
    	routinelog.setFitWidth(170);
    	routinelog.setFitHeight(150);

    }

    @FXML
    void dozoomin2(MouseEvent event)
    {
    	generatebill.setFitWidth(170);
    	generatebill.setFitHeight(150);

    }

    @FXML
    void dozoomin3(MouseEvent event) 
    {
    	customertable.setFitWidth(170);
    	customertable.setFitHeight(150);

    }

    @FXML
    void dozoomin4(MouseEvent event) 
    {
    	Paymentlog.setFitWidth(170);
    	Paymentlog.setFitHeight(150);

    }

    @FXML
    void dozoomin5(MouseEvent event) 
    {
    	about.setFitWidth(170);
    	about.setFitHeight(150);

    }
    
    @FXML
    void dozoomin6(MouseEvent event) 
    {
    	billcollector.setFitWidth(170);
    	billcollector.setFitHeight(150);

    }

    @FXML
    void dozoomin7(MouseEvent event) 
    {
    	checkvariations.setFitWidth(170);
    	checkvariations.setFitHeight(150);

    }
    
    @FXML
    void dozoomin8(MouseEvent event)
    {
    	music.setFitWidth(55);
    	music.setFitHeight(55);

    }
    
    @FXML
    void dozoomin9(MouseEvent event) 
    {
    	music1.setFitWidth(55);
    	music1.setFitHeight(55);

    }

    @FXML
    void dozoomout(MouseEvent event) 
    {
    	playsound();
    	addcustomer.setFitWidth(220);
    	addcustomer.setFitHeight(170);

    }

    @FXML
    void dozoomout1(MouseEvent event) 
    {
    	playsound();
    	routinelog.setFitWidth(220);
    	routinelog.setFitHeight(170);

    }

    @FXML
    void dozoomout2(MouseEvent event) 
    {
    	playsound();
    	generatebill.setFitWidth(220);
    	generatebill.setFitHeight(170);

    }

    @FXML
    void dozoomout3(MouseEvent event) 
    {
    	playsound();
    	customertable.setFitWidth(220);
    	customertable.setFitHeight(170);

    }

    @FXML
    void dozoomout4(MouseEvent event) 
    {
    	playsound();
    	Paymentlog.setFitWidth(220);
    	Paymentlog.setFitHeight(170);

    }

    @FXML
    void dozoomout5(MouseEvent event) 
    {
    	playsound();
    	about.setFitWidth(220);
    	about.setFitHeight(170);

    }
    
    @FXML
    void dozoomout6(MouseEvent event) 
    {
    	playsound();
    	billcollector.setFitWidth(220);
    	billcollector.setFitHeight(170);

    }

    @FXML
    void dozoomout7(MouseEvent event) 
    {
    	playsound();
    	checkvariations.setFitWidth(220);
    	checkvariations.setFitHeight(170);

    }
    
    @FXML
    void dozoomout8(MouseEvent event) 
    {
    	music.setFitWidth(58);
    	music.setFitHeight(58);

    }
    
    @FXML
    void dozoomout9(MouseEvent event) 
    {
    	music1.setFitWidth(58);
    	music1.setFitHeight(58);

    }
    //==========================play music===============================
    
    MediaPlayer mediaplayer;
    Media media;
    void playSong()
    {
    	
    	url=getClass().getResource("music.mp3");
		media=new Media(url.toString());
		mediaplayer=new MediaPlayer(media);
		mediaplayer.play();
		
		
		
    }
    
    //=====================  playsound on click  ==========================
    URL url;
    AudioClip audio;
    
    void playsound()
    {
    	url=getClass().getResource("cool.wav");
    	audio=new AudioClip(url.toString());
    	audio.play();
    }
    //======================================================================

    @FXML
    void initialize() {
        
    }
}
