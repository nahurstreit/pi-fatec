import { Document } from 'mongoose'
import { Aliment } from '../../../../../aliment/Aliment.model'

export interface SubFood extends Document {
    aliment: Aliment
    quantity: number
    unit: string
    obs?: string
}