import { Model } from 'mongoose'
import { Document } from 'mongoose'

export abstract class BaseRepository<T extends Document> {
	protected model: Model<T>

	constructor(model: Model<T>) {
		this.model = model
	}

	async findAll(select=''): Promise<T[]> {
		return await this.model.find().select(select)
	}

	async findAllWhere(query: {}, select=''): Promise<T[]> {
		return await this.model.find(query).select(select)
	}

	async findById(id: string, select=''): Promise<T | null> {
		return await this.model.findById(id).select(select)
	}

	async findOneWhere(query: {}, select='') {
		return await this.model.findOne(query).select(select)
	}

	async create(data: T, select='') {
		const register = await this.model.create(data)
		return await this.model.findById(register._id).select(select)
	}

	async update(id: string, data: Partial<T>, select='') {
		return await this.model.findByIdAndUpdate(id, data, {new: true}).select(select)
	}

	async delete(id: string) {
		return await this.model.findByIdAndDelete(id)
	}
}