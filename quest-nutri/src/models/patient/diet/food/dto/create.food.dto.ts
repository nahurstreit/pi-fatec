import { IsNumber, IsString, IsNotEmpty, IsOptional } from 'class-validator'

export class CreateFoodDto {
    @IsNumber()
    	quantity!: number

    @IsString()
    @IsNotEmpty()
    	unit!: string

    @IsString()
    @IsOptional()
    	obs?: string
}
