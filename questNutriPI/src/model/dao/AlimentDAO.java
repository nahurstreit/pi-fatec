package model.dao;

import java.util.List;
import model.entities.Aliment;

public abstract class AlimentDAO extends GenericDAO<Aliment> {
	
	public static List<Aliment> findAll(String ...params) {
		return GenericDAO.findAll(Aliment.class, params);
	}
	
	public static Aliment findByPK(int id) {
		return GenericDAO.findByPK(Aliment.class, id);
	}
	
	public static Aliment findOne(String ...params) {
		return GenericDAO.findOne(Aliment.class, params);
	}
	
}