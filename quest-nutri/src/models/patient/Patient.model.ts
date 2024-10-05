import mongoose, { Document, ObjectId, Schema } from 'mongoose'
import * as bcrypt from 'bcrypt'
import { Diet, DietSchema } from './diet/Diet.interface'

export interface IPatient extends Document {
    name: string
    email?: string
    password: string
    details?: {
        birth?: string
        height?: number
    },
	nutri: ObjectId
    activeDiet?: Diet
    diets?: Diet[]
    dailyMealRecord?: any[]
}

export const PatientSchema = new Schema<IPatient>({
	name: {
		type: String
	},
	email: {
		type: String,
	},
	password: {
		type: String,
	},
	details: {
		birth: String,
		height: Number
	},
	nutri: {
		type: Schema.Types.ObjectId,
		ref: 'Nutritionist'
	},
	diets: {
		type: [DietSchema],
		default: []
	}
}, {
	timestamps: true
})

PatientSchema.pre('save', async function (next) {
	if(this.isModified('registeredAt')) this.invalidate('registeredAt', 'Cannot modify registeredAt')
	if(this.isModified('password')) this.password = await bcrypt.hash(this.password, 12)
	next()
})

export default mongoose.model('Patient', PatientSchema)