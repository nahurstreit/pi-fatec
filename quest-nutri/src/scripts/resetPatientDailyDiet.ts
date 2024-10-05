import dbConnect from '../database/dbConnect'
import patientRepository from '../repositories/patient.repository'


dbConnect()
patientRepository.findAll().then(patients => {
	patients.forEach(patient => {
		console.log(patient)
	})
})