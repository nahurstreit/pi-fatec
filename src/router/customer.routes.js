import { Router } from "express"
import customerModel from "../models/customerModel.js"

export default class CustomerRoutes {
    routes() {
        const router = Router()

        router.route('/')
        .get((req, res) => res.status(200).json({ path: '/', method: 'get' }))
        .post((req,res) => res.status(200).json({path: '/', method: 'post'}))

        router.route('/:idCustomer')
        .post((req,res) => res.status(200).json({path: '/:idCustomer', method: 'post'}))
        .get((req,res) => res.status(200).json({path: '/:idCustomer', method: 'get'}))
        .delete((req,res) => res.status(200).json({path: '/:idCustomer', method: 'delete'}))
        .put((req,res) => res.status(200).json({path: '/:idCustomer', method: 'put'}))

        router.route('/:idCustomer/meal[s]?')
        .get((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?', method: 'get'}))
        .post((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?', method: 'post'}))

        router.route('/:idCustomer/meal[s]?/:idMeal')
        .post((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal', method: 'post'}))
        .get((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal', method: 'get'}))
        .delete((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal', method: 'delete'}))
        .put((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal', method: 'put'}))
        
        router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?')
        .get((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?', method: 'get'}))
        .post((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?', method: 'post'}))

        router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood')
        .post((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood', method: 'post'}))
        .get((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood', method: 'get'}))
        .delete((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood', method: 'delete'}))
        .put((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood', method: 'put'}))

        router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?')
        .get((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?', method: 'get'}))
        .post((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?', method: 'post'}))

        router.route('/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?/:idSubFood')
        .post((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?/:idSubFood', method: 'post'}))
        .get((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?/:idSubFood', method: 'get'}))
        .delete((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?/:idSubFood', method: 'delete'}))
        .put((req,res) => res.status(200).json({path: '/:idCustomer/meal[s]?/:idMeal/food[s]?/:idFood/subfood[s]?/:idSubFood', method: 'put'}))

        return router
    }
    
}