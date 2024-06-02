package controller;

import model.dao.UserDAO;
import model.entities.User;
import view.QuestNutri;
import view.components.QuestNutriJOP;

public class AuthController {

	/**
	 * Método para adicionar um novo usuário.
	 *
	 * @param name        - Nome do usuário.
	 * @param password    - Senha do usuário.
	 * @param systemLevel - Nível do usuário no sistema.
	 * @return Mensagem de sucesso ou erro.
	 */
	public String addUser(String name, String password, Integer systemLevel) {
		try {
			// Verificar se o nome de usuário já existe
			User existingUser = UserDAO.findOne("name", name);
			if (existingUser != null) {
				return "Erro: Nome de usuário já existe.";
			}

			// Verificar se o systemLevel está no intervalo válido
			if (systemLevel < 1 || systemLevel > 3) {
				return "Erro: Nível do sistema deve ser entre 1 e 3.";
			}

			User user = new User(name, password, name, systemLevel);
			user.save();
			return "Usuário adicionado com sucesso!";
		} catch (Exception e) {
			return "Erro ao adicionar usuário: " + e.getMessage();
		}
	}

	/**
	 * Método para trocar a senha de um usuário.
	 *
	 * @param userId      - ID do usuário.
	 * @param oldPassword - Senha antiga do usuário.
	 * @param newPassword - Nova senha do usuário.
	 * @return Mensagem de sucesso ou erro.
	 */
	public String changePassword(int userId, String oldPassword, String newPassword) {
		try {
			User user = UserDAO.findByPK(userId);
			if (user == null) {
				return "Usuário não encontrado.";
			}
			if (!user.getPassword().equals(oldPassword)) {
				return "Senha antiga incorreta.";
			}
			if (user.getPassword().equals(newPassword)) {
				return "A nova senha não pode ser igual à senha antiga.";
			}
			user.setPassword(newPassword);
			user.save();
			return "Senha alterada com sucesso!";
		} catch (Exception e) {
			return "Erro ao alterar senha: " + e.getMessage();
		}
	}

	/**
	 * Método para logar no sistema.
	 *
	 * @param name     - Nome do usuário.
	 * @param password - Senha do usuário.
	 * @return Mensagem de sucesso ou erro.
	 */
	public static boolean doLogin(String login, String password) {
		boolean res = false;
		String errMsg = "";
		try {
			User user = User.findOne("login = '"+ login+"'");
			if(user == null) {
				errMsg = "Usuário não encontrado";
			} else if (!user.getPassword().equals(password)) {
				errMsg = "Senha incorreta";
			} else {
				QuestNutri.setConnectedUser(user);
				res = true;
			}
		} catch (Exception e) {
			errMsg = "Houve um erro na aplicação ao tentar fazer login.\nConsulte o log para mais informações.";
			e.printStackTrace();
		}
		
		if(!res) QuestNutriJOP.showMessageDialog(null, errMsg, "Impossível fazer login", 1, null);
		return res;
	}

	/**
	 * Método para excluir um usuário.
	 *
	 * @param userId - ID do usuário a ser excluído.
	 * @return Mensagem de sucesso ou erro.
	 */
	public String deleteUser(int userId) {
		try {
			User user = UserDAO.findByPK(userId);
			if (user == null) {
				return "Usuário não encontrado.";
			}
			user.delete();
			return "Usuário excluído com sucesso!";
		} catch (Exception e) {
			return "Erro ao excluir usuário: " + e.getMessage();
		}
	}
}
