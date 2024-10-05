import { NextFunction, Request, Response } from 'express'
import nutritionistService from '../services/nutritionist.service'
import NotFound from '../errors/NotFound.error'
import ShouldNeverHappen from '../errors/ShouldNeverHappen.error'

class NutritionistController {

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
			if (!nutritionist) return next(new NotFound('Nutritionist not found'))
			return res.status(201).json(nutritionist)
		} catch (error) {
			next(error)
		}
	}

	async updateById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const nutritionist = await nutritionistService.update(req.params.id, req.body)
			if (!nutritionist) return next(new NotFound('Nutritionist not foud'))
			return res.status(200).json(nutritionist)
		} catch (error) {
			next(error)
		}
	}

	async deleteById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const nutritionist = await nutritionistService.delete(req.params.id)
			if (!nutritionist) return next(new NotFound('Nutritionist not found'))
			return res.status(200).json({ message: 'Nutritionist deleted' })
		} catch (error) {
			next(error)
		}
	}

}

export default new NutritionistController()