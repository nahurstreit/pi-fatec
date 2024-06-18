package controller.entities;

import model.entities.Meal;
import utils.ConfirmDialog;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;

/**
 * Controlador responsável por operações relacionadas a refeições na aplicação.
 */
public abstract class MealController {
    /**
     * Salva uma refeição na base de dados com o nome, horário e dias da semana especificados.
     *
     * @param meal         Refeição a ser salva.
     * @param name         Nome da refeição.
     * @param hour         Horário da refeição.
     * @param daysOfWeek   Dias da semana em que a refeição ocorre.
     * @return true se a refeição foi salva com sucesso, false caso contrário.
     */
    public static boolean saveMeal(Meal meal, String name, String hour, int daysOfWeek) {
        boolean res = true;
        try {
            meal.setName(name)
                .setHour(hour)
                .setDaysOfWeek(daysOfWeek);
            
            res = meal.save();
        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        
        return res;
        
    }
    
    /**
     * Exclui uma refeição da base de dados após confirmação do usuário.
     *
     * @param meal   Refeição a ser excluída.
     * @return true se a refeição foi excluída com sucesso, false caso contrário.
     */
    public static boolean deleteMeal(Meal meal) {
        boolean res = false;
        if(ConfirmDialog.ask(new LanguageUtil("Você deseja mesmo excluir essa refeição?", "Do you really want to delete this meal?").get(), 
                new LanguageUtil("Excluir refeição.", "Delete meal.").get())) {
            try {
                res = meal.delete();
                if(res) QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Refeição excluída!", "Meal deleted!").get());
                else QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Houve um erro ao deletar a refeição.", "An error occurred while deleting the meal.").get());
            } catch (Exception e) {
                QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro interno do servidor.", "Internal server error.").get());
            }
        }
        
        return res;
    }

}
