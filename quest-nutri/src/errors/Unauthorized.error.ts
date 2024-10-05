import BaseError from './BaseError.error'

export default class Unauthorized extends BaseError {
	constructor(message:string=`Unauthorized Attempt`, status:number=401) {
		super(message, status)
	}
}