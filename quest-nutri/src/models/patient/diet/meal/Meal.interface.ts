import { Document, Schema } from 'mongoose'
import { Food, FoodSchema } from '../food/Food.interface'

export interface Meal extends Document {
    name: string
    hour: string
    obs?: string
    foods?: Food[]
    daysOfWeek: string[]
}

export const MealSchema = new Schema<Meal>({
	name: {
		type: String,
		required: true,
	},
	hour: {
		type: String,
		required: true,
	},
	obs: {
		type: String,
	},
	daysOfWeek: {
		type: [String],
		default: [],
		required: true
	},
	foods: {
		type: [FoodSchema],
		default: []
	}
})