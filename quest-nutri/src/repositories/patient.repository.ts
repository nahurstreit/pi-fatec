import { BaseRepository } from './base.repository'
import PatientModel, { IPatient } from '../models/patient/Patient.model'

class PatientRepository extends BaseRepository<IPatient> {
	constructor() {
		super(PatientModel)
	}
}

export default new PatientRepository()