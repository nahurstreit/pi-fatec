import { IsOptional, IsString, IsEmail } from 'class-validator'

export class UpdatePatientDto {
    @IsOptional()
    @IsString()
    	name?: string

    @IsOptional()
    @IsEmail()
    	email?: string

    @IsOptional()
    //@IsStrongPassword({minLength: 8, minLowercase: 1, minUppercase: 1, minSymbols: 1})
    	password?: string

}
