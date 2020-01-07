package it.dstech.jpatest;

import javax.persistence.Column;//attenzione alla provenienza degli import
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

	@Id // equivale a pk della tabella
	@GeneratedValue // ()//logica incremento id: not null, auto-increment,
	private int id;

	@Column(nullable = false) // annotazione per il campo "street". nullable = false --> Istruzione per il db,
								// se arriva un record con street valorizzato null, il db da errore e non crea
								// il record. Se non specificato il valore booleano di nullable = e unique = )
	private String street;

	private String city;

	private String province;

	private String country;

	private String postcode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", province=" + province + ", country="
				+ country + ", postcode=" + postcode + "]";
	}

}