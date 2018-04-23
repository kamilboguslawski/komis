package kamil.dao;

import kamil.entities.CustomerEntity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerDAO {

	private final static String UNIT_NAME = "calculatorPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
    public void create(CustomerEntity customer) {
    	em.persist(customer);
    }
    public void createdwa(CustomerEntity customer) {
		em.persist(customer);
		em.flush();
	}
    
	public CustomerEntity merge(CustomerEntity customer) {
		return em.merge(customer);
	}

	public void remove(CustomerEntity customer) {
		em.remove(em.merge(customer));
	}

	public CustomerEntity find(Object id) {
		return em.find(CustomerEntity.class, id);
	}
	
	public List<CustomerEntity> getFullList() {
		List<CustomerEntity> list = null;

		Query query = em.createQuery("select c from CustomerEntity c");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public List<CustomerEntity> getList(Map<String, Object> searchParams) {
		List<CustomerEntity> list = null;

		String select = "select c ";
		String from = "from CustomerEntity c ";
		String where = "";
		String orderby = "order by c.surname asc, c.name";

		String surname = (String) searchParams.get("surname");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "c.surname like :surname ";
		}
		Query query = em.createQuery(select + from + where + orderby);

		if (surname != null) {
			query.setParameter("surname", surname+"%");
		}
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list; 
	}

}
