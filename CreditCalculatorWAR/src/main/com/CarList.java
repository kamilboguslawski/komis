package main.com;

import kamil.dao.AddCostDAO;
import kamil.entities.CarEntity;
import kamil.dao.CarDAO;
import kamil.entities.UserEntity;
import main.com.services.SessionService;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
public class CarList {

    @EJB
    CarDAO carDAO;

    @EJB
    AddCostDAO addCostDAO;


    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    private String Mark;
    private int id;
    private static final String PAGE_CAR_LIST = "/komis/carList?faces-redirect=true";
    private static final String PAGE_SOLD_LIST = "/komis/soldList?faces-redirect=true";
    private static final String PAGE_ADD_CAR = "/komis/addCar?faces-redirect=true";
    private static final String PAGE_ADD_COST = "/komis/addCost?faces-redirect=true";
    private static final String PAGE_EDIT = "/komis/editCar?faces-redirect=true";
    private static final String PAGE_SOLD_CAR = "/komis/soldForm?faces-redirect=true";

    public SessionService getSessionService() {
        return sessionService;
    }
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getMark() {
        return Mark;
    }

    public void setMarka(String mark) {
        Mark = mark;
    }

    public List<CarEntity> getFullList() {
        return carDAO.getFullList();
    }

    public List<CarEntity> getSoldList() {
        UserEntity userEntity = sessionService.currentUser;
        return carDAO.getSoldList(userEntity.getIduser());
    }

    public List<CarEntity> getList() {
        List<CarEntity> list = null;
        UserEntity userEntity = sessionService.currentUser;
        // 2. Get list
        System.out.println(userEntity.getIduser());
        list = carDAO.getNotSoldList(userEntity.getIduser());

        return list;
    }

    public String carListView() {
        return PAGE_CAR_LIST;
    }

    public String soldCarView() {
        return PAGE_SOLD_LIST;
    }

    public String addCarView() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        CarEntity car = new CarEntity();
        session.setAttribute("car", car);
        return PAGE_ADD_CAR;
    }

    public String editCar(CarEntity car) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("car", car);
        return PAGE_EDIT;
    }

    public String changeToSoldCar(CarEntity car) {

        this.sessionService.currentCar = car;
        return PAGE_SOLD_CAR;
    }

    public String addCost(CarEntity car) {
        this.sessionService.currentCar = car;
        return PAGE_ADD_COST;
    }


}
