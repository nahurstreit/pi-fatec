import { IAdmin } from '../models/admin/Admin.model'
import adminRepository from '../repositories/admin.repository'

class AdminService{
	async findAll(){
		return await adminRepository.findAll('-password')
	}
	async findById(id: string){
		return await adminRepository.findById(id,'-password')
	}
	async findByEmail(email: string){
		return await adminRepository.findOneWhere({email}, 'password')
	}
	async create(data: Partial<IAdmin>){
		return await adminRepository.create(data as IAdmin,'-password')
	}
	async update(id: string, data: Partial<IAdmin>){
		return await adminRepository.update(id, data, '-password')
	}
	async delete(id: string){
		return await adminRepository.delete(id)
	}
}

export default new AdminService()