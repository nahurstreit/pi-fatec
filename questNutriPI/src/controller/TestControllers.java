package controller;

import model.entities.User;

public class TestControllers {

    private static AuthController authController = new AuthController(); // Para teste do banco de dados
 // private static final Customer TESTE_CUSTOMER = Customer.findByPK(5); // Para teste do PDF

    public static void main(String[] args) {
        // PdfGeneratorController.generate(TESTE_CUSTOMER);
        testAddUser();
        testChangePassword();
        testLogin();
        testDeleteUser();
    }

    private static void testAddUser() {
        // Teste: Adicionar um novo usuário
        String result = authController.addUser("admin", "123", 3);
        System.out.println("addUser: " + result);

        // Teste: Adicionar um usuário com nome duplicado
        result = authController.addUser("adminQuestNutri", "1234", 3);
        System.out.println("addUser (duplicado): " + result);

        // Teste: Adicionar um usuário com nível de sistema inválido
        result = authController.addUser("novoUsuario", "senhaTeste", 4);
        System.out.println("addUser (nível de sistema inválido): " + result);
    }

    private static void testChangePassword() {
        // Supondo que o usuário com ID 1 já exista e tenha a senha "senhaTeste"
        int userId = 1;

        // Teste: Alterar a senha com a senha antiga correta
        String result = authController.changePassword(userId, "senhaTeste", "novaSenhaTeste");
        System.out.println("changePassword: " + result);

        // Teste: Alterar a senha com a senha antiga incorreta
        result = authController.changePassword(userId, "senhaIncorreta", "novaSenhaTeste");
        System.out.println("changePassword (senha antiga incorreta): " + result);

        // Teste: Alterar a senha para a mesma senha antiga
        result = authController.changePassword(userId, "novaSenhaTeste", "novaSenhaTeste");
        System.out.println("changePassword (mesma senha antiga): " + result);
    }

    private static void testLogin() {
        // Teste: Login com credenciais corretas
        String result = authController.login("usuarioTeste", "novaSenhaTeste");
        System.out.println("login: " + result);

        // Teste: Login com senha incorreta
        result = authController.login("usuarioTeste", "senhaIncorreta");
        System.out.println("login (senha incorreta): " + result);

        // Teste: Login com nome de usuário inexistente
        result = authController.login("UsuarioInexistente", "senhaTeste");
        System.out.println("login (usuário inexistente): " + result);
    }

    private static void testDeleteUser() {
        // Supondo que o usuário com ID 1 exista para ser excluído
        int userId = 4;

        // Teste: Excluir o usuário existente
        String result = authController.deleteUser(userId);
        System.out.println("deleteUser: " + result);

        // Teste: Tentar excluir um usuário que não existe mais
        result = authController.deleteUser(userId);
        System.out.println("deleteUser (usuário não existe): " + result);
    }
}
