import app from './app'
import 'dotenv/config'
import dbConnect from './database/dbConnect'

const PORT = process.env.PORT || 3000

app.listen(PORT, () => {
    console.log(`Server running at port ${PORT}`)
    dbConnect()
})