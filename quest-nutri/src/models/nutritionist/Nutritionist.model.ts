import mongoose, { Document, Schema } from 'mongoose'
import * as bcrypt from 'bcrypt'

export interface INutritionist extends Document {
	name: string,
	email: string,
	password: string,
	patients: string
}

export const NutritionistSchema = new Schema<INutritionist>({
	name: { type: String, required: true },
	email: { type: String, required: true },
	password: { type: String, required: true },
	patients: { type: String }
}, {
	timestamps: true
})

NutritionistSchema.pre('save', async function (next) {
	if(this.isModified('registeredAt')) this.invalidate('registeredAt', 'Cannot modify registeredAt')
	if(this.isModified('password')) this.password = await bcrypt.hash(this.password, 12)
	next()
})

export default mongoose.model('Nutritionist', NutritionistSchema)