package model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	public static void main(String[] args) {
		// Configuração do Hibernate
		Configuration configuration = new Configuration();
		configuration.configure("config/database/hibernate.cfg.xml");

		// Criação da fábrica de sessões
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// Verifica se a conexão foi estabelecida com sucesso
		if (sessionFactory != null) {
			System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
			sessionFactory.close();
		} else {
			System.out.println("Falha ao estabelecer conexão com o banco de dados.");
		}
	}
}