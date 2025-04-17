<template>
    <q-page class="row items-center justify-evenly">
        <h1>{{welcome}}</h1>
    </q-page>
</template>

<script setup lang="ts">
import { ref,onMounted } from 'vue';
import api from '../services/api';
const welcome = ref('')
async function getWelcome() {
    try{
        const res = await api.auth.home();
        welcome.value = res;
    }
    catch (error) {
        console.error('Error fetching welcome message:', error);
        // Handle error (e.g., show a notification)
    }
    
}
onMounted(async () => {
    getWelcome()
})
</script>