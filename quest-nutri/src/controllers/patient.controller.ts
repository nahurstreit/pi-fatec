import { NextFunction, Request, Response } from 'express'
import patientService from '../services/patient.service'
import NotFound from '../errors/NotFound.error'
import ServerError from '../errors/ServerError.error'

class PatientController {
    async getById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
        try {
            const patient = await patientService.findById(req.params.id)
            if(!patient) return next(new NotFound('Patient not found'))
            return res.status(200).json(patient)
        } catch (error) {
            next(new ServerError(error))
        }
    }

    async getAll(req: Request, res: Response, next: NextFunction):  Promise<void | any> {
        try {
            const patients = await patientService.findAll()
            return res.status(200).json(patients)
        } catch (error) {
            next(new ServerError(error))
        }
    }

    async create(req: Request, res: Response, next: NextFunction): Promise<void | any> {
        try {
            const newPatient = await patientService.create(req.body)
            return res.status(201).json(newPatient)
        } catch (error) {
            next(new ServerError(error))
        }
    }

    async getByEmail(req: Request, res: Response, next: NextFunction): Promise<void | any> {
        try {
            const patient = await patientService.findByEmail(req.body.email)
            if(!patient) return next(new NotFound('Patient not found'))
            return res.status(201).json(patient)
        } catch (error) {
            next(new ServerError(error))
        }
        
    }

    async updateById(req: Request, res: Response, next: NextFunction): Promise<void | any>  {
        try {
            const patient = await patientService.update(req.params.id, req.body)
            if(!patient) return next(new NotFound('Patient not found'))
            return res.status(200).json(patient)
        } catch (error) {
            next(new ServerError(error))
        }
    }

    async deleteById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
        try {
            const patient = await patientService.delete(req.params.id)
            if(patient) return res.status(200).json({message: 'Patient deleted'})
            return next(new NotFound('Patient not found'))
        } catch (error) {
            next(new ServerError(error))
        }
    }

}

export default new PatientController()