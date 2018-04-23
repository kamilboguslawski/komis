package com.main;

import kamil.dao.AddCostDAO;
import kamil.dao.CarDAO;
import kamil.entities.CarEntity;
import serwis.SessionService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kamil on 03.03.2018.
 */

@ManagedBean
public class carListAdm {

    @EJB
    CarDAO carDAO;

    @EJB
    AddCostDAO addCostDAO;


    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;


    private String mark;
    private String login;

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
//  public List<CarEntity> getFullList() {
 //       return carDAO.getFullList();
  //  }


    public List<CarEntity> getListofCars() {
        List<CarEntity> list = null;
        System.out.println("wchodze do getliofcars");
        // 2. Get list

        if(login !=null && login.length()>0){
            System.out.println("szukam po mark ");
        }
        System.out.println("wypisuje ");
        list = carDAO.getList(login);

        return list;
    }
}