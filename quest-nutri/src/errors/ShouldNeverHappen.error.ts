import BaseError from './BaseError.error'

export default class ShouldNeverHappen extends BaseError {
	constructor(happendAt: string, message:string=`This message should never be seen`) {
		super(message, 500)
		if(happendAt) console.log(`Logic error while `, happendAt)
	}
}