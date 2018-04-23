package main.com;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import kamil.dao.AddCostDAO;
import kamil.dao.CarDAO;
import kamil.entities.AdditionalCostEntity;
import kamil.entities.CarEntity;
import main.com.services.SessionService;

import java.util.List;

/**
 * Created by Kamil on 24.02.2018.
 */
@ManagedBean
public class CostList {

    @EJB
    AddCostDAO addCostDAO;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;


    public SessionService getSessionService() {
        return sessionService;
    }
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    public List<AdditionalCostEntity> getFullList() {
           return addCostDAO.getFullList();
        }

    public List<AdditionalCostEntity> getCarCostList() {
        CarEntity currentCar = this.sessionService.currentCar;
        System.out.println("id samochodu " + currentCar.getIdcar());
        return addCostDAO.getCarCostList(currentCar.getIdcar());
    }

}
