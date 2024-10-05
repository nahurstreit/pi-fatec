import { Document, Schema } from 'mongoose'
import { Meal, MealSchema } from './meal/Meal.interface'

export interface Diet extends Document {
    name: string
    meals?: Meal[]
}

export const DietSchema = new Schema<Diet>({
	name: {
		type: String,
		required: true,
	},
	meals: {
		type: [MealSchema],
		default: []
	}
}, {timestamps: true})