package view.pages.settings;

import controller.app.AuthController;
import model.entities.User;
import utils.ConfirmDialog;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.generics.GenericJScrollPaneList;
import view.components.utils.IDoAction;

public class UserList extends GenericJScrollPaneList<User>{
	private static final long serialVersionUID = 1L;

	public UserList(IDoAction onUpdate) {
        super(User.findAll(" login NOT LIKE 'admin' "),
                new Object[] {"Username", new LanguageUtil("Nível de acesso", "System Access").get()},
                user -> new Object[] {user.getLogin(), user.getSysLevel()},
                user -> () -> {
                	AuthController.openUserChangeFrame(user);
                });

        addDeleteItemPopUpOption(new LanguageUtil("Excluir", "Delete").get(), user -> () -> {
        	if(ConfirmDialog.ask(new LanguageUtil("Tem certeza que deseja excluir o usuário?", "Are you sure you want to delete this user?").get(),
        					  new LanguageUtil("Excluir", "Delete").get())) {
        		if(onUpdate != null) onUpdate.execute();
        		QuestNutriJOP.showMessageDialog(null, AuthController.deleteUser(user.getId()));
        	};
            return false;
        });

        init();
    }
}
