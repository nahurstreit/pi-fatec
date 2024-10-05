import app from './app'
import 'dotenv/config'
import dbConnect from './database/dbConnect'
import { checkEnvConfig } from './utils/checkEnvConfig'

const PORT = process.env.PORT || 3000

app.listen(PORT, () => {
	console.log(`Server running at port ${PORT}`)
	if(checkEnvConfig()) dbConnect() 
})