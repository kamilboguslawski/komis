package kamil.entities;

import javax.persistence.*;

/**
 * Created by Kamil on 21.02.2018.
 */
@Entity
@Table(name = "additional_cost", schema = "jpdsi_calculator", catalog = "")
public class AdditionalCostEntity {
    private int idaddCost;
    private String part;
    private Integer cost;
    private CarEntity carByCarIdcar;

    @Id
    @Column(name = "idadd_cost")
    public int getIdaddCost() {
        return idaddCost;
    }

    public void setIdaddCost(int idaddCost) {
        this.idaddCost = idaddCost;
    }

    @Basic
    @Column(name = "part")
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Basic
    @Column(name = "cost")
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditionalCostEntity that = (AdditionalCostEntity) o;

        if (idaddCost != that.idaddCost) return false;
        if (part != null ? !part.equals(that.part) : that.part != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idaddCost;
        result = 31 * result + (part != null ? part.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "car_idcar", referencedColumnName = "idcar", nullable = false)
    public CarEntity getCarByCarIdcar() {
        return carByCarIdcar;
    }

    public void setCarByCarIdcar(CarEntity carByCarIdcar) {
        this.carByCarIdcar = carByCarIdcar;
    }
}
