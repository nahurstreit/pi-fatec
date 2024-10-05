import BaseError from './BaseError.error'

export default class ServerConflict extends BaseError {
	constructor(message:string=`The request sent has conflicts with server`, status:number=409) {
		super(message, status)
	}
}