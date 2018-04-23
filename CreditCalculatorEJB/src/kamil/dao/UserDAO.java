package kamil.dao;

import kamil.entities.UserEntity;

import java.util.List;
import java.util.Map;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDAO {

    private final static String UNIT_NAME = "calculatorPU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void createUser(UserEntity user) {
        em.persist(user);
    }

    public UserEntity merge(UserEntity user) {
        return em.merge(user);
    }

    public void remove(UserEntity user) {
        em.remove(em.merge(user));
    }

    public UserEntity find(Object id) {
        return em.find(UserEntity.class, id);
    }

    public List<UserEntity> getFullList() {
        List<UserEntity> list = null;

        Query query = em.createQuery("select u from UserEntity u");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<UserEntity> getList(Map<String, Object> searchParams) {
        List<UserEntity> list = null;

        String select = "select u ";
        String from = "from UserEntity u ";
        String where = "";
        String orderby = "";

        String surname = (String) searchParams.get("");
        if (surname != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "";
        }

        Query query = em.createQuery(select + from + where + orderby);

        // 3. Set configured parameters
        if (surname != null) {
            query.setParameter("surname", surname + "%");
        }
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<UserEntity> getUser(String lg, String ps) {
        List<UserEntity> list = null;
        Query query = em.createQuery("select u from UserEntity u where u.login=:lg and u.pass=:ps");
        query.setParameter("lg", lg);
        query.setParameter("ps", ps);
        list = query.getResultList();
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<UserEntity> getUserWhereLoginAndPassword(String login, String password) {
        List<UserEntity> list = null;
        Query query = em.createQuery("select u from UserEntity u where u.login=:log and u.pass=:password");
        query.setParameter("log", login);
        query.setParameter("password", password);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
