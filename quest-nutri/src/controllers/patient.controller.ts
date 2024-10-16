import { NextFunction, Request, Response } from 'express'
import patientService from '../services/patient.service'
import NotFound from '../errors/NotFound.error'
import ShouldNeverHappen from '../errors/ShouldNeverHappen.error'
import { generatePasswordResetToken } from '../utils/password.reset.util'
import { ContextRequest } from './findContext.controller'
import ServerError from '../errors/ServerError.error'
import { ObjectId } from 'mongoose'

class PatientController {
	async getPatient(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.patient) throw new ShouldNeverHappen('Finding a patient')
			return res.status(200).json(req.patient)
		} catch (error) {
			next(error)
		}
	}

	async getById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			const patient = await patientService.findById(patientId)
			if (!patient) throw new NotFound('Patient not found')
			return res.status(200).json(patient)
		} catch (error) {
			next(error)
		}
	}

	async getAll(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const {nutritionistId} = req.headers
			if(!nutritionistId) throw new ShouldNeverHappen('Getting all patients from one nutritionist')
			const patients = await patientService.findAllFromNutritionist(nutritionistId as string)
			return res.status(200).json(patients)
		} catch (error) {
			next(error)
		}
	}

	async getAllAsAdmin(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const patients = await patientService.findAll()
			return res.status(200).json(patients)
		} catch (error) {
			next(error)
		}
	}

	async create(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.nutritionist) throw new ShouldNeverHappen('Creating a new patient to a nutritionist')
			const newPatient = await patientService.create({...req.body, nutri: req.nutritionist._id})
			if(!newPatient) throw new ServerError('Impossible to create a new patient')
			req.nutritionist.patients.push(newPatient._id as ObjectId)
			await req.nutritionist.save()
			return res.status(201).json({patient: newPatient, activationToken: generatePasswordResetToken('Patient', newPatient!._id as string)})
		} catch (error) {
			next(error)
		}
	}

	async getByEmail(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const patient = await patientService.findByEmail(req.body.email)
			if (!patient) throw new NotFound('Patient not found')
			return res.status(201).json(patient)
		} catch (error) {
			next(error)
		}
	}

	async updateById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.patient) throw new ShouldNeverHappen('Updating a patient')
			const updated = await patientService.update(req.patient._id as string, req.body)
			if(!updated) throw new NotFound('Patient not found')
			return res.status(200).json(updated)
		} catch (error) {
			next(error)
		}
	}

	async deleteById(req: ContextRequest, res: Response, next: NextFunction): Promise<void | any> {
		try {
			if(!req.patient) throw new ShouldNeverHappen('Deleting a patient')
			const patientId = req.patient._id
			const deleted = await patientService.delete(req.patient._id as string)
			if(deleted) {
				const index = req.nutritionist?.patients.indexOf(patientId as ObjectId) ?? -1
				if(index !== -1) req.nutritionist?.patients.splice(index, 1)
				await req.nutritionist?.save()
				return res.status(200).json({ message: 'Patient deleted' })
			}
			throw new NotFound('Patient not found')
		} catch (error) {
			next(error)
		}
	}
}

export default new PatientController()