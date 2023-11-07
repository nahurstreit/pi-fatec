import { Router } from "express"
import handleRoute from "../helpers/handleRoute.js"
import { wrongPostPath } from "../helpers/responseUtils.js"

import alimentCustomModel from "../models/alimentCustomModel.js"
import tacoModel from "../models/tacoModel.js"

export class TacoRoutes {
    routes() {
        const router = Router()

        router.route('/:idAliment')
            .get((req, res) => handleRoute(req, res, tacoModel.findById))
            .all((req, res) => res.status(403).json({mensagem: "Não é possível interagir assim com essa rota."}))

        return router
    }
}

export class AlimentCustomRoutes {
    routes() {
        const router = Router()

        router.route('/')
            .get((req, res) => res.status(200).json({mensagem: "Rota disponível para POST."}))
            .post((req, res) => handleRoute(req, res, alimentCustomModel.create))
        
        router.route('/:idAliment')
            .post((req, res) => handleRoute(req, res, wrongPostPath))
            .get((req, res) => handleRoute(req, res, alimentCustomModel.findById))
            .delete((req, res) => handleRoute(req, res, alimentCustomModel.delete))
            .put((req, res) => handleRoute(req, res, alimentCustomModel.update))

        return router
    }
}