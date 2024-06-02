//package controller;
//
//import javax.swing.JOptionPane;
//
//import model.entities.Customer;
//import utils.CpfValidate;
//
//public class TestControllers {
//
//	private static AuthController authController = new AuthController(); // Para teste do banco de dados
//	private static final Customer TESTE_CUSTOMER = Customer.findByPK(5); // Para teste do PDF
//
//	public static void main(String[] args) {
////		PdfGeneratorController.generate(TESTE_CUSTOMER);
////        testAddUser();
////        testChangePassword();
////        testLogin();
////        testDeleteUser();
//	}
//
//
//	private static void testAddUser() {
//		// Teste: Adicionar um novo usuário
//		String result = authController.addUser("miguel", "12345", 3);
//		System.out.println("addUser: " + result);
//
//		// Teste: Adicionar um usuário com nome duplicado
//		result = authController.addUser("adminQuestNutri", "1234", 3);
//		System.out.println(result);
//
//		// Teste: Adicionar um usuário com nível de sistema inválido
//		result = authController.addUser("novoUsuario", "senhaTeste", 4);
//		System.out.println(result);
//	}
//
//	private static void testChangePassword() {
//		// Supondo que o usuário com ID 1 já exista e tenha a senha "senhaTeste"
//
//		// Teste: Alterar a senha com a senha antiga correta
//		String result = authController.changePassword(4, "12345", "novaSenhaTeste");
//		System.out.println("changePassword: " + result);
//
//		// Teste: Alterar a senha com a senha antiga incorreta
//		result = authController.changePassword(4, "senhaIncorreta", "novaSenhaTeste");
//		System.out.println(result);
//
//		// Teste: Alterar a senha para a mesma senha antiga
//		result = authController.changePassword(4, "novaSenhaTeste", "123456");
//		System.out.println(result);
//	}
//
//	private static void testLogin() {
//		// Teste: Login com credenciais corretas
//		String result = authController.login("miguel", "123456");
//		System.out.println(result);
//
//		// Teste: Login com senha incorreta
//		result = authController.login("miguel", "senhaIncorreta");
//		System.out.println(result);
//
//		// Teste: Login com nome de usuário inexistente
//		result = authController.login("UsuarioInexistente", "senhaTeste");
//		System.out.println(result);
//	}
//
//	private static void testDeleteUser() {
//		// Supondo que o usuário com ID 1 exista para ser excluído
//
//		// Teste: Excluir o usuário existente
//		String result = authController.deleteUser(4);
//		System.out.println("deleteUser: " + result);
//
//		// Teste: Tentar excluir um usuário que não existe mais
//		result = authController.deleteUser(4);
//		System.out.println("deleteUser (usuário não existe): " + result);
//	}
//}
package controller;

