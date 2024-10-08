import { IsOptional, IsString } from 'class-validator'

export default class UpdateAdminDto{
    @IsOptional()
    @IsString()
    	email?: string
    @IsOptional()
    @IsString()
    	password?: string
}