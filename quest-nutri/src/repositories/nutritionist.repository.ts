import { BaseRepository } from './base.repository'
import NutritionistModel, { INutritionist } from '../models/nutritionist/Nutritionist.model'

class NutritionistRepository extends BaseRepository<INutritionist> {
	constructor() {
		super(NutritionistModel)
	}
}

export default new NutritionistRepository()