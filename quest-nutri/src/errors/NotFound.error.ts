import BaseError from './BaseError.error'

export default class NotFound extends BaseError {
	constructor(message:string=`Page not found`) {
		super(message, 404)
	}
}