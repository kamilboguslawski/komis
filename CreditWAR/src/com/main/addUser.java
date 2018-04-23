package com.main;

import kamil.dao.UserDAO;
import kamil.entities.UserEntity;
import serwis.SessionService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * Created by Kamil on 04.03.2018.
 */
@ManagedBean
public class addUser {
    private int role;
    private String login;
    private String pass;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

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

    private Boolean validate() {
        System.out.println("walidacja ");

        FacesContext ctx = FacesContext.getCurrentInstance();
        if (login == null || login.trim().length() == 0) {
            System.out.println("walidacja, part  ");
            ctx.addMessage(null, new FacesMessage("Podaj login"));
            return false;
        }
        if (pass == null || pass.trim().length() == 0) {
            System.out.println("walidacja, cost ");
            ctx.addMessage(null, new FacesMessage("Podaj haslo"));
            return false;
        }
        return true;
    }

    public String saveData() {

        if (!validate()) {
            System.out.println("Walidacja po stronie serwera - blad");
            return null;
        }

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(login);
            userEntity.setPass(pass);
            userEntity.setRole(role);
            userdao.createUser(userEntity);

            System.out.println("nie udalo sie dodac kosztu :( ");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blad podczas dodawania addCost"));
            System.out.println("catch exception blad ");
            return null;
        }
        System.out.println("Zapis dziala poprawnie");
        return "admPanel?faces-redirect=true";
    }
}
