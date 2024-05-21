package model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.entities.Weight;
import utils.HibernateUtil;

public abstract class WeightDAO extends GenericDAO<Weight> {
	
	public static List<Weight> findAll(String... params) {
		return GenericDAO.findAll(Weight.class, params);
	}

	public static List<Weight> findAllByCustomerPK(int id) {
		return Weight.findAll("customer.id = " + id);
	}
	
	public static Weight findByPK(int id) {
		return GenericDAO.findByPK(Weight.class, id);
	}
	
	   public static Weight findLastWeightRecord(int customerId) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            String queryString = "FROM Weight WHERE idCustomer = :customerId";
	            queryString += " ORDER BY wgtDateRegister DESC";
	            Query<Weight> query = session.createQuery(queryString, Weight.class);
	            query.setParameter("customerId", customerId);
	            query.setMaxResults(1);
	            return query.uniqueResult();
	        } catch (HibernateException e) {
	            System.err.println("Erro ao retornar o último registro de peso: " + e.getMessage());
	            return null;
	        }
	    }
	
	public void createWeight(Weight weight){
		create(weight);
	}

	public void deleteWeight(Weight weight){
		delete(weight);
	}

	public void updateWeight(Weight weight){
		update(weight);
	}
}