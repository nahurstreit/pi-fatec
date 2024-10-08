import { NextFunction, Request, Response } from 'express'
import { ContextRequest } from '../findContext.controller'
import ShouldNeverHappen from '../../errors/ShouldNeverHappen.error'

class MealController {
	async getAllMeals(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			return res.status(200).json(req.diet?.meals)
		} catch (error) {
			next(error)
		}
	}

	async getMealById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			return res.status(200).json(req.meal)
		} catch (error) {
			next(error)
		}
	}

	async createMeal(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			req.diet?.meals?.push(req.body)
			await req.patient?.save()
			return res.status(201).json(req.diet)
		} catch (error) {
			next(error)
		}

	}

	async updateMeal(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.meal) throw new ShouldNeverHappen('Meal context not found')

			req.meal.name = req.body.name || req.meal.name
			req.meal.hour = req.body.hour || req.meal.hour
			req.meal.obs = req.body.obs || req.meal.obs
			req.meal.foods = req.body.foods || req.meal.foods
			req.meal.daysOfWeek = req.body.daysOfWeek || req.meal.daysOfWeek


			await req.patient?.save()

			return res.status(200).json(req.meal)
		} catch (error) {
			next(error)
		}
	}

	async deleteMeal(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.meal) throw new ShouldNeverHappen('Meal context not found')

            req.diet!.meals = req.diet!.meals?.filter(meal => meal._id !== req.meal!._id)

            await req.patient?.save()

            return res.status(200).json({ message: 'Meal deleted successfully' })
		} catch (error) {
			next(error)
		}
	}


}

export default new MealController()