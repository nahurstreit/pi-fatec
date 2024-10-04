import { NextFunction, Request, Response } from "express";
import nutritionistService from "../services/nutritionist.service";
import NotFound from "../errors/NotFound.error";
import ServerError from "../errors/ServerError.error";

class NutritionistController {

  async getById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
    try {
      const nutritionist = await nutritionistService.findById(req.params.id)
      if (!nutritionist) return next(new NotFound('Nutritionist not found'))
      return res.status(200).json(nutritionist)
    } catch (error) {
      next(new ServerError(error))
    }
  }

  async getAll(req: Request, res: Response, next: NextFunction): Promise<void | any> {
    try {
      const nutritionists = await nutritionistService.findAll()
      return res.status(200).json(nutritionists)
    } catch (error) {
      next(new ServerError(error))
    }
  }

  async getByEmail(req: Request, res: Response, next: NextFunction): Promise<void | any> {
    try {
      const nutritionist = await nutritionistService.findByEmail(req.body.email)
      if (!nutritionist) return next(new NotFound('Nutritionist not found'))
      return res.status(201).json(nutritionist)
    } catch (error) {
      next(new ServerError(error))
    }
  }

  async create(req: Request, res: Response, next: NextFunction): Promise<void | any> {
    try {
      const newNutritionist = await nutritionistService.create(req.body)
      return res.status(200).json(newNutritionist)
    } catch (error) {
      next(new ServerError(error))
    }
  }

  async updateById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
    try {
      const nutritionist = await nutritionistService.update(req.params.id, req.body)
      if (!nutritionist) return next(new NotFound('Nutritionist not foud'))
      return res.status(200).json(nutritionist)
    } catch (error) {
      next(new ServerError(error))
    }
  }

  async deleteById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
    try {
      const nutritionist = await nutritionistService.delete(req.params.id)
      if (!nutritionist) return next(new NotFound('Nutritionist not found'))
      return res.status(200).json({ message: 'Nutritionist deleted' })
    } catch (error) {
      next(new ServerError(error))
    }
  }

}

export default new NutritionistController()