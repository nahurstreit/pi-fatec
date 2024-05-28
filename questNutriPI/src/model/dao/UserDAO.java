package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.entities.User;
import model.utils.HibernateUtil;

public abstract class UserDAO extends GenericDAO<User> {

	/**
	 * Método que retorna uma lista de todos os Users que correspondem aos
	 * parâmetros fornecidos
	 *
	 * @param params - Parâmetros de filtro opcionais para a consulta devem ser
	 *               escritos de acordo com a declaração das classes de entidade
	 * 
	 * @return Lista de User que correspondem aos parâmetros fornecidos.
	 */
	    public static List<User> findAll(String... params) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            StringBuilder queryString = new StringBuilder("FROM User");

	            if (params.length > 0 && params.length % 2 == 0) {
	                queryString.append(" WHERE ");
	                for (int i = 0; i < params.length; i += 2) {
	                    queryString.append(params[i]).append(" = :").append(params[i]);
	                    if (i < params.length - 2) {
	                        queryString.append(" AND ");
	                    }
	                }
	            }

	            Query<User> query = session.createQuery(queryString.toString(), User.class);
	            for (int i = 0; i < params.length; i += 2) {
	                query.setParameter(params[i], params[i + 1]);
	            }
	            return query.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	/**
	 * Método que retorna um User pelo seu id
	 * 
	 * @param id de um User a ser buscado.
	 * @return User que corresponde ao id fornecido ou null se não encontrado.
	 */
	public static User findByPK(int id) {
		return GenericDAO.findByPK(User.class, id);
	}


		 public static User findOne(String... params) {
		        List<User> found = findAll(params);
		        if (!found.isEmpty()) {
		            return found.get(0);
		        }
		        return null;
		    }
	
	
    /**
     * Método para excluir um usuário pelo seu ID.
     * 
     * @param userId - ID do usuário a ser excluído.
     */
    public void delete(int userId) {
        User user = findByPK(userId);
        if (user != null) {
            super.delete(); // Chama o método delete da classe pai (GenericDAO)
        }
    }

}
