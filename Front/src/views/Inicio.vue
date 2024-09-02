<template>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="inicio-container text-center p-4 rounded shadow">
            <h1>{{ message }}</h1>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const message = ref('Colleccion de Cromos/Cartas');
</script>

<style scoped>
.inicio-container {
    text-align: center;
    background-color: #f8f9fa;
}

@media (max-width: 768px) {
    .inicio-container {
        padding-bottom: 15vh;
    }

    .inicio-container h1 {
        font-size: 20px;
    }
}
</style>