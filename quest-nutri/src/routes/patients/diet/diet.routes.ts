import { Router } from 'express'
import findContextController from '../../../controllers/findContext.controller'
import dietController from '../../../controllers/diet/diet.controller'
import mealController from '../../../controllers/diet/meal.controller'
import foodController from '../../../controllers/diet/food.controller'
import { validateDto } from '../../../middlewares/validate/validateDto.middleware'
import { DietDto } from '../../../models/patient/diet/dto/generic.diet.dto'
import { CreateMealDto as CreateMealDto } from '../../../models/patient/diet/meal/dto/create.meal.dto'
import { UpdateMealDto } from '../../../models/patient/diet/meal/dto/update.meal.dto'
import { CreateFoodDto } from '../../../models/patient/diet/food/dto/create.food.dto'
import { UpdateFoodDto } from '../../../models/patient/diet/food/dto/update.food.dto'

const dietRoutes = Router({mergeParams: true})

const fcx = findContextController

dietRoutes.route('/').all(fcx.findPatient)
	.get(dietController.getAllDiets)
	.post(validateDto(DietDto), dietController.createDiet)

dietRoutes.route('/:dietId').all(fcx.findPatient, fcx.findDiet)
	.get(dietController.getDietById)
	.patch(validateDto(DietDto), dietController.updateDiet)
	.delete(dietController.deleteDiet)

dietRoutes.route('/:dietId/meal').all(fcx.findPatient, fcx.findDiet)
	.get(mealController.getAllMeals)
	.post(validateDto(CreateMealDto),mealController.createMeal)

dietRoutes.route('/:dietId/meal/:mealId').all(fcx.findPatient, fcx.findDiet, fcx.findMeal)
	.get(mealController.getMealById)
	.patch(validateDto(UpdateMealDto), mealController.updateMeal)
	.delete(mealController.deleteMeal)

dietRoutes.route('/:dietId/meal/:mealId/food').all(fcx.findPatient, fcx.findDiet, fcx.findMeal)
	.get(foodController.getAllFoods)
	.post(validateDto(CreateFoodDto),foodController.createFood)

dietRoutes.route('/:dietId/meal/:mealId/food/:foodId').all(fcx.findPatient, fcx.findDiet, fcx.findMeal, fcx.findFood)
	.get(foodController.getFoodById)
	.patch(validateDto(UpdateFoodDto),foodController.updateFood)
	.delete(foodController.deleteFood)

export default dietRoutes