import {Request, Response, NextFunction} from 'express'
import NotFound from '../../errors/NotFound.error'

export default function notFoundHandler(req: Request, res: Response, next: NextFunction) {
	next(new NotFound())
}