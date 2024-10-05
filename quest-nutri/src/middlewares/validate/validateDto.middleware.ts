import { plainToInstance } from 'class-transformer'
import { validate, ValidationError } from 'class-validator'
import { NextFunction, Request, Response } from 'express'

export function validateDto(dtoClass: any) {
	return async (req: Request, res: Response, next: NextFunction): Promise<void | any> => {
		const dtoInstance = plainToInstance(dtoClass, req.body)

		const errors: ValidationError[] = await validate(dtoInstance)

		if (errors.length > 0) {
			const formattedErrors = errors.map(error => ({
				property: error.property,
				constraints: error.constraints
			}))
			return res.status(400).json({
				message: 'Validation failed',
				errors: formattedErrors
			})
		}

		next()
	}
}
