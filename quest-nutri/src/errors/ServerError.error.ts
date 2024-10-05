import BaseError from './BaseError.error'

export default class ServerError extends BaseError {
	constructor(error:unknown=null, message:string=`Server internal error`, status:number=500)  {
		super(message, status)
		if(error) {
			console.log(error)
		}
	}
}