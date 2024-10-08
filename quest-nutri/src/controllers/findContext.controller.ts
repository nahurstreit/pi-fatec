import { NextFunction, Request, Response } from 'express'
import patientService from '../services/patient.service'
import NotFound from '../errors/NotFound.error'
import { IPatient } from '../models/patient/Patient.model'
import { Diet } from '../models/patient/diet/Diet.interface'
import { Meal } from '../models/patient/diet/meal/Meal.interface'
import { Food } from '../models/patient/diet/food/Food.interface'
import { INutritionist } from '../models/nutritionist/Nutritionist.model'
import nutritionistService from '../services/nutritionist.service'
import ShouldNeverHappen from '../errors/ShouldNeverHappen.error'

export interface ContextRequest extends Request {
	nutritionist?: INutritionist
    patient?: IPatient
    diet?: Diet
    meal?: Meal
    food?: Food
}

class FindContextController {
	async injectNutritionist(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { nutritionistId } = req.params
			if(!nutritionistId) throw new ShouldNeverHappen('While injecting a nutritionist to headers')
			req.headers.nutritionistId = nutritionistId
			next()
		} catch (error) {
			next(error)
		}
	}

	async findNutritionist(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { nutritionistId } = req.headers
			const nutritionist = await nutritionistService.findById(nutritionistId as string)
			if(!nutritionist) throw new NotFound('Nutritionist not found')
			req.nutritionist = nutritionist
			next()
		} catch (error) {
			next(error)
		}
	}

	async injectPatient(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			if(!patientId) throw new ShouldNeverHappen('While injecting a patient to headers')
			req.headers.patientId = patientId
			next()
		} catch (error) {
			next(error)
		}
	}

	async findPatient(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.headers
			const patient = await patientService.findById(patientId as string)
			if(!patient) throw new NotFound('Patient not found')
			req.patient = patient
			next()
		} catch (error) {
			next(error)
		}
	}

	async findDiet(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const diet = req.patient?.diets?.find(diet => diet._id == req.params.dietId)
			if(!diet) throw new NotFound('Diet not found')
			req.diet = diet
			next()
		} catch (error) {
			next(error)
		}
	}

	async findMeal(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const meal = req.diet?.meals?.find(meal => meal._id == req.params.mealId)
			if(!meal) throw new NotFound('Meal not found')
			req.meal = meal
			next()
		} catch (error) {
			next(error)
		}
	}

	async findFood(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
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

export default new FindContextController()