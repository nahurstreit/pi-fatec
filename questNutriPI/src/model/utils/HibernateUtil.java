package model.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	/**
	 * Variavel estatica apenas para armazenar o SessionFactory
	 */
	private static final SessionFactory sessionFactory = buildSessionFactory();

	/*
	 * Método privado que cria e configura a SessionFactory de acordo com o arquivo
	 * em (config.database/hibernate.cfg.xml), esse método constrói e retorna a
	 * SessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			/**
			 * Cria uma nova instancia de Configuration que recebe as configurações
			 * definidas no arquivo xml.
			 */
			Configuration configuration = new Configuration().configure("model/config/database/hibernate.cfg.xml");

			// Após carregar as configurações do Hibernate, retorna a construção da
			// SessionFactory.
			return configuration.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/*
	 * Método público getter para retornar a instância de sessionFactory Ele que vai
	 * permitir que as classes obtenham a sessionFactory para poder interagir com o
	 * banco de dados.
	 */

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Método que faz o refresh de alguma instância com os dados atuais
	 * @param <T> -> Classe da Instância
	 * @param instance -> Instância a ser atualizada
	 */
	public static <T> void refreshInstance(T instance) {
		try (Session session = sessionFactory.openSession()) {
			session.refresh(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}