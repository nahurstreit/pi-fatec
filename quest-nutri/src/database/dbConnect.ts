import mongoose, { mongo } from 'mongoose'
import 'dotenv/config'

const uri = `mongodb+srv://${process.env.DB_USERNAME}:${process.env.DB_PASSWORD}${process.env.DB_CONNECTION_URI}`

export default async (): Promise<boolean> => {
    try {
        console.log(`Connecting to database...`)
        await mongoose.connect(uri)
        console.log(`Connection stablished`)
        return true
    } catch(err) {
        console.log(err)
        return false
    }
}