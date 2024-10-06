import { IsEmail, IsNotEmpty } from "class-validator";

export default class CreateAdminDto{
    @IsNotEmpty()
    @IsEmail()
        email!: string
    @IsNotEmpty()
        password!: string
}