import { IsNumber, IsString, IsOptional } from 'class-validator'

export class UpdateFoodDto {
    @IsNumber()
    @IsOptional()
    	quantity?: number

    @IsString()
    @IsOptional()
    	unit?: string

    @IsString()
    @IsOptional()
    	obs?: string

}
