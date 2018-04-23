package kamil.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Kamil on 21.02.2018.
 */
@Entity
@Table(name = "customer", schema = "jpdsi_calculator", catalog = "")
public class CustomerEntity {
    private int idcustomer;
    private String name;
    private String surname;
    private String city;
    private String streetAndHome;
    private Collection<CarEntity> carsByIdcustomer;

    @Id
    @Column(name = "idcustomer")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street_and_home")
    public String getStreetAndHome() {
        return streetAndHome;
    }

    public void setStreetAndHome(String streetAndHome) {
        this.streetAndHome = streetAndHome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (idcustomer != that.idcustomer) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (streetAndHome != null ? !streetAndHome.equals(that.streetAndHome) : that.streetAndHome != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcustomer;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (streetAndHome != null ? streetAndHome.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "customerByCustomerIdcustomer")
    public Collection<CarEntity> getCarsByIdcustomer() {
        return carsByIdcustomer;
    }

    public void setCarsByIdcustomer(Collection<CarEntity> carsByIdcustomer) {
        this.carsByIdcustomer = carsByIdcustomer;
    }
}
