import { NextFunction, Request, Response } from 'express'
import adminService from '../services/admin.service'
import NotFound from '../errors/NotFound.error'

class AdminController {
	async getById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const admin = await adminService.findById(req.params.id)
			if (!admin) throw new NotFound('Admin not found')
			return res.status(200).json(admin)
		} catch (error) {
			next(error)
		}
	}

	async getByEmail(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const admin = await adminService.findByEmail(req.body.email)
			if (!admin) throw new NotFound('Admin not found')
			return res.status(201).json(admin)
		} catch (error) {
			next(error)
		}
	}

	async create(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const newAdmin = await adminService.create(req.body)
			return res.status(200).json(newAdmin)
		} catch (error) {
			next(error)
		}
	}

	async updateById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const admin = await adminService.update(req.params.id, req.body)
			if (!admin) throw new NotFound('Admin not foud')
			return res.status(200).json(admin)
		} catch (error) {
			next(error)
		}
	}

	async deleteById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const admin = await adminService.delete(req.params.id)
			if (!admin) throw new NotFound('Admin not found')
			return res.status(200).json({ message: 'Admin deleted' })
		} catch (error) {
			next(error)
		}
	}
}
export default new AdminController()