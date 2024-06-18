package controller.entities;

import model.entities.Aliment;
import model.entities.Food;
import model.entities.Meal;
import utils.ConfirmDialog;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.generics.GenericJFrame;
import view.frames.FoodInfoFrame;
import view.frames.NewFoodFrame;
import view.frames.UpdateFoodFrame;
import view.pages.customer.diet.DietMealPanel;

/**
 * Controlador responsável por operações relacionadas a alimentos na aplicação.
 */
public abstract class FoodController {
    /**
     * Abre o frame para adicionar uma nova Food a uma Meal.
     *
     * @param callerFrame    Frame que chamou a abertura do novo alimento.
     * @param dietMealPanel  Painel de refeição da dieta.
     * @param meal           Refeição à qual o alimento está associado.
     */
    public static void openNewFoodFrame(GenericJFrame callerFrame, DietMealPanel dietMealPanel, Meal meal) {
        Food food = new Food().setMeal(meal);
        NewFoodFrame foodFrame = new NewFoodFrame(callerFrame, dietMealPanel, food);
        foodFrame.setVisible(true);
    }
    
    /**
     * Abre o frame para atualizar informações de uma Food.
     *
     * @param callerFrame   Frame que chamou a abertura da atualização do alimento.
     * @param food          Alimento a ser atualizado.
     * @param afterUpdate   Ação a ser executada após a atualização.
     */
    public static void openFoodUpdate(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
        UpdateFoodFrame frame = new UpdateFoodFrame(callerFrame.getCallerFrame(), food, afterUpdate);
        frame.setVisible(true);
    }
    
    /**
     * Atualiza a Food com um novo alimento básico (Aliment).
     *
     * @param food      Food a ser atualizada.
     * @param aliment   Aliment a ser associado a Food.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public static boolean updateFoodAliment(Food food, Aliment aliment) {
        boolean res = false;
        try {
            food.aliment = aliment;
            res = food.save();
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
    
    
    /**
     * Atualiza as informações do alimento com novas unidades e quantidade.
     *
     * @param food      Food a ser atualizada.
     * @param unityQt   Unidade de quantidade do alimento.
     * @param quantity  Quantidade do alimento.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public static boolean updateFoodInfo(Food food, String unityQt, String quantity) {
        boolean res = false;
        try {
            food.setUnityQt(unityQt);
            try {
                food.setQuantity(Double.parseDouble(quantity.replace(',', '.')));
            } catch (Exception e) {}
            res = food.save();
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        return res;
    }
    
    
    /**
     * Cria uma nova Food em uma refeição.
     *
     * @param food  Food a ser criada.
     * @return true se a food foi criada com sucesso, false caso contrário.
     */
    public static boolean createNewFood(Food food) {
        boolean res = false;
        try {
            res = food.save();
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
     * Abre o frame com informações detalhadas sobre um alimento em uma refeição.
     *
     * @param callerFrame   Frame que chamou a abertura das informações do alimento.
     * @param food          Alimento para exibir informações.
     * @param afterUpdate   Ação a ser executada após a atualização das informações.
     */
    public static void openFoodInfo(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
        FoodInfoFrame f = new FoodInfoFrame(callerFrame, food, afterUpdate);
        f.setVisible(true);
    }
    
    /**
     * Exclui uma Food de uma Meal após a confirmação do usuário.
     *
     * @param food  Food a ser excluída.
     * @return true se o alimento foi excluído com sucesso, false caso contrário.
     */
    public static boolean deleteFood(Food food) {
        boolean res = false;
        boolean choice = ConfirmDialog.ask(new LanguageUtil("Deseja realmente excluir esse alimento?", "Do you really want to delete this food?").get(), 
                          new LanguageUtil("Confirmar Exclusão", "Confirm Delete").get());
        if(choice) {
            try {
                res = food.delete();
                if(res) QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento excluído.", "Food deleted.").get());
            } catch (Exception e) {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível excluir o alimento.", "The food couldn't be excluded.").get());
            }   
        }
        
        return res;
    }
    
}
