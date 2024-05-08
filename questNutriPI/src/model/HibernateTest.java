package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {
        //Configuração do Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("config/database/hibernate.cfg.xml");

        //Criação da fábrica de sessões
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Verifica se a conexão foi estabelecida com sucesso
        if (sessionFactory != null) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

            //Abre uma nova sessão de conexão
            Session session = sessionFactory.openSession();

            try {
                //Executa a query
                List<Aliment> aliments = session.createQuery("FROM Aliment", Aliment.class).getResultList();

                for (Aliment aliment : aliments) {
                    System.out.println(aliment);

                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Fecha a sessão
                session.close();
            }

            // Fecha a fábrica de sessões
            sessionFactory.close();
        } else {
            System.out.println("Falha ao estabelecer conexão com o banco de dados.");
        }
    }
}
