import { Router } from 'express'
import findContextController from '../../../controllers/findContext.controller'
import dietController from '../../../controllers/diet/diet.controller'
import mealController from '../../../controllers/diet/meal.controller'
import foodController from '../../../controllers/diet/food.controller'
import { validateDto } from '../../../middlewares/validate/validateDto.middleware'
import { DietDto } from '../../../models/patient/diet/dto/generic.diet.dto'

const mealRoutes = Router({mergeParams: true})

const fcx = findContextController

mealRoutes.route('/')
	.get(mealController.getAllMeals)
	.post(mealController.createMeal)



export default mealRoutes