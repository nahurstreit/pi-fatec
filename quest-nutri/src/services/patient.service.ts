import { IPatient } from '../models/patient/Patient.model'
import patientRepository from '../repositories/patient.repository'

class PatientService {
	async findAll() {
		return await patientRepository.findAll('-password')
	}

	async findAllFromNutritionist(nutritionistId: string) {
		return await patientRepository.findAllWhere({nutri: nutritionistId}, '-password')
	}

	async findById(id: string) {
		return await patientRepository.findById(id, '-password')
	}

	async findByEmail(email: string) {
		return await patientRepository.findOneWhere({email}, 'password')
	}

	async create(data: Partial<IPatient>) {
		return await patientRepository.create(data as IPatient, '-password')
	}

	async update(id: string, data: Partial<IPatient>) {
		return await patientRepository.update(id, data, '-password')
	}

	async delete(id: string) {
		return await patientRepository.delete(id)
	}
}

export default new PatientService()