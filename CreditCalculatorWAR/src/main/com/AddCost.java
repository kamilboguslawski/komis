package main.com;


import kamil.dao.AddCostDAO;
import kamil.dao.CarDAO;
import kamil.entities.AdditionalCostEntity;
import kamil.entities.CarEntity;
import main.com.services.SessionService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ManagedBean
@ViewScoped
public class AddCost {

    private static final String PAGE_STAY_AT_THE_SAME = null;
    private static final String PAGE_CAR_LIST = "/komis/carList?faces-redirect=true";

    @EJB
    CarDAO carDAO;

    @EJB
    AddCostDAO addCostDAO;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    private String cost;
    private String part;
    private Integer sumcost;
    private Integer addcost;
    private Integer addSumCost;

    public SessionService getSessionService() {
        return sessionService;
    }
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPart() {
        return part;
    }
    public void setPart(String part) {
        this.part = part;
    }

    public Integer getSumcost() {
        return sumcost;
    }
    public void setSumcost(Integer sumcost) {
        this.sumcost = sumcost;
    }



    private Boolean validate() {
        System.out.println("walidacja ");

        FacesContext ctx = FacesContext.getCurrentInstance();
        if (part == null || part.trim().length() == 0) {
            System.out.println("walidacja, part ");
            ctx.addMessage(null, new FacesMessage("Podaj nazwe"));
            return false;
        }
        if (cost == null || cost.trim().length() == 0) {
            System.out.println("walidacja, cost ");
            ctx.addMessage(null, new FacesMessage("Podaj koszt"));
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
            AdditionalCostEntity additionalCostEntity = new AdditionalCostEntity();
            additionalCostEntity.setCarByCarIdcar(currentCar);
            System.out.println("nr auta: " + currentCar.getIdcar());
            additionalCostEntity.setCost(Integer.parseInt(cost));
            additionalCostEntity.setPart(part);
            this.addCostDAO.create(additionalCostEntity);
            System.out.println("probuje dodac do auta ");

            addSumCost = currentCar.getBuyPrice() + currentCar.getAddCost();
            sumcost = currentCar.getSumCost() + Integer.parseInt(cost);
            addcost = currentCar.getAddCost() + Integer.parseInt(cost);

            currentCar.setSumCost(sumcost);
            currentCar.setAddCost(addcost);

            this.carDAO.merge(currentCar);

            System.out.println("nie udalo sie dodac kosztu :( ");
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
