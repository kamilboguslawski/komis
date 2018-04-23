package kamil.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Kamil on 21.02.2018.
 */
@Entity
@Table(name = "car", schema = "jpdsi_calculator", catalog = "")
public class CarEntity {
    private int idcar;
    private String mark;
    private String model;
    private Timestamp buyDate;
    private Timestamp sellDate;
    private Integer buyPrice;
    private Integer sellPrice;
    private Integer addCost;
    private Integer sumCost;
    private Integer gain;
    private Integer sellState;
    private Collection<AdditionalCostEntity> additionalCostsByIdcar;
    private CustomerEntity customerByCustomerIdcustomer;
    private UserEntity userByUserIduser;

    @Id
    @Column(name = "idcar")
    public int getIdcar() {
        return idcar;
    }

    public void setIdcar(int idcar) {
        this.idcar = idcar;
    }

    @Basic
    @Column(name = "mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "buy_date")
    public Timestamp getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }

    @Basic
    @Column(name = "sell_date")
    public Timestamp getSellDate() {
        return sellDate;
    }

    public void setSellDate(Timestamp sellDate) {
        this.sellDate = sellDate;
    }

    @Basic
    @Column(name = "buy_price")
    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "sell_price")
    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Basic
    @Column(name = "add_cost")
    public Integer getAddCost() {
        return addCost;
    }

    public void setAddCost(Integer addCost) {
        this.addCost = addCost;
    }

    @Basic
    @Column(name = "sum_cost")
    public Integer getSumCost() {
        return sumCost;
    }

    public void setSumCost(Integer sumCost) {
        this.sumCost = sumCost;
    }

    @Basic
    @Column(name = "gain")
    public Integer getGain() {
        return gain;
    }

    public void setGain(Integer gain) {
        this.gain = gain;
    }

    @Basic
    @Column(name = "sell_state")
    public Integer getSellState() {
        return sellState;
    }

    public void setSellState(Integer sellState) {
        this.sellState = sellState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (idcar != carEntity.idcar) return false;
        if (mark != null ? !mark.equals(carEntity.mark) : carEntity.mark != null) return false;
        if (model != null ? !model.equals(carEntity.model) : carEntity.model != null) return false;
        if (buyDate != null ? !buyDate.equals(carEntity.buyDate) : carEntity.buyDate != null) return false;
        if (sellDate != null ? !sellDate.equals(carEntity.sellDate) : carEntity.sellDate != null) return false;
        if (buyPrice != null ? !buyPrice.equals(carEntity.buyPrice) : carEntity.buyPrice != null) return false;
        if (sellPrice != null ? !sellPrice.equals(carEntity.sellPrice) : carEntity.sellPrice != null) return false;
        if (addCost != null ? !addCost.equals(carEntity.addCost) : carEntity.addCost != null) return false;
        if (sumCost != null ? !sumCost.equals(carEntity.sumCost) : carEntity.sumCost != null) return false;
        if (gain != null ? !gain.equals(carEntity.gain) : carEntity.gain != null) return false;
        if (sellState != null ? !sellState.equals(carEntity.sellState) : carEntity.sellState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcar;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (buyDate != null ? buyDate.hashCode() : 0);
        result = 31 * result + (sellDate != null ? sellDate.hashCode() : 0);
        result = 31 * result + (buyPrice != null ? buyPrice.hashCode() : 0);
        result = 31 * result + (sellPrice != null ? sellPrice.hashCode() : 0);
        result = 31 * result + (addCost != null ? addCost.hashCode() : 0);
        result = 31 * result + (sumCost != null ? sumCost.hashCode() : 0);
        result = 31 * result + (gain != null ? gain.hashCode() : 0);
        result = 31 * result + (sellState != null ? sellState.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "carByCarIdcar")
    public Collection<AdditionalCostEntity> getAdditionalCostsByIdcar() {
        return additionalCostsByIdcar;
    }

    public void setAdditionalCostsByIdcar(Collection<AdditionalCostEntity> additionalCostsByIdcar) {
        this.additionalCostsByIdcar = additionalCostsByIdcar;
    }

    @ManyToOne
    @JoinColumn(name = "customer_idcustomer", referencedColumnName = "idcustomer", nullable = false)
    public CustomerEntity getCustomerByCustomerIdcustomer() {
        return customerByCustomerIdcustomer;
    }

    public void setCustomerByCustomerIdcustomer(CustomerEntity customerByCustomerIdcustomer) {
        this.customerByCustomerIdcustomer = customerByCustomerIdcustomer;
    }

    @ManyToOne
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public UserEntity getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(UserEntity userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
