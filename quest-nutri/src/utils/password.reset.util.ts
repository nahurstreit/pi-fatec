import jwt from 'jsonwebtoken'

export function generatePasswordResetToken(userType: string, id: string) {
	return jwt.sign({ [`reset${userType}Id`]: id }, process.env.JWT_SECRET!, { expiresIn: '1d' })
}