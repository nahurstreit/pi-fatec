export function checkEnvConfig() {
	let result = true
	const requiredEnvVars = ['DB_USERNAME', 'DB_PASSWORD', 'DB_CONNECTION_URI', 'JWT_SECRET']
	const messageColor = {
		red: '\x1b[31m',
		green: '\x1b[32m',
		yellow: '\x1b[33m',
		blue: '\x1b[34m',
		magenta: '\x1b[35m',
		cyan: '\x1b[36m',
		white: '\x1b[37m',
		reset: '\x1b[0m'
	}

	const missingVars = requiredEnvVars.filter(key => !process.env[key])
    
	if(missingVars.length > 0) {
		result = false
		console.log(`[${messageColor.red}ERROR${messageColor.reset}] Found error at .env file: {
${missingVars.map(key => `\t${messageColor.red}* Missing required environment variable: ${messageColor.magenta}${key}${messageColor.reset}`).join('\n')}
}`)
	}


	if(!result) console.log(`Define missing environment variables in a .env file and reload.`)
	return result
}
