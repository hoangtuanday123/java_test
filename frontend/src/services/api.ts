import axios, { AxiosError, AxiosResponse } from 'axios';
import { userStore } from '../stores/user.store'
import { storeToRefs } from 'pinia';
import pinia from '../stores/index';
const _userStore = userStore(pinia())

const {  authToken } = storeToRefs(_userStore);
interface Todo {
  id: string;
  title: string;
}

interface Login {
  username: string;
  password: string;
}

axios.defaults.baseURL = 'http://localhost:8080';

axios.interceptors.request.use((config) => {
    config.headers['Content-Type']='application/json'
  if (authToken.value) {
    config.headers.Authorization = `Bearer ${authToken.value}`;
  }
  return config;
});

axios.interceptors.response.use(
  (res) => res,
  (error: AxiosError) => {
    const { data, status, config } = error.response!;
    switch (status) {
      case 400:
        console.error(data);
        break;

      case 401:
        console.error('unauthorised');
        break;

      case 404:
        console.error('/not-found');
        break;

      case 500:
        console.error('/server-error');
        break;
    }
    return Promise.reject(error);
  }
);

const responseBody = <T>(response: AxiosResponse<T>) => response.data;

const request = {
  get: <T>(url: string) => axios.get<T>(url).then(responseBody),
  post: <T>(url: string, body: {}) =>
    axios.post<T>(url, body).then(responseBody),
};

const todos = {
  list: () => request.get<Todo[]>('/todos'),
  details: (id: string) => request.get<Todo>(`/todos/${id}`),
  create: (data: Todo) => request.post<void>('/todos', data),
};

const auth={
    login:(data:Login)=>request.post<string>('/login',data),
    home:()=>request.get<string>('/random')
}



const api = {
    auth
};

export default api;