import { IsNotEmpty, IsString } from 'class-validator'

export default class CreateAdminDto{
    @IsNotEmpty()
    @IsString()
    	email!: string
    @IsNotEmpty()
    @IsString()
    	password!: string
}