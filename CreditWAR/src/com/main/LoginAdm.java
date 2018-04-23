package com.main;

import kamil.dao.UserDAO;
import kamil.entities.UserEntity;
import serwis.SessionService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;


@ManagedBean
public class LoginAdm {

    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String login;
    private String password;

    @EJB
    UserDAO userDAO;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;


    public SessionService getSessionService() {
        return sessionService;
    }
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() {

        if ( this.sessionService.currentUser != null) {

            this.sessionService.currentUser = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        } else {
            System.out.println("Blad, uzytkownik nie byl zalogowany");
        }
        System.out.println("teeeeeeeeeeeeeeeeeeeeeeeest");
        return "/index?faces-redirect=true";
    }

    public String login() {
        List<UserEntity> list = this.userDAO.getUserWhereLoginAndPassword(this.login, this.password);
        if (list.size() != 0 ) {
            UserEntity currentUser = list.get(0);
            this.sessionService.currentUser = currentUser;
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", currentUser);

            System.out.println("Zalogowano do systemu: " + this.sessionService.currentUser.getLogin());
            return "/panel/admPanel?faces-redirect=true";
        } else {
            System.out.println("Nie udalo sie zalogowac");
            return "/login?faces-redirect=true";
        }
    }
}
