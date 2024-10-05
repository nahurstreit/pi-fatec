import { Response } from 'express'
import BaseError from './BaseError.error'

export default class ReqError extends BaseError {
	fields: {field: string, error: string}[]
	constructor(fields: {field: string, error: string}[]) {
		super()
		this.fields = fields
	}

	sendMessage(res: Response) {
		res.status(400).json({message: `Request Body have been sent incorrectly`, errors: this.fields}) //status is sent as json prop to be easilly accessed at frontend
	}
}