import { NextFunction, Request, Response } from 'express'
import { ContextRequest } from '../findContext.controller'
import ShouldNeverHappen from '../../errors/ShouldNeverHappen.error'

class DietController {
	async getAllDiets(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			return res.status(200).json(req.patient?.diets)
		} catch (error) {
			next(error)
		}
	}

	async getDietById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			return res.status(200).json(req.diet)
		} catch (error) {
			next(error)
		}
	}

	async createDiet(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			req.patient?.diets?.push(req.body)
			return res.status(201).json(await req.patient?.save())
		} catch (error) {
			next(error)
		}
	}

	async updateDiet(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {	
			req.diet!.name = req.body.name || req.diet!.name
			req.diet!.meals = req.body.meals || req.diet!.meals
	
			await req.patient?.save()
	
			return res.status(200).json(req.diet)
		} catch (error) {
			next(error)
		}
	}

	async deleteDiet(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {	
			req.patient!.diets = req.patient!.diets?.filter(diet => diet._id !== req.diet!._id)
	
			await req.patient?.save()
	
			return res.status(200).json({ message: 'Diet deleted successfully' })
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
}

export default new DietController()