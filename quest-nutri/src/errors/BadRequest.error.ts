import BaseError from './BaseError.error'

export default class BadRequest extends BaseError {
	constructor(message:string=`Bad Request`, status:number=400) {
		super(message, status)
	}
}