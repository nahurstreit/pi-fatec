import { Request, Response, NextFunction } from 'express'
import jwt from 'jsonwebtoken'
import NotFound from '../errors/NotFound.error'

export default function authenticateToken (req: Request, res: Response, next: NextFunction) {
	const authHeader = req.headers['authorization']
	const token = authHeader && authHeader.split(' ')[1]

	if (token == null) return res.sendStatus(401)

	jwt.verify(token, process.env.JWT_SECRET as string, (err, user) => {
		if(err) return next(new NotFound())
		next()
	})
}
