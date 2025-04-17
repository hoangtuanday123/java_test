import { defineStore } from 'pinia'
export const userStore = defineStore('userStore', {
    state: () => ({
        // initialize state from local storage to enable user to stay logged in
        authToken: localStorage.getItem('authToken')
    }),
    actions: {
        saveToken(token: string) {
            // update pinia state
            this.authToken = token

            localStorage.setItem('authToken', token)
        },
        clearToken() {
            this.authToken = null;
            localStorage.removeItem('authToken')
        },
        
    }
});