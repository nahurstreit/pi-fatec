import mongoose, {Document, mongo, Schema}from 'mongoose'
import * as bcrypt from 'bcrypt'

export interface IAdmin extends Document{
    email: string,
    password: string
}

export const AdminSchema = new Schema<IAdmin>({
	email: {type:String, required:true},
	password: {type:String, required:true}
},{
	timestamps: true
})
AdminSchema.pre('save', async function (next) {
	if(this.isModified('registeredAt')) this.invalidate('registeredAt', 'Cannot modify registeredAt')
	if(this.isModified('password')) this.password = await bcrypt.hash(this.password, 12)
	next()
})

export default mongoose.model('Admin', AdminSchema)
