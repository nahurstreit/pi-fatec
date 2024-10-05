import { IsString, IsArray, IsEnum, Matches, IsOptional } from 'class-validator'
import { DayOfWeek } from './create.meal.dto'

export class UpdateMealDto {
    @IsString()
    @IsOptional()
    	name?: string

    @IsString()
    @IsOptional()
    @Matches(/^([01]?[0-9]|2[0-3]):([0-5][0-9])$/, {
    	message: 'hour must be in HH:mm format',
    })
    	hour?: string

    @IsString()
    @IsOptional()
    	obs?: string
    
    @IsArray()
    @IsOptional()
    @IsEnum(DayOfWeek, { each: true })
    	daysOfWeek?: DayOfWeek[]
}
