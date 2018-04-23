package main.com;

import kamil.dao.CarDAO;
import kamil.dao.CustomerDAO;
import kamil.entities.CarEntity;
import kamil.entities.CustomerEntity;
import main.com.services.SessionService;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class SoldCar implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    CarDAO carDAO;
	
	@EJB
	CustomerDAO customerDAO;

	@ManagedProperty(value = "#{sessionService}")
	private SessionService sessionService;

	public SessionService getSessionService() {
		return sessionService;
	}
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private String idcar;
	private Integer soldpPrice;
	private Date soldDate;


	private String name;
	private String surname;
	private String city;
	private String streetAndHome;
	private String iduser;
	private String idcustomer;
	private int gain;

	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_CAR_LIST = "/komis/carList?faces-redirect=true";
	
	public String getIdcar() {
		return idcar;
	}
	public void setIdcar(String idcar) {
		this.idcar = idcar;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreetAndHome() {
		return streetAndHome;
	}
	public void setStreetAndHome(String streetAndHome) {
		this.streetAndHome = streetAndHome;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(String idcustomer) {
		this.idcustomer = idcustomer;
	}
	public Integer getSoldpPrice() { return soldpPrice;}
	public void setSoldpPrice(Integer soldpPrice) { this.soldpPrice = soldpPrice;}
	public Date getSoldDate() { return soldDate;}
	public void setSoldDate(Date soldDate) {this.soldDate = soldDate;}


	// metody kudlaty, merge(update), customer dao chyba trzeba cos utworzyc... 


	private Boolean validate() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		if (soldpPrice == null ) {
			System.out.println("walidacja, cena ");
			ctx.addMessage(null, new FacesMessage("Podaj kwote sprzedazy"));
			return false;
		}
		if (soldDate == null ) {
			System.out.println("walidacja, data ");
			ctx.addMessage(null, new FacesMessage("Podaj date"));
			return false;
		}
		if (name == null || name.trim().length() == 0) {
			System.out.println("walidacja, imie ");
			ctx.addMessage(null, new FacesMessage("Podaj imie"));
			return false;
		}
		if (surname == null || surname.trim().length() == 0) {
			System.out.println("walidacja, nazwisko ");
			ctx.addMessage(null, new FacesMessage("Podaj nazwisko"));
			return false;
		}
		if (city == null || city.trim().length() == 0) {
			System.out.println("walidacja, miasto ");
			ctx.addMessage(null, new FacesMessage("Podaj miasto"));
			return false;
		}
		if (streetAndHome == null || streetAndHome.trim().length() == 0) {
			System.out.println("walidacja, ulica ");
			ctx.addMessage(null, new FacesMessage("Podaj ulice i dom"));
			return false;
		}

		return true;
	}

	public String saveData() {
		CarEntity currentCar = this.sessionService.currentCar;

		if (currentCar == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Blad, currentCar"));
			System.out.println("blad current car == null ");
			return PAGE_STAY_AT_THE_SAME;
		}

		if (!validate()) {
			System.out.println("Walidacja po stronie serwera - blad");
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			Timestamp timestamp = new Timestamp(soldDate.getTime());
			System.out.println("tworze klienta");
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setName(name);
			customerEntity.setSurname(surname);
			customerEntity.setCity(city);
			customerEntity.setStreetAndHome(streetAndHome);
			customerDAO.createdwa(customerEntity);

			System.out.println("Merge samochodu " + customerEntity.getIdcustomer());
			currentCar.setSellState(1);
			currentCar.setSellDate(timestamp);
			currentCar.setSellPrice(soldpPrice);
			currentCar.setCustomerByCustomerIdcustomer(customerEntity);
			gain = soldpPrice - currentCar.getSumCost();
			currentCar.setGain(gain);
			this.carDAO.merge(currentCar);

			System.out.println("Dodane!!!!!!!!!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blad podczas dodawania addCost"));
			System.out.println("catch exception blad ");
			return PAGE_STAY_AT_THE_SAME;
		}
		System.out.println("Zapis dziala poprawnie");
		return "carList?faces-redirect=true";
	}
}
	

