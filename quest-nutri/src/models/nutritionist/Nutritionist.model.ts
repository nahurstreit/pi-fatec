import mongoose, { Document, Schema } from "mongoose"
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

export default mongoose.model('Nutritionist', NutritionistSchema)