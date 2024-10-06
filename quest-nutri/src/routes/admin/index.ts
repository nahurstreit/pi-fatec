import { Router } from "express";
import adminController from "../../controllers/admin.controller";

const adminRoutes = Router()

adminRoutes.route('/')
    .get(adminController.getAll)
    .post(adminController.create)

adminRoutes.route('/:id')
    .get(adminController.getById)
    .patch(adminController.updateById)
    .delete(adminController.deleteById)

export default adminRoutes