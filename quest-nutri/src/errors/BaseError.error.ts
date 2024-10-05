import { Response } from 'express'

export default class BaseError extends Error {
	message: string
	status: number

	constructor(message: string = `Server Internal Error`, status: number = 500) {
		super()
		this.message = message
		this.status = status
	}

	sendMessage(res: Response) {
		res.status(this.status).json({error: this.message, status: this.status}) //status is sent as json prop to be easilly accessed at frontend
	}
}