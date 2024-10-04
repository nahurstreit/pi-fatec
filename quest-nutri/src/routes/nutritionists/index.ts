import { Router } from "express";
import nutritionistController from "../../controllers/nutritionist.controller";

const nutritionistRoutes = Router()

nutritionistRoutes.route('/')
  .get(nutritionistController.getAll)
  .post(nutritionistController.create)

nutritionistRoutes.route('/:id')
  .get(nutritionistController.getById)
  .patch(nutritionistController.updateById)
  .delete(nutritionistController.deleteById)

export default nutritionistRoutes