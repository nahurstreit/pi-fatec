package dao;

	import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtil;

		public class GenericDAO<T> {
		
		
	    private final Class<T> entityType;//Variavel generica que armazena a classe model que o DAO vai lidar.
	    /*
	     * Variavel que armazena
	     * a fábrica sessões do Hibernate usadas para interagir 
	     * com o banco de dados
	     */
	    private final SessionFactory sessionFactory;//

	    /*
	     * Construtor da classe que recebe como argumento as variaveis
	     * Class<T> que é uma classe de entidade do Model
	     * e a variavel para iniciar a fabrica de sessões para 
	     * interagir com o banco de dados
	     */
	    public GenericDAO(Class<T> entityType) {
	        this.entityType = entityType;
	        this.sessionFactory = HibernateUtil.getSessionFactory();
	    }
	    
	    /*
	     * Método para salvar os dados de um objeto no banco de dados
	     * seria algo como o CREATE, ele recebe a entidade que sera salva,
	     * abre uma sessão com o banco de dados, inicia uma transação para garantir
	     * atomicidade, salva essa entidade no banco e faz um commit dessa transação
	     * para que os dados possam ser persistidos de forma permanente.
	     */
	    public void insert(T entity) {
	        try (Session session = sessionFactory.openSession()) {
	            session.beginTransaction();
	            session.save(entity);
	            session.getTransaction().commit();
	        }
	    }
	    
	    /*
	     * Método para alterar os dados de um objeto no banco de dados
	     * seria algo como o UPTADE, ele recebe a entidade que sera atualizada,
	     * abre uma sessão com o banco de dados, inicia uma transação para garantir
	     * atomicidade, atualiza as informações dessa entidade no banco e faz um commit dessa transação
	     * para que os dados possam ser persistidos de forma permanente.
	     */

		public void merge(T entity) {
	        try (Session session = sessionFactory.openSession()) {
	            session.beginTransaction();
	            session.merge(entity);
	            session.getTransaction().commit();
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
	            session.delete(entity);
	            session.getTransaction().commit();
	        }
	    }
		
		/*
		 * Método que procura todas as entidades do tipo T presentes no banco de dados
		 * abre uma sessão do Banco de Dados, utiliza uma string de consulta simples 
		 * do Hibernate.query.Query, onde FROM + T.
		 * E retorna o resultado dessa Consulta.
		 */
		
		public List<T> findAll() {
		    try (Session session = sessionFactory.openSession()) {
		        String queryString = "FROM " + entityType.getSimpleName(); 
		        Query<T> query = session.createQuery(queryString, entityType); 
		        return query.getResultList(); 
		    }
		}
		
		/*
	     * Método para procurar os dados de um objeto especificado pela chave primaria 
	     * seria algo como o READ, ele recebe a entidade que sera pesquisada e o ID a ser pesquisado,
	     * abre uma sessão com o banco de dados,e verifica se aquela entidade com aquele ID existe
	     * retorna os dados dessa requisição como a entidade, o caso não exista retorna null.
	     */

	    public T findByPK(int id) {
	        try (Session session = sessionFactory.openSession()) {
	            return session.get(entityType, id);
	        }
	    }
	}
