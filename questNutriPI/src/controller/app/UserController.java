package controller.app;

import java.util.List;

import model.entities.User;

/**
 * Controlador responsável por operações relacionadas os usuários na aplicação.
 */
public abstract class UserController {
	/**
	 * Método para buscar usuários com base em um campo de pesquisa e termo de busca.
	 *
	 * @param searchField - Campo de pesquisa (atualmente suporta "User" para nome do usuário).
	 * @param searchTerm  - Termo de busca para a pesquisa.
	 * @return Lista de usuários que correspondem aos critérios de busca.
	 */
    public static List<User> searchUsers(String searchField, String searchTerm) {
        String searchParam = "";
        if (!searchTerm.isBlank()) {
            switch (searchField) {
                case "User":
                    searchParam = "name";
                    break;
            }
            
            searchParam += " LIKE '%" + searchTerm + "%'";
        }
        
        return User.findAll(searchParam);
    }
}