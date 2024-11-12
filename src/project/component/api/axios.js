import axios from "axios";

const api = axios.create({

    baseURL : "http://localhost:8001/",
    withCredentials: true, // 쿠키를 포함하도록 설정
})

export default api;