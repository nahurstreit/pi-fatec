import { Router } from "express"
import handleRoute from "../helpers/handleRoute.js"
import { wrongPostPath } from "../helpers/responseUtils.js"
import alimentModel from "../models/alimentModel.js"

export default class AlimentRoutes {
    routes() {
        const router = Router()

        router.route('/')
            .get((req, res) => handleRoute(req, res, alimentModel.all))
            .post((req, res) => handleRoute(req, res, alimentModel.create))

        router.route('/taco')
            .get((req, res) => handleRoute(req, res, alimentModel.allTaco))

        router.route('/custom')
            .get((req, res) => handleRoute(req, res, alimentModel.allCustom))

        router.route('/:idAliment')
            .get((req, res) => handleRoute(req, res, alimentModel.findById))
            .delete((req, res) => handleRoute(req, res, alimentModel.delete))
            .put((req, res) => handleRoute(req, res, alimentModel.update))

        return router
    }
}