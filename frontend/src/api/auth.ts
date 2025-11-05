import axios from 'axios'

export async function loginUser(email: string, password: string) {
    const response = await axios.post('/api/login', { email, password })
    return response.data // { role: 'admin' | 'user' | 'partner }
}