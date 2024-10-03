import { NextFunction, Request, Response } from 'express'
import BaseError from '../../errors/BaseError.error'
import ReqError from '../../errors/ReqError.error'

export default function errorHandler(error: any, req: Request, res: Response, next: NextFunction) {
    //console.error(error)
    switch(error.constructor) {
        case error instanceof ReqError:
            error.sendMessage()
            break
        default:
            if(error instanceof BaseError) return error.sendMessage(res)
            new BaseError().sendMessage(res)
    }
}