package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Urunler {

	private final ObjectProperty urunAdi;
	private final StringProperty urunFiyati;
	private final StringProperty urunToplamTutar;
	
	
	
	public Urunler()
	{
		this("","","");
	}
	
	public Urunler(String urunAdi, String urunFiyati, String urunToplamTutar) {
		this.urunAdi = new  SimpleObjectProperty(urunAdi);
		this.urunFiyati = new  SimpleStringProperty(urunFiyati);
		this.urunToplamTutar = new  SimpleStringProperty(urunToplamTutar);
		
	}


	public final ObjectProperty urunAdiProperty() {
		return this.urunAdi;
	}
	



	public final Object getUrunAdi() {
		return this.urunAdiProperty().get();
	}
	



	public final void setUrunAdi(final String urunAdi) {
		this.urunAdiProperty().set(urunAdi);
	}
	



	public final StringProperty urunFiyatiProperty() {
		return this.urunFiyati;
	}
	



	public final String getUrunFiyati() {
		return this.urunFiyatiProperty().get();
	}
	



	public final void setUrunFiyati(final String urunFiyati) {
		this.urunFiyatiProperty().set(urunFiyati);
	}
	



	public final StringProperty urunToplamTutarProperty() {
		return this.urunToplamTutar;
	}
	



	public final String getUrunToplamTutar() {
		return this.urunToplamTutarProperty().get();
	}
	



	public final void setUrunToplamTutar(final String urunToplamTutar) {
		this.urunToplamTutarProperty().set(urunToplamTutar);
	}
	





	
	
	
	
	
	
	
	
}
