package kamil.dao;

import kamil.entities.AdditionalCostEntity;
import kamil.entities.UserEntity;

import java.util.List;
import java.util.Map;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
public class AddCostDAO {

    private final static String UNIT_NAME = "calculatorPU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(AdditionalCostEntity additionalCostEntity) {
        em.persist(additionalCostEntity);
    }

    public AdditionalCostEntity merge(AdditionalCostEntity additionalCostEntity) {
        return em.merge(additionalCostEntity);
    }

    public void remove(AdditionalCostEntity additionalCostEntity) {
        em.remove(em.merge(additionalCostEntity));
    }

    public AdditionalCostEntity find(Object id) {
        return em.find(AdditionalCostEntity.class, id);
    }


    public List<AdditionalCostEntity> getFullList() {
        List<AdditionalCostEntity> list = null;

        Query query = em.createQuery("select a from AdditionalCostEntity a");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<AdditionalCostEntity> getCarCostList(int par) {
        List<AdditionalCostEntity> list = null;

        Query query = em.createQuery("select a from AdditionalCostEntity a where a.carByCarIdcar.idcar=:par");
        query.setParameter("par", par);

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
