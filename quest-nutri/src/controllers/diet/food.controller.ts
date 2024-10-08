import { NextFunction, Response } from 'express'
import { ContextRequest } from '../findContext.controller'
import ShouldNeverHappen from '../../errors/ShouldNeverHappen.error'

class FoodController {
	async getAllFoods(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			return res.status(200).json(req.meal?.foods)
		} catch (error) {
			next(error)
		}
	}

	async getFoodById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			return res.status(200).json(req.food)
		} catch (error) {
			next(error)
		}
	}


	async createFood(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			req.meal?.foods?.push(req.body)
			await req.patient?.save()
			return res.status(201).json(req.meal)
		} catch (error) {
			next(error)
		}
	}

	// Atualizar um alimento específico
	async updateFood(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.food) throw new ShouldNeverHappen('Food context not found')

			req.food.quantity = req.body.quantity || req.food.quantity
			req.food.unit = req.body.unit || req.food.unit
			req.food.obs = req.body.obs || req.food.obs
			req.food.subFoods = req.body.subFoods || req.food.subFoods

			await req.patient?.save()

			return res.status(200).json(req.food)
		} catch (error) {
			next(error)
		}
	}

	async deleteFood(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if (!req.food) throw new ShouldNeverHappen('Food context not found')

            req.meal!.foods = req.meal!.foods?.filter(food => food._id !== req.food!._id)

            await req.patient?.save()

            return res.status(200).json({ message: 'Food deleted successfully' })
		} catch (error) {
			next(error)
		}
	}
}

export default new FoodController()
