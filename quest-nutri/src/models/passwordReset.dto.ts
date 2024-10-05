import { IsEmail, IsNotEmpty } from 'class-validator'

export class PasswordResetDto {
    @IsNotEmpty()
    //@IsStrongPassword({minLength: 8, minLowercase: 1, minUppercase: 1, minSymbols: 1})
    	password!: string

}

export class SendPasswordResetDto {
    @IsNotEmpty()
    @IsEmail()
    	email!: string
}