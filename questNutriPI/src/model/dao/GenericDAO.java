package model.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import utils.HibernateUtil;

public abstract class GenericDAO<T> {
	/*
	 * Variavel que armazena a fábrica sessões do Hibernate usadas para interagir com o banco de dados
	 */
	protected static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();//

	/*
	 * Método para salvar os dados de um objeto no banco de dados
	 * seria algo como o CREATE, ele recebe a entidade que sera salva,
	 * abre uma sessão com o banco de dados, inicia uma transação para garantir
	 * atomicidade, salva essa entidade no banco e faz um commit dessa transação
	 * para que os dados possam ser persistidos de forma permanente.
	 */
	public void create(T entity) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.getTransaction();
			session.beginTransaction();
			session.persist(entity);
			session.close();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/*
	 * Método para alterar os dados de um objeto no banco de dados
	 * seria algo como o UPTADE, ele recebe a entidade que sera atualizada,
	 * abre uma sessão com o banco de dados, inicia uma transação para garantir
	 * atomicidade, atualiza as informações dessa entidade no banco e faz um commit dessa transação
	 * para que os dados possam ser persistidos de forma permanente.
	 */

	public void update(T entity) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.merge(entity);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para Deletar um objeto no banco de dados
	 * seria algo como o DELETE, ele recebe a entidade que sera excluida,
	 * abre uma sessão com o banco de dados, inicia uma transação para garantir
	 * atomicidade, exclui a entidade no banco e faz um commit dessa transação
	 * para que os dados possam ser persistidos de forma permanente.
	 */
	public void delete(T entity) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.remove(entity);
			session.getTransaction().commit();
			session.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected static <T> List<T> findAll(Class<T> entityType, String... params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryString = "FROM " + entityType.getSimpleName();
            if (params.length > 0) {
                queryString += " WHERE ";
                for (int i = 0; i < params.length; i++) {
                    queryString += params[i];
                    if (i < params.length - 1) {
                        queryString += " AND ";
                    }
                }
            }
            Query<T> query = session.createQuery(queryString, entityType);
            return query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Erro ao retornar os dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }


	/**
	 * Método para procurar os dados de um objeto especificado pela chave primaria 
	 * seria algo como o READ, ele recebe a entidade que sera pesquisada e o ID a ser pesquisado,
	 * abre uma sessão com o banco de dados,e verifica se aquela entidade com aquele ID existe
	 * retorna os dados dessa requisição como a entidade, o caso não exista retorna null.
	 * 
	 * @param <T> é a classe do objeto final
	 * @param id 
	 * @param entityType
	 * @return
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
	 * 
	 * @param <T>
	 * @param entityType
	 * @param params
	 * @return
	 */
    public static <T> T findOne(Class<T> entityType, String... params) {
        List<T> found = GenericDAO.findAll(entityType, params);
        if (!found.isEmpty()) {
            return found.get(0);
        }
        return null;
    }
}

