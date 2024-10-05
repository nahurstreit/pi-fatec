import { IsString, IsArray, IsEnum, IsNotEmpty, Matches, IsOptional } from 'class-validator'

export enum DayOfWeek {
    Sunday = 'Sunday',
    Monday = 'Monday',
    Tuesday = 'Tuesday',
    Wednesday = 'Wednesday',
    Thursday = 'Thursday',
    Friday = 'Friday',
    Saturday = 'Saturday',
}

export class CreateMealDto {
    @IsString()
    @IsNotEmpty()
    	name!: string

    @IsString()
    @IsNotEmpty()
    @Matches(/^([01]?[0-9]|2[0-3]):([0-5][0-9])$/, {
    	message: 'hour must be in HH:mm format',
    })
    	hour!: string

    @IsString()
    @IsOptional()
    	obs?: string
    
    @IsArray()
    @IsEnum(DayOfWeek, { each: true })
    	daysOfWeek!: DayOfWeek[]
}
