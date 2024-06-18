package utils;

import java.time.LocalDate;

import model.entities.Customer;

/**
 * Classe utilitária para calcular o total de calorias diárias recomendadas para um Customer.
 */
public class DailyKcal {

    /**
     * Método para calcular a quantidade de kcal diária recomendada para um Customer.
     *
     * @param customer - O objeto Customer para o qual a recomendação de kcal será calculada.
     * @return A quantidade de kcal diária recomendada.
     */
    public static double calculateRecommendedKcal(Customer customer) {
		/**
		 *  Fórmula para calcular a TMB (Taxa Metabólica Basal) usando a equação de Harris-Benedict
		 */
        double bmr;
        int age = customer.getLocalDateBirth() != null ? LocalDate.now().getYear() - customer.getLocalDateBirth().getYear() : 0;

        if (customer.gender.equalsIgnoreCase("M")) {
            bmr = 88.362 + (13.397 * customer.getLastWeight().idWeight) + (4.799 * customer.height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * customer.getLastWeight().idWeight) + (3.098 * customer.height) - (4.330 * age);
        }

        // Ajuste para nível de atividade física
        double activityFactor;
        switch (customer.getActivityStatus()) {
            case 1:
                activityFactor = 1.2; // Sedentário
                break;
            case 2:
                activityFactor = 1.375; // Levemente ativo
                break;
            case 3:
                activityFactor = 1.55; // Moderadamente ativo
                break;
            case 4:
                activityFactor = 1.725; // Muito ativo
                break;
            case 5:
                activityFactor = 1.9; // Extremamente ativo
                break;
            default:
                activityFactor = 1.2; // Padrão para sedentário
                break;
        }

        /**
         *  Cálculo das kcal diárias recomendadas
         */
        double recommendedKcal = bmr * activityFactor;

        return recommendedKcal;
    }
}