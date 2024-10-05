import { Document, Schema } from 'mongoose'
import { SubFood } from '../subfood/SubFood.interface'

export interface Food extends Document {
    quantity: number
    unit: string
    obs?: string
    subFoods?: SubFood[]
}

export const FoodSchema = new Schema<Food>({
	quantity: {
		type: Number,
		required: true,
	},
	unit: {
		type: String,
		required: true,
	},
	obs: {
		type: String
	}
})