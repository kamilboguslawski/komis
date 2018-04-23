package serwis;

import kamil.entities.CarEntity;
import kamil.entities.UserEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class SessionService implements Serializable {
    public CarEntity currentCar;
    public UserEntity currentUser;

    public CarEntity getCurrentCar() {
        return currentCar;
    }
    public void setCurrentCar(CarEntity currentCar) {
        this.currentCar = currentCar;
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    public SessionService() {

    }
}
