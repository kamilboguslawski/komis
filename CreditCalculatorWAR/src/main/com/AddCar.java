package main.com;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import kamil.dao.CarDAO;
import kamil.dao.CustomerDAO;
import kamil.dao.UserDAO;
import kamil.entities.CarEntity;
import kamil.entities.CustomerEntity;
import kamil.entities.UserEntity;
import main.com.services.SessionService;


@ManagedBean
@ViewScoped
public class AddCar {

    private static final String PAGE_CAR_LIST = "/pages/komis/carList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;


    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    @EJB
    CarDAO carDAO;

    @EJB
    CustomerDAO customerDAO;

    @EJB
    UserDAO userDAO;

    private String idcar;
    public String mark;
    private String model;
    private Integer buyPrice;
    private Integer sumcost;
    private Date buyDate;

    private String iduser;

    public CarEntity car;

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdcar() {
        return idcar;
    }

    public void setIdcar(String idcar) {
        this.idcar = idcar;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @PostConstruct
    public void postConstruct() {

        //this.car = new CarEntity();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        car = (CarEntity) session.getAttribute("car");
        System.out.println("metoda post construct" );
        if (car != null) {
            session.removeAttribute("car");
            System.out.println("metoda post construct, usun atrybut car, 1 if");
        }
        if (car == null) {
            System.out.println("metoda post construct, 2 if, car == null");
            HttpServletRequest req = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            idcar = req.getParameter("p");
            if (idcar != null) {
                // parse id
                Integer id = null;
                try {
                    id = Integer.parseInt(idcar);
                    System.out.println("metoda post construct, try - catch, parsowanie idcar");
                } catch (NumberFormatException e) {
                    System.out.println("catch");
                }
                if (id != null) {
                    // if parsing successful
                    System.out.println("metoda post construct, id != null");
                    car = carDAO.find(id);
                }
            }
        }

//         if loaded record is to be edited then copy data to properties
        if (car != null && car.getIdcar() != 0) {
            System.out.println("postconstruct, trzeci if ");
            setMark(car.getMark());
            setModel(car.getModel());
            setBuyPrice(car.getBuyPrice());
            // appropriately convert date to string
            setBuyDate(car.getBuyDate());
        }

    }


    private boolean validate() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        boolean result = false;

        if (mark == null || mark.trim().length() == 0) {
            System.out.println("jestem w walidacji marka ");
            ctx.addMessage(null, new FacesMessage("Podaj mark�"));
        }
        if (model == null || model.trim().length() == 0) {
            System.out.println("jestem w walidacji model ");
            ctx.addMessage(null, new FacesMessage("Podaj model"));
        }
        if (buyPrice == null ) {
            System.out.println("jestem w walidacji cena ");
            ctx.addMessage(null, new FacesMessage("Podaj kwote zakupu"));
        }

        Timestamp timestamp = new Timestamp(buyDate.getTime());

        if (ctx.getMessageList().isEmpty()) {
            System.out.println("jestem w walidacji, ustawianie pol car ");
            HttpSession session = (HttpSession) ctx.getExternalContext()
                    .getSession(true);

            UserEntity user1 = this.sessionService.currentUser;
            System.out.println(user1.getLogin());
            CustomerEntity customerEntity = this.customerDAO.find(1);
            car.setCustomerByCustomerIdcustomer(customerEntity);
            UserEntity userEntity = this.sessionService.currentUser;
            car.setUserByUserIduser(userEntity);
            car.setBuyDate(timestamp);
            car.setBuyPrice(this.buyPrice);
            car.setMark(this.mark);
            car.setModel(this.model);
            car.setSellState(0);
            car.setGain(0);
            result = true;
        }
        System.out.println("jestem w walidacji na koncu");
        return result;
    }

    public String saveData() {

        // no car object passed
        if (car == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Bledne uzycie systemu"));
            System.out.println("pierwszy if w savedata");
            return PAGE_STAY_AT_THE_SAME;
        }


        if (!validate()) {
            System.out.print("walidacja");
            System.out.println("drugi if, nie przeszedl walidacji");
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (car.getIdcar() == 0) {
                // new record
                System.out.println("try, tworze nowy rekord");
                car.setSumCost(this.buyPrice);
                car.setAddCost(0);
                carDAO.create(car);
            } else {
                // existing record
                System.out.println("try, wystepuje rekorod robie merge");
                sumcost = car.getAddCost() + car.getBuyPrice();
                car.setSumCost(sumcost);
                carDAO.merge(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("wystapil blad podczas zapisu"));
            System.out.println("catch, blad zapisu");
            return PAGE_STAY_AT_THE_SAME;
        }
        System.out.println("wszystko dziala");
        return "carList?faces-redirect=true";
    }
}
