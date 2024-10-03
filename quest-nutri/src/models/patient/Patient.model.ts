import mongoose, { Document, Schema } from 'mongoose'
import * as bcrypt from 'bcrypt'

export interface IPatient extends Document {
    name: string
    password: string
}

export const PatientSchema = new Schema<IPatient>({
    name: {
        type: String
    },
    password: {
        type: String,
        required: true
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