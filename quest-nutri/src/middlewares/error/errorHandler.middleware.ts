import { NextFunction, Request, Response } from 'express'
import mongoose from 'mongoose'
import BaseError from '../../errors/BaseError.error'
import ReqError from '../../errors/ReqError.error'
import BadRequest from '../../errors/BadRequest.error'

export default function errorHandler(error: any, req: Request, res: Response, next: NextFunction) {
	console.error(error)
	switch(error.constructor.name) {
	case ReqError.name:
		error.sendMessage()
		break
	case mongoose.Error.CastError.name:
		new BadRequest(`Invalid '${error.kind}' with value '${error.value}'. ${error.reason}.`).sendMessage(res)
		break
	default:
		if(error instanceof BaseError) return error.sendMessage(res)
		new BaseError().sendMessage(res)
	}
}