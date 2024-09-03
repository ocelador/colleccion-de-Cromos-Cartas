<template>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="collecciones-container text-center p-4 rounded shadow">
            <h1>Collecciones</h1>
            <div class="d-flex justify-content-around flex-wrap">
                <div v-for="album in albums" :key="album.id" class="imagen-container">
                    <img :src="getImageUrl(album.imagen)" :alt="album.nombre" class="imagen img-fluid m-2" @error="onImageError(album)" />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const albums = ref([]);

const getAllAlbums = () => {
    axios.get('/api/albums', { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
            console.log(response.data); // Depuración: Verifica la respuesta de la API
            albums.value = response.data;
        })
        .catch(error => {
            console.error(error);
        });
};

const getImageUrl = (imagePath) => {
    // Añade un parámetro de consulta único para evitar el caché
    const timestamp = new Date().getTime();
    return `${imagePath}?t=${timestamp}`;
};

const onImageError = (album) => {
    console.error(`Error loading image for album: ${album.nombre}`);
};

onMounted(() => {
    getAllAlbums();
});
</script>

<style scoped>
.collecciones-container {
    text-align: center;
    background-color: #f8f9fa;
}

.imagen-container {
    margin: 15px;
    width: 250px; 
    height: auto; 
}

.imagen {
    width: 100%;
    height: auto;
    transition: transform 0.3s ease, filter 0.3s ease;
}

.imagen:hover {
    transform: scale(1.15);
    filter: brightness(0.15); 
}

@media (max-width: 768px) {
    .collecciones-container {
        padding-bottom: 15vh;
    }

    .collecciones-container h1 {
        font-size: 20px;
    }

    .imagen-container {
        width: 100px; 
    }
}
</style>