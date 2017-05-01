package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;

public class LoginController implements Initializable{

	@FXML
	private Label label;
	
	@FXML
	private TextField userNameBox;
	
	@FXML
	private TextField passwordBox;
	
	@FXML
	private Label invalidLabel;
	
	@FXML 
	private boolean logincontrol=false;
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException
	{		
		Parent orderPageParent = FXMLLoader.load(getClass().getResource("/view/OrderPage.fxml"));
		Scene orderPageScene = new Scene(orderPageParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		if (isValidCredentials()) {
			appStage.setFullScreen(true);
			appStage.hide();
			appStage.setScene(orderPageScene);
			appStage.show();
		}
		else
		{
			userNameBox.clear();
			passwordBox.clear();
			invalidLabel.setText("Lütfen Tekrar Deneyiniz..");
		}
	}

	@FXML
	public void enterAction(KeyEvent event) throws IOException{

	    if(event.getCode().equals(KeyCode.ENTER)){
	    	Parent orderPageParent = FXMLLoader.load(getClass().getResource("/view/OrderPage.fxml"));
			Scene orderPageScene = new Scene(orderPageParent);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			if (isValidCredentials()) {
				appStage.setFullScreen(true);
				appStage.hide();
				appStage.setScene(orderPageScene);
				appStage.show();
			}
			else
			{
				userNameBox.clear();
				passwordBox.clear();
				invalidLabel.setText("Lütfen Tekrar Deneyiniz..");
			}
	    }
	    		
	}
	protected boolean isValidCredentials() {
		
		
		boolean login = false;
		Connection c=null;
		Statement stmt=null;
		try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3307/posproje","root","12345");
				c.setAutoCommit(false);
				
				System.out.println("veritabaný açýldý..");
				
				stmt =c.createStatement();
				
				ResultSet rs =stmt.executeQuery("SELECT * FROM users WHERE USERNAME=" +"'"+userNameBox.getText() + "'"
				+ "AND PASSWORD= "+"'" +passwordBox.getText() +"'");
				
				while (rs.next()) {
					if(rs.getString("USERNAME") !=null && rs.getString("PASSWORD") != null)
					{
						//String username =rs.getString("USERNAME");
						//String password = rs.getString("PASSWORD");
						
						login = true;
					}
				}
				rs.close();
				stmt.close();
				c.close();
				
		} catch (Exception e) {
			System.err.println(e.getClass().getName() +": " +e.getMessage());
			System.exit(0);
		}
		
		
		return login;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
