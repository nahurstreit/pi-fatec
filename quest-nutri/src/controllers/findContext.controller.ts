import { NextFunction, Request, Response } from 'express'
import patientService from '../services/patient.service'
import NotFound from '../errors/NotFound.error'
import { IPatient } from '../models/patient/Patient.model'
import { Diet } from '../models/patient/diet/Diet.interface'
import { Meal } from '../models/patient/diet/meal/Meal.interface'
import { Food } from '../models/patient/diet/food/Food.interface'

export interface DietContextRequest extends Request {
    patient?: IPatient
    diet?: Diet
    meal?: Meal
    food?: Food
}

class FindPatientDietContextController {
	async findPatient(req: DietContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			const patient = await patientService.findById(patientId)
			if(!patient) throw new NotFound('Patient not found')
			req.patient = patient
			next()
		} catch (error) {
			next(error)
		}
	}

	async findDiet(req: DietContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const diet = req.patient?.diets?.find(diet => diet._id == req.params.dietId)
			if(!diet) throw new NotFound('Diet not found')
			req.diet = diet
			next()
		} catch (error) {
			next(error)
		}
	}

	async findMeal(req: DietContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const meal = req.diet?.meals?.find(meal => meal._id == req.params.mealId)
			if(!meal) throw new NotFound('Meal not found')
			req.meal = meal
			next()
		} catch (error) {
			next(error)
		}
	}

	async findFood(req: DietContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const food = req.meal?.foods?.find(food => food._id == req.params.foodId)
			if(!food) throw new NotFound('Food not found')
			req.food = food
			next()
		} catch (error) {
			next(error)
		}
	}
}

export default new FindPatientDietContextController()