import { INutritionist } from '../models/nutritionist/Nutritionist.model'
import nutritionistRepository from '../repositories/nutritionist.repository'

class NutritionistService {
	async findAll() {
		return await nutritionistRepository.findAll('-password')
	}

	async findById(id: string) {
		return await nutritionistRepository.findById(id, '-password')
	}

	async findByEmail(email: string) {
		return await nutritionistRepository.findOneWhere({ email }, 'password')
	}

	async create(data: Partial<INutritionist>) {
		return await nutritionistRepository.create(data as INutritionist, '-password')
	}

	async update(id: string, data: Partial<INutritionist>) {
		return await nutritionistRepository.update(id, data, '-password')
	}

	async delete(id: string) {
		return await nutritionistRepository.delete(id)
	}
}

export default new NutritionistService()