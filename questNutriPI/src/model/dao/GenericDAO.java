package model.dao;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.utils.HibernateUtil;
import utils.CopyFactory;

public abstract class GenericDAO<T> {
	
	/**
	 * Método para salvar um registro no banco de dados do objeto enviado.
	 * <\n>Se o objeto <b><u>NÃO EXISTIR</u></b> no banco de dados, será criado.
	 * <\n>Se o objeto <b><u>EXISTIR</u></b> existir no banco de dados, será criado.
	 * @return Retorna o status da operação.
	 * <li>Se <b>true</b>: o objeto foi salvo.
	 * <li>Se <b>false</b>: houve um erro durante a execução. Consulte o log.
	 */
	public boolean save() {
		boolean result = true;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.merge(this);
			session.getTransaction().commit();
			if(this.getId() == null) {
				CopyFactory.clone(findLast(this.getClass()), this);
			}
		} catch (ConstraintViolationException e) {
		    Throwable cause = e.getCause();
		    if (cause instanceof SQLServerException) {
		        SQLServerException sqlException = (SQLServerException) cause;
		        JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + sqlException.getMessage());
		    } else {
		        JOptionPane.showMessageDialog(null, "Violação de restrição: " + e.getMessage());
		    }
		    result = false;
		}
		
		return result;
	}
	
	public abstract Integer getId();

	/**
	 * Deleta o registro no banco de dados associado à entidade que chama esse método.
	 * @return Retorna o status da operação.
	 * <li>Se <b>true</b>: se o objeto existir no banco de dados, então foi deletado.
	 * <li>Se <b>false</b>: houve um erro durante a execução. Consulte o log.
	 */
	public boolean delete() {
		boolean result = true;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.remove(this);
			session.getTransaction().commit();
		} catch(Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Encontra todos os registros no banco de dados de uma dada Classe mapeada pelo ORM.
	 * @param <T> - Classe de retorno.
	 * @param entityType - Definição da Classe de retorno e busca.
	 * @param params - Complemento da query de busca.
	 * @return Retorna uma lista com todos os registros encontrados no banco de dados instanciados
	 * com a classe declarada (EntityType).
	 */
	protected static <T> List<T> findAll(Class<T> entityType, String... params) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        StringBuilder queryStringBuilder = new StringBuilder("FROM ").append(entityType.getSimpleName());
	        if (params.length > 0) {
	            queryStringBuilder.append(" WHERE ");
	            for (int i = 0; i < params.length; i++) {
	                queryStringBuilder.append(params[i]);
	                if (i < params.length - 1) {
	                    queryStringBuilder.append(" AND ");
	                }
	            }
	        }
	        Query<T> query = session.createQuery(queryStringBuilder.toString(), entityType);
	        return query.getResultList();
	    } catch (HibernateException e) {
	        System.err.println("Erro ao retornar os dados: " + e.getMessage());
	        return new ArrayList<>();
	    }
	}


	/**
	 * Método que procura um registro específico de uma determinada Classe mapeada pelo ORM, através de um id/PK.
	 * @param <T> - Classe de retorno
	 * @param entityType - Definição da Classe de retorno e busca.
	 * @param id - id/pk procurada.
	 * @return Retorna uma instância do tipo (EntityType) caso encontrada, senão null.
	 */
	protected static <T> T findByPK(Class<T> entityType, int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(entityType, id);
		} catch(HibernateException e) {
			System.err.println("Erro ao retornar a entidade com id: " + id +
					" Erro: " + e);
			return null;
		}
	}
	
	/**
	 * Método que procura e retorna apenas o primeiro registro dentro de todos os registros
	 * de uma Classe mapeada pelo ORM.
	 * @param <T> - Classe de retorno.
	 * @param entityType - Definição da Classe de retorno e busca.
	 * @param params - Complemento da query de busca.
	 * @return Retorna uma instância do tipo (EntityType) caso encontrada, senão null.
	 */
    protected static <T> T findOne(Class<T> entityType, String... params) {
        List<T> found = GenericDAO.findAll(entityType, params);
        if (!found.isEmpty()) {
            return found.get(0);
        }
        return null;
    }
    
	/**
	 * Método que procura e retorna o último registro feito na tabela de uma Classe mapeada pelo ORM.
	 * @param <T> - Classe de retorno.
	 * @param entityType - Definição da Classe de retorno e busca.
	 * @param params - Complemento da query de busca.
	 * @return Retorna uma instância do tipo (EntityType) caso encontrada, senão null.
	 */
    protected static <T> T findLast(Class<T> entityType, String... params) {
    	String query = " ORDER BY id DESC ";
    	if(params.length > 0) {
    		for(String param: params) {
    			query += " AND "+ param +" ";
    		}
    	}
    	return GenericDAO.findOne(entityType, query);
    }
}