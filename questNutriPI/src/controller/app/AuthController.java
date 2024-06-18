/**
 * Package que contém os controllers da Aplicação.
 */
package controller.app;

import model.dao.UserDAO;
import model.entities.User;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.QuestNutriJOP;
import view.frames.ChangeUserFrame;

/**
 * Controlador responsavel por operacoes relacionadas a relacionadas a autenticacao do sistema.
 */
public abstract class AuthController {

	/**
	 * Método para adicionar um novo usuário.
	 *
	 * @param login       - Nome do usuário.
	 * @param password    - Senha do usuário.
	 * @param firstName   - Primeiro nome do usuário.
	 * @param systemLevel - Nível do usuário no sistema.
	 * @param onUpdate    - Interface para ação após atualização.
	 * @return Mensagem de sucesso ou erro.
	 */

	public static String createUser(String login, String password, String firstName, Integer systemLevel, IDoAction onUpdate) {
		try {
			// Verificar se o nome de usuário já existe
			User existingUser = UserDAO.findOne(" login LIKE '"+ login +"' ");
			if (existingUser != null) {
				return new LanguageUtil("Erro: Nome de usuário já existe.", "Error: this username already exists").get();
			}

			User user = new User(login, password, firstName, systemLevel);
			user.save();
			if(onUpdate != null) onUpdate.execute();
			return new LanguageUtil("Usuário adicionado com sucesso!", "User added successfully!").get();
		} catch (Exception e) {
			return new LanguageUtil("Erro ao adicionar usuário: ", "Error adding user: ").get() + e.getMessage();
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
				return new LanguageUtil("Usuário não encontrado.", "User not found.").get();
			}
			if (!user.getPassword().equals(oldPassword)) {
				return new LanguageUtil("Senha antiga incorreta.", "Incorrect old password.").get();
			}
			if (user.getPassword().equals(newPassword)) {
				return new LanguageUtil("A nova senha não pode ser igual à senha antiga.", "New password cannot be the same as the old password.").get();
			}
			user.setPassword(newPassword);
			user.save();
			return new LanguageUtil("Senha alterada com sucesso!", "Password changed successfully!").get();
		} catch (Exception e) {
			return new LanguageUtil("Erro ao alterar senha: ", "Error changing password: ").get() + e.getMessage();
		}
	}

	/**
	 * Método para logar no sistema.
	 *
	 * @param login    - Login usuário.
	 * @param password - Senha do usuário.
	 * @return true se o login foi bem sucedido, false caso contrário.
	 */
	public static boolean doLogin(String login, String password) {
		boolean res = false;
		String errMsg = "";
		try {
			User user = User.findOne("login = '"+ login+"'");
			if(user == null) {
				errMsg = new LanguageUtil("Usuário não encontrado", "User not found").get();
			} else if (!user.getPassword().equals(password)) {
				errMsg = new LanguageUtil("Senha incorreta", "Incorrect password").get();
			} else {
				QuestNutri.setConnectedUser(user);
				res = true;
			}
		} catch (Exception e) {
			errMsg = new LanguageUtil("Houve um erro na aplicação ao tentar fazer login.\nConsulte o log para mais informações.", "An error occurred while attempting to log in.\nCheck the log for more information.").get();
			e.printStackTrace();
		}
		
		if(!res) QuestNutriJOP.showMessageDialog(null, errMsg, new LanguageUtil("Impossível fazer login", "Unable to log in").get(), 1, null);
		return res;
	}

	/**
	 * Método para excluir um usuário.
	 *
	 * @param userId - ID do usuário a ser excluído.
	 * @return Mensagem de sucesso ou erro.
	 */
	public static String deleteUser(int userId) {
		try {
			User user = UserDAO.findByPK(userId);
			if (user == null) {
				return new LanguageUtil("Usuário não encontrado.", "User not found.").get();
			}
			user.delete();
			return new LanguageUtil("Usuário excluído com sucesso!", "User deleted successfully!").get();
		} catch (Exception e) {
			return new LanguageUtil("Erro ao excluir usuário: ", "Error deleting user: ").get() + e.getMessage();
		}
	}
	
	/**
	 * Método para atualizar todas as informações de um usuário.
	 *
	 * @param user        - Usuário a ser atualizado.
	 * @param login       - Novo nome de usuário.
	 * @param password    - Nova senha de usuário.
	 * @param firstName   - Novo primeiro nome do usuário.
	 * @param systemLevel - Novo nível do usuário no sistema.
	 * @param onUpdate    - Interface para ação após atualização.
	 * @return Mensagem de sucesso ou erro.
	 */

	public static String updateFullUser(User user, String login, String password, String firstName, int systemLevel, IDoAction onUpdate) {
		String msg = "";
		try {
			user.setLogin(login);
		} catch (Exception e) {}
		
		try {
			user.setPassword(password);
		} catch (Exception e) {}
		
		try {
			user.setFirstName(firstName);
		} catch (Exception e) {}
		
		try {
			user.setSystemLevel(systemLevel);
		} catch (Exception e) {}
		
		try {
			if(user.save()) {
				if(onUpdate != null) {
					onUpdate.execute();
					msg = new LanguageUtil("Usuário atualizado!", "User updated!").get();
				} else {
					msg =  new LanguageUtil("Não foi possível atualizar o usuário.", "Unable to update user.").get();
				}
			}
		} catch (Exception e) {
			msg = new LanguageUtil("Erro interno do sistema.", "System internal error.").get();

		}
		
		return msg;
		
	}
	
	/**
	 * Método para abrir o frame de alteração de usuário.
	 *
	 * @param user - Usuário a ser alterado.
	 */
	public static void openUserChangeFrame(User user) {
		ChangeUserFrame frame = new ChangeUserFrame(user);
		frame.setVisible(true);
	}
}
