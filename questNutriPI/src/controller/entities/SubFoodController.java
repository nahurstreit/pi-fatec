package controller.entities;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.SubFood;
import utils.ConfirmDialog;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.generics.GenericJFrame;
import view.frames.NewSubFoodFrame;
import view.frames.UpdateSubFoodFrame;


/**
 * Controlador responsável por operações relacionadas a opções de substituição de alimentos.
 */
public abstract class SubFoodController {
    
    /**
     * Abre o frame para adicionar uma nova opção de substituição associada a uma food.
     *
     * @param callerFrame  Frame que chamou a abertura da nova SubFood.
     * @param food         Food à qual a subFood está associada.
     * @param afterUpdate  Ação a ser executada após a atualização da SubFood.
     */
    public static void openNewSubFoodFrame(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
        SubFood sbf = new SubFood().setFood(food);
        NewSubFoodFrame subFoodFrame = new NewSubFoodFrame(callerFrame.getCallerFrame(), sbf, afterUpdate);
        subFoodFrame.setVisible(true);
    }
    
    /**
     * Abre o frame para atualizar informações de uma SubFood.
     *
     * @param callerFrame  Frame que chamou a abertura da atualização da SubFood.
     * @param subFood      SubFood a ser atualizada.
     * @param afterUpdate  Ação a ser executada após a atualização da SubFood.
     */
    public static void openSubFoodUpdate(GenericJFrame callerFrame, SubFood subFood, IDoAction afterUpdate) {
        UpdateSubFoodFrame frame = new UpdateSubFoodFrame(callerFrame.getCallerFrame(), subFood, afterUpdate);
        frame.setVisible(true);
    }
    
    /**
     * Cria uma nova SubFood.
     *
     * @param subFood  SubFood a ser criada.
     * @return true se a SubFood foi criada com sucesso, false caso contrário.
     */
    public static boolean createNewSubFood(SubFood subFood) {
        boolean res;
        try {
            res = subFood.save();
            if(res) {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Novo alimento adicionado!", "New food added!").get());
            } else {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível adicionar o novo alimento.", "Unable to add new food.").get());
            }
        } catch (Exception e) {
            QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro no servidor.", "Server error.").get());
            e.printStackTrace();
            res = false;
        }

        return res;
    }
    
    /**
     * Atualiza o alimento básico (Aliment) associado a uma SubFood.
     *
     * @param subFood  SubFood a ser atualizada.
     * @param aliment  Alimento básico a ser associado à SubFood.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public static boolean updateSubFoodAliment(SubFood subFood, Aliment aliment) {
        boolean res = false;
        try {
            subFood.aliment = aliment;
            res = subFood.save();
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
    
    /**
     * Atualiza as informações de uma SubFood com novas unidades e quantidade.
     *
     * @param subFood    SubFood a ser atualizada.
     * @param unityQt    Unidade de quantidade da SubFood.
     * @param quantity   Quantidade da SubFood.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public static boolean updateSubFoodInfo(SubFood subFood, String unityQt, String quantity) {
        boolean res = false;
        try {
            subFood.setUnityQt(unityQt);
            try {
                subFood.setQuantity(Double.parseDouble(quantity.replace(',', '.')));
            } catch (Exception e) {}
            res = subFood.save();
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
    
    /**
     * Exclui uma SubFood após a confirmação do usuário.
     *
     * @param subFood  SubFood a ser excluída.
     * @return true se a SubFood foi excluída com sucesso, false caso contrário.
     */
    public static boolean deleteSubFood(SubFood subFood) {
        boolean res = false;
        boolean choice = ConfirmDialog.ask(new LanguageUtil("Deseja realmente excluir esse alimento substituto?", "Do you really want to delete this replacement food?").get(), 
                          new LanguageUtil("Confirmar Exclusão", "Confirm Delete").get());
        if(choice) {
            try {
                res = subFood.delete();
                if(res) QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento substituto excluído.", "Replacement Food deleted.").get());
            } catch (Exception e) {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível excluir o alimento.", "The food couldn't be excluded.").get());
            }    
        }
        
        return res;
    }

}
