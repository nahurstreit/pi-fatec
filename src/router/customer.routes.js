import { Router } from "express"
import handleRoute from "../helpers/handleRoute.js"
import { wrongPostPath } from "../helpers/responseUtils.js"

import customerModel from "../models/customerModel.js"
import mealModel from "../models/mealModel.js"
import foodModel from "../models/foodModel.js"
import subFoodModel from "../models/subFoodModel.js"

export default class CustomerRoutes {
    routes() {
        const router = Router()

        //Customer
            router.route('/')
            .get((req, res) => handleRoute(req, res, customerModel.all))
            .post((req, res) => handleRoute(req, res, customerModel.create))

            router.route('/:idCustomer')
            .post((req, res) => handleRoute(req, res, wrongPostPath))
            .get((req, res) => handleRoute(req, res, customerModel.findById))
            .delete((req, res) => handleRoute(req, res, customerModel.delete))
            .put((req, res) => handleRoute(req, res, customerModel.update))

        //Meals
            router.route('/:idCustomer/meal[s]?')
            .get((req, res) => handleRoute(req, res, mealModel.all))
            .post((req, res) => handleRoute(req, res, mealModel.create))

            router.route('/:idCustomer/meal[s]?/:idMeal')
            .post((req, res) => handleRoute(req, res, wrongPostPath))
            .get((req, res) => handleRoute(req, res, mealModel.findMealById))
            .delete((req, res) => handleRoute(req, res, mealModel.delete))
            .put((req, res) => handleRoute(req, res, mealModel.update))

        //Foods
            router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?')
            .get((req, res) => handleRoute(req, res, foodModel.all))
            .post((req, res) => handleRoute(req, res, foodModel.create))

            router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood')
            .post((req, res) => handleRoute(req, res, wrongPostPath))
            .get((req, res) => handleRoute(req, res, foodModel.findFoodById))
            .delete((req, res) => handleRoute(req, res, foodModel.delete))
            .put((req, res) => handleRoute(req, res, foodModel.update))

        //SubFoods
            router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?')
            .get((req, res) => handleRoute(req, res, subFoodModel.all))
            .post((req, res) => handleRoute(req, res, subFoodModel.create))

            router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?/:idSubFood')
            .post((req, res) => handleRoute(req, res, wrongPostPath))
            .get((req, res) => handleRoute(req, res, subFoodModel.findSubFoodById))
            .delete((req, res) => handleRoute(req, res, subFoodModel.delete))
            .put((req, res) => handleRoute(req, res, subFoodModel.update))

        return router
    }
}