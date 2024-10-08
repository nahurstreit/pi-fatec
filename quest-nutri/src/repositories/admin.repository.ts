import { BaseRepository } from './base.repository'
import AdminModel, {IAdmin} from '../models/admin/Admin.model'

class AdminRepository extends BaseRepository<IAdmin>{
	constructor(){
		super(AdminModel)
	}
}
export default new AdminRepository()