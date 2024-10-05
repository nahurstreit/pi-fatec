import { IsNotEmpty, IsString } from 'class-validator'

export class DietDto {
    @IsNotEmpty()
    @IsString()
    	name!: string
}
