package kamil.dao;

import kamil.entities.CarEntity;
import kamil.entities.UserEntity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CarDAO {

	private final static String UNIT_NAME = "calculatorPU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(CarEntity car) {
		em.persist(car);
	}

	public CarEntity merge(CarEntity car) {
		return em.merge(car);
	}

	public void remove(CarEntity car) {
		em.remove(em.merge(car));
	}

	public CarEntity find(Object id) {
		return em.find(CarEntity.class, id);
	}

	public List<CarEntity> getFullList() {
		List<CarEntity> list = null;

		Query query = em.createQuery("select c from CarEntity c");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<CarEntity> getList(String log) {
		List<CarEntity> list = null;

		// 1. Build query string with parameters
		String select = "select c ";
		String from = "from CarEntity c ";
		String where = "where c.sellState=:par ";

		// search for surname
		if (log != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "c.mark like :log";
		}

		Query query = em.createQuery(select + from + where );
		query.setParameter("par",0);

		// 3. Set configured parameters
		if (log != null) {
			query.setParameter("log", log +"%" );
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<CarEntity> getSoldList(int id) {
		List<CarEntity> list = null;

		Query query = em.createQuery("select c from CarEntity c where c.sellState=:par and c.userByUserIduser.id=:id");
		query.setParameter("par", 1);
		query.setParameter("id", id);
		list = query.getResultList();
		System.out.print(list.size());
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<CarEntity> getNotSoldList(int id) {
		List<CarEntity> list = null;

		Query query = em.createQuery("select c from CarEntity c where c.sellState=:par and c.userByUserIduser.id=:id");
		query.setParameter("par", 0);
		query.setParameter("id", id);
		list = query.getResultList();
		System.out.print(list.size());
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<CarEntity>  getAdmCarList(){
		List<CarEntity> list = null;

		Query query = em.createQuery("select c from CarEntity c where c.sellState=:par ");
		query.setParameter("par", 0);
		list = query.getResultList();
		System.out.print(list.size());
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
