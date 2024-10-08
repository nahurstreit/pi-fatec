import { NextFunction, Request, Response } from 'express'
import nutritionistService from '../services/nutritionist.service'
import NotFound from '../errors/NotFound.error'
import ShouldNeverHappen from '../errors/ShouldNeverHappen.error'
import { ContextRequest } from './findContext.controller'

class NutritionistController {
	async getNutritionist(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.nutritionist) throw new ShouldNeverHappen('Getting a nutritionist')
			return res.status(200).json(req.nutritionist)
		} catch (error) {
			next(error)
		}
	}

	async getById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const {nutritionistId} = req.headers
			if(!nutritionistId) throw new ShouldNeverHappen('Getting nutri info')
			const nutritionist = await nutritionistService.findById(nutritionistId as string)
			if (!nutritionist) return next(new NotFound('Nutritionist not found'))
			return res.status(200).json(nutritionist)
		} catch (error) {
			next(error)
		}
	}

	async getAll(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const nutritionists = await nutritionistService.findAll()
			return res.status(200).json(nutritionists)
		} catch (error) {
			next(error)
		}
	}

	async getByEmail(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const nutritionist = await nutritionistService.findByEmail(req.body.email)
			if (!nutritionist) throw new NotFound('Nutritionist not found')
			return res.status(201).json(nutritionist)
		} catch (error) {
			next(error)
		}
	}

	async updateById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.nutritionist) throw new ShouldNeverHappen('Updating a nutritionist')
			const updated = await nutritionistService.update(req.nutritionist._id as string, req.body)
			if(!updated) throw new NotFound('Nutritionist not foud')
			return res.status(200).json(updated)
		} catch (error) {
			next(error)
		}
	}

	async deleteById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.nutritionist) throw new ShouldNeverHappen('Deleting a nutritionist')
			const deleted = await nutritionistService.delete(req.nutritionist._id as string)
			if(!deleted) return next(new NotFound('Nutritionist not found'))
			return res.status(200).json({ message: 'Nutritionist deleted' })
		} catch (error) {
			next(error)
		}
	}

}

export default new NutritionistController()