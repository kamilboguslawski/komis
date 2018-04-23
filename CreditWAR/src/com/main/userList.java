package com.main;

import kamil.dao.UserDAO;
import kamil.entities.UserEntity;
import serwis.SessionService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Kamil on 27.02.2018.
 */
@ManagedBean
public class userList {

    @EJB
    UserDAO userdao;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;


    public SessionService getSessionService() {
        return sessionService;
    }
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public List<UserEntity> getFullList() {
        return userdao.getFullList();
    }

}
