package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Urunler;
import service.printText;

public class OrderPageController implements Initializable {

	float h=0;
	float adet= 0;
	String urunAdi;
	String degisken ="";
	@FXML
	private Button girisButon;
	@FXML
	private Button nakitButon;
	@FXML
	private Button kredikartiButon;
	@FXML
	private Button miktarButon;
	@FXML
	private Button toplamButon;
	@FXML
	private Button indirimButon;
	@FXML
	private Button cButon;
	@FXML
	private Button yeniIslem;
	@FXML
	private Button cikisButon;
	@FXML
	private Button urun1Buton;
	@FXML
	private Button urun2Buton;
	@FXML
	private Button urun3Buton;
	@FXML
	private Button urun4Buton;
	@FXML
	private Button odeButon;
	@FXML
	private Button dokuz;
	@FXML
	private Button sekiz;
	@FXML
	private Button yedi;
	@FXML
	private Button alti;
	@FXML
	private Button bes;
	@FXML
	private Button dort;
	@FXML
	private Button uc;
	@FXML
	private Button iki;
	@FXML
	private Button bir;
	@FXML
	private Button sifir;
	@FXML
	private Label urunAdiLabel;
	@FXML
	private Label araToplamLabel;
	@FXML
	private Label indirimLabel;
	@FXML
	private Label kkartiLabel;
	@FXML
	private Label nakitLabel;
	@FXML
	private Label toplamTutarLabel;
	@FXML
	private Label sifreUyari;
	@FXML
	private TextField sifre;
	@FXML 
	private TableView<Urunler> siparisTable;
	@FXML
	private TableColumn<Urunler,Label> siparisAdiColumn;
	@FXML
	private TableColumn<Urunler,String> siparisTutarColumn;
	@FXML
	private TableColumn<Urunler,String> siparisFiyatColumn;
	@FXML
	private Label display;
	@FXML
	private Label tutarDisplay;
	@FXML
	private Label odemeDisplay;
	
	private ObservableList<Urunler> siparisList = FXCollections.observableArrayList();
	@FXML
	private void cikisButtonAction(ActionEvent event){
	    Stage stage = (Stage) cikisButon.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void urunAl(ActionEvent event) throws Exception
	{

		if (tutarDisplay.getText() == "") 
			{

		        if (event.getSource() == bir) {
		            display.setText(display.getText() + "1");
		            sifre.setText(sifre.getText()+"1");
		        } else if (event.getSource() == iki) {
		            display.setText(display.getText() + "2");
		            sifre.setText(sifre.getText()+"2");
		        } else if (event.getSource() == uc) {
		            display.setText(display.getText() + "3");
		            sifre.setText(sifre.getText()+"3");
		        } else if (event.getSource() == dort) {
		            display.setText(display.getText() + "4");
		            sifre.setText(sifre.getText()+"4");
		        } else if (event.getSource() == bes) {
		            display.setText(display.getText() + "5");
		            sifre.setText(sifre.getText()+"5");
		        } else if (event.getSource() == alti) {
		            display.setText(display.getText() + "6");
		            sifre.setText(sifre.getText()+"6");
		        } else if (event.getSource() == yedi) {
		            display.setText(display.getText() + "7");
		            sifre.setText(sifre.getText()+"7");
		        } else if (event.getSource() == sekiz) {
		            display.setText(display.getText() + "8");
		            sifre.setText(sifre.getText()+"8");
		        } else if (event.getSource() == dokuz) {
		            display.setText(display.getText() + "9");
		            sifre.setText(sifre.getText()+"9");
		        } else if (event.getSource() == sifir) {
		            display.setText(display.getText() + "0");
		            sifre.setText(sifre.getText()+"0");
		        } else if (event.getSource() == cButon) {
		            display.setText("");
		            sifre.setText("");
		        }else if(event.getSource()==miktarButon){
		        	adet = Float.parseFloat(display.getText());
		        	display.setText(display.getText()+"*");
		        }else if(event.getSource()==urun1Buton)
		        {
		        	//girilen rakam(adet)la urun1'in fiyatý çarpýlýp tabloya uygun þekilde yerleþecek.Urun1'in fiyatý veritabanýndan gelecek.
		        	long urunfiyatGetir =fiyatGetir(1);
		        	display.setText(display.getText() + urunfiyatGetir);
		        	long urunToplamTutar = (long) (fiyatGetir(1) * adet);//fiyat getir bize id si girilen ürünün fiyatýný veriyor adetle çarpým toplam fiyatýný buluyoruz
		        	tutarDisplay.setText( "Toplam :"+ urunToplamTutar +""+"TL" ); //þimdi bu bulduðumuz urunToplamTutar ý tabloya eklemeliyiz....
		        	aratoplam(urunToplamTutar); //alinan ürün listeye ekleniyor
		        	String ad=adGetir(1);
		        	urunAdiLabel.setText(ad);
		        	tabloyaEkle(urunAdiLabel.getText(), display.getText(), tutarDisplay.getText());
		        	sifirla();
		        	
		        }else if(event.getSource()==urun2Buton)
		        {
		        	
		        	long urunfiyatGetir =fiyatGetir(2);
		        	display.setText((int)adet + " * " + urunfiyatGetir);
		        	long urunToplamTutar = (long) (fiyatGetir(2) * adet);
		        	tutarDisplay.setText( "Toplam :"+ urunToplamTutar +""+ "TL");
		        	aratoplam(urunToplamTutar);
		        	String ad=adGetir(2);
		        	urunAdiLabel.setText(ad);
		        	tabloyaEkle(urunAdiLabel.getText(), display.getText(), tutarDisplay.getText());
		        	sifirla();
		        }else if(event.getSource()==urun3Buton)
		        {
		        	long urunfiyatGetir =fiyatGetir(3);
		        	display.setText(display.getText() + urunfiyatGetir);
		        	long urunToplamTutar = (long) (fiyatGetir(3) * adet);
		        	tutarDisplay.setText( "Toplam :"+ urunToplamTutar+""+ "TL" );
		        	aratoplam(urunToplamTutar);
		        	String ad=adGetir(3);
		        	urunAdiLabel.setText(ad);
		        	tabloyaEkle(urunAdiLabel.getText(), display.getText(), tutarDisplay.getText());
		        	sifirla();
		        }else if(event.getSource()==urun4Buton)
		        {
		        	long urunfiyatGetir =fiyatGetir(4);
		        	display.setText(display.getText() + urunfiyatGetir);
		        	long urunToplamTutar = (long) (fiyatGetir(4) * adet);
		        	tutarDisplay.setText( "Toplam :"+ urunToplamTutar+""+ "TL" );
		        	aratoplam(urunToplamTutar);
		        	String ad=adGetir(4);
		        	urunAdiLabel.setText(ad);
		        	tabloyaEkle(urunAdiLabel.getText(), display.getText(), tutarDisplay.getText());
		        	sifirla();
		        }else if(event.getSource()==indirimButon)
		        {	
		        	display.setOpacity(0);
		        	sifre.setText("");
		        	urunAdiLabel.setText("");
		        	sifre.setOpacity(1);
		    				        	
		        }else if(event.getSource()==girisButon){
		        	sifre.setOpacity(0);
		        	if (sifreDogrula()==true) {
		        		
		        		sifreUyari.setText("");
		        		double indirimOrani=0.1;	
			        	h= Float.parseFloat(araToplamLabel.getText());
			        	h=(float) (h*indirimOrani);
			        	indirimLabel.setText(""+h);
			        	
		    		}else
		    		{
		    			sifreUyari.setText("Þifre Yanlýþ..");
		    		}
		        	
		        }else if(event.getSource()==toplamButon){
		        	
		        	sifre.setOpacity(0);
		        	urunAdiLabel.setText("");
		        	if(indirimLabel.getText() == ""){
		        		float p=Float.parseFloat(araToplamLabel.getText());
		        		toplamTutarLabel.setText(""+ p);
		        	}else
		        	{
		        		float h= Float.parseFloat(araToplamLabel.getText());
		        		float i= Float.parseFloat(indirimLabel.getText());
		        		float k = h-i;
			        	toplamTutarLabel.setText(""+k);
		        	}

		        }else if(event.getSource()==kredikartiButon){
		        	
		        	odemeDisplay.setText("Kredi miktar :");
		        	display.setText("");
		        	
		        }else if(event.getSource()==nakitButon){
		        	
		        	odemeDisplay.setText("Nakit miktar :");
		        	display.setText("");
		        	
		        }else if(event.getSource() == yeniIslem)
		        {
		        	
		        	//dosyaya kaydet ve her þeyi temizle
		        	toplamTutarLabel.setText("");
		        	araToplamLabel.setText("");
		        	indirimLabel.setText("");
		        	nakitLabel.setText("");
		        	kkartiLabel.setText("");  
		        	sifirla();
		        	siparisList.clear();
		        	sifre.setOpacity(0);
		        	display.setOpacity(1);
		        }else if(event.getSource()==odeButon){
		        	
				      if(odemeDisplay.getText() =="Nakit miktar :"){  	
				        	float nakit = Float.parseFloat(display.getText());
				        	if(nakit > Float.parseFloat(toplamTutarLabel.getText()))
				        	{
				        		odemeDisplay.setText("Para Üstü :"+ (nakit - (Float.parseFloat(toplamTutarLabel.getText()))));
				        		
				        		
				        	}else{
				        		
				        		kkartiLabel.setText(""+ (Float.parseFloat(toplamTutarLabel.getText())-nakit));
				        		nakitLabel.setText("" + nakit);
				        	}
				      }else{
				    	  
				    	  float kredi = Float.parseFloat(display.getText());
				        	if(kredi > Float.parseFloat(toplamTutarLabel.getText()))
				        	{
				        		odemeDisplay.setText("FAZLA MÝKTAR GÝRDÝNÝZ..");
				        		display.setText("");
				        		
				        	}else{
				        		
				        		nakitLabel.setText(""+ (Float.parseFloat(toplamTutarLabel.getText())-kredi));
				        		kkartiLabel.setText("" + kredi);
				        	}
				    	  
				      }
				      printText t =new printText();
				      t.kaydet( kkartiLabel.getText(), nakitLabel.getText(),araToplamLabel.getText(),toplamTutarLabel.getText(),indirimLabel.getText());
				      
		        }	
			}else
			{
				tutarDisplay.setText("");
				display.setText("");
			}
		
	}
 private boolean sifreDogrula() {

	 boolean login = false;
		Connection c=null;
		Statement stmt=null;
		try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3307/posproje","root","12345");
				c.setAutoCommit(false);
				
				System.out.println("veritabaný açýldý..");
				
				stmt =c.createStatement();
				
				ResultSet rs =stmt.executeQuery("SELECT * FROM users WHERE PASSWORD= "+"'" +sifre.getText() +"'");
				
				while (rs.next()) {
					if(rs.getString("PASSWORD") != null)
					{
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
////////////////////////////////TABLOYA EKLEME FONKSÝYONU
	private void tabloyaEkle(String urunAdi, String urunFiyati, String urunToplamTutar)
	{
		Urunler s =new Urunler(urunAdi ,urunFiyati ,urunToplamTutar );
		siparisList.add(s);
	}

	ArrayList<Long> alinanUrunler =new ArrayList<Long>();
	public void aratoplam(Long urunToplamTutar)
	{
		long araToplamLabelDeger =0;
		
		 // alýnan ürünlerin toplam fiyattýný bulmak için alýnan ürünleri listeye atýyoruz daha sonra tabloya atacaz
		alinanUrunler.add(urunToplamTutar);
		
		for (int i=0; i<alinanUrunler.size(); i++) 
		{
		  araToplamLabelDeger += alinanUrunler.get((int) i);
		  araToplamLabel.setText(araToplamLabelDeger+"");
		}	
	}
		
	private int fiyatGetir(long id) {
			
			int urunfiyati = 0;
			
			Connection c=null;
			PreparedStatement stmt=null;
			try {
					c = DriverManager.getConnection("jdbc:mysql://localhost:3307/posproje","root","12345");
					c.setAutoCommit(false);
					
					System.out.println("veritabaný açýldý..");
					
					stmt =c.prepareStatement("SELECT * FROM urunler WHERE id = ? ");
					stmt.setLong(1, id);
					ResultSet rs =stmt.executeQuery();
					
					while (rs.next())
					{
						urunfiyati = rs.getInt("urunFiyati"); //sorgudan gelen fiyat bilgisi int tipe atandý
						
					}
					rs.close();
					stmt.close();
					c.close();
					
			} catch (Exception e) {
				System.err.println(e.getClass().getName() +": " +e.getMessage());
				System.exit(0);
			}
			
			
			return urunfiyati;
		}
	private String adGetir(long id) {
				
		String urunAdi ="";
		Connection c=null;
		PreparedStatement stmt=null;
		try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3307/posproje","root","12345");
				c.setAutoCommit(false);
				
				System.out.println("veritabaný açýldý..");
				
				stmt =c.prepareStatement("SELECT * FROM urunler WHERE id = ? ");
				stmt.setLong(1, id);
				ResultSet rs =stmt.executeQuery();
				
				while (rs.next())
				{

					urunAdi = rs.getString("urunAdi");
				}
				rs.close();
				stmt.close();
				c.close();
				
		} catch (Exception e) {
			System.err.println(e.getClass().getName() +": " +e.getMessage());
			System.exit(0);
		}				
		return urunAdi;
	}
	
	public void sifirla()
	{
		     	
    	display.setText("");
    	odemeDisplay.setText("");
    	tutarDisplay.setText("");
    	urunAdiLabel.setText("");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		siparisTutarColumn.setCellValueFactory(new PropertyValueFactory<>("urunToplamTutar"));
		siparisFiyatColumn.setCellValueFactory(new PropertyValueFactory<>("urunFiyati"));
		siparisAdiColumn.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		siparisTable.setItems(siparisList);		
	}
}
