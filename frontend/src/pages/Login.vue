<template>
    <q-page class="row items-center justify-evenly">
        <div class="q-pa-md" style="max-width: 400px">

            <q-form @submit="login" class="q-gutter-md">
                <q-input filled v-model="username" label="Username *" hint="Username" lazy-rules
                    :rules="[val => val && val.length > 0 || 'Please type something']" />

                <q-input v-model="password" filled :type="isPwd ? 'password' : 'text'" hint="Password with toggle" :rules="[val => val && val.length > 0 || 'Please type something']">
                    <template v-slot:append>
                        <q-icon :name="isPwd ? 'visibility_off' : 'visibility'" class="cursor-pointer"
                            @click="isPwd = !isPwd" />
                    </template>
                </q-input>


                <div>
                    <q-btn label="Submit" type="submit" color="primary" />
                </div>
            </q-form>

        </div>
    </q-page>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import api from '../services/api';
import { useRouter } from 'vue-router';
const router = useRouter();
import { userStore } from '../stores/user.store'
import { storeToRefs } from 'pinia';
import pinia from '../stores/index';
const _userStore = userStore(pinia())

const {  authToken } = storeToRefs(_userStore);
const username = ref('');
const password = ref('');
const isPwd= ref(true)

async function login() {
    try{
        const login={username: username.value, password: password.value}
        const res=await api.auth.login(login)
        _userStore.saveToken(res)
        router.push('/home')
    }
    catch (error) {
        console.error('Login failed:', error);
        // Handle error (e.g., show a notification)
    }
}
</script>