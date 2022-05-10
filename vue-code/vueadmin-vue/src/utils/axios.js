import axios from "axios";
import router from "../router";
import Element from "element-ui"
axios.defaults.baseURL = "http://localhost:8081"
const request = axios.create({
	timeout: 5000,
	headers: {
		'Content-Type': "application/json; charset=utf-8"
	}
})

// 添加请求拦截器
request.interceptors.request.use(config => {
	config.headers['Authorization'] = localStorage.getItem("token")
	return config
})

// 响应拦截器即异常处理
request.interceptors.response.use(
	response => {
		console.log("response ->" + response)
		let res = response.data
		if (res.code === 200) {
			return response
		} else {
			// 操作失败统一拦截
			Element.Message.error(!res.data.msg ? '系统异常' : res.data.msg)
			return Promise.reject(response.data.msg)
		}
	},
	error => {
		console.log(error)
		if (error.response.data) {
			error.massage = error.response.data.msg
		}
		if (error.response.status === 401) {
			router.push("/login")
		}
		Element.Message.error(error.massage, {duration: 3000})
		return Promise.reject(error)
	}
)

export default request