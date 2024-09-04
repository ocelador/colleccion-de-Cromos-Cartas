<template>
  <div class="container flex-column align-items-center vh-100">
    <!-- Contenedor principal para el título y las tarjetas -->
    <div class="collecciones-container p-4 rounded text-center">
      <h1>Colecciones</h1>
      <!-- Fila que contiene las tarjetas -->
      <div class="row justify-content-center">
        <div 
          v-for="(album, index) in albums" 
          :key="album.id" 
          class="col-lg-3 col-md-6 col-sm-6 mb-4 imagen-container">
          <div class="card" style="position: relative; overflow: hidden;">
            <img 
              :src="getImageUrl(album.id)" 
              :alt="album.nombre" 
              class="card-img-top" 
              style="width:100%; height:auto;" />

            <div class="card-img-overlay overlay">
              <h4 class="card-title">{{ album.nombre }}</h4>
              <p class="card-text">{{ album.descripcion }}</p>
              <router-link :to="{ name: 'Cromos', params: { albumId: album.id } }" class="btn btn-primary">Ver Más</router-link>
            </div>
          </div>
        </div>
      </div>
      <button @click="goToAlbumes" class="btn btn-primary mt-3">Editar Colección</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const albums = ref([]);
const router = useRouter();

const getAllAlbums = () => {
  axios.get('/api/albums', { headers: { 'Cache-Control': 'no-cache' } })
    .then(response => {
      albums.value = response.data;
    })
    .catch(error => {
      console.error('Error fetching albums:', error);
    });
};

const getImageUrl = (albumId) => {
  const timestamp = new Date().getTime();
  return `/api/albums/${albumId}/image?t=${timestamp}`;
};

const onImageError = (album) => {
  console.error(`Error loading image for album: ${album.nombre}`);
};

const goToCromos = (albumId) => {
  router.push({ name: 'Cromos', params: { albumId } });
};

const goToAlbumes = () => {
  router.push({ name: 'Albumes' });
};

onMounted(() => {
  getAllAlbums();
});
</script>

<style scoped>
.collecciones-container {
  background-color: #ffffff;
}

.imagen-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.card {
  transition: transform 0.2s ease-in-out;
}

.card:hover {
  transform: scale(1.05); /* Hace zoom cuando se pasa el ratón */
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.8); /* Fondo semitransparente */
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: opacity 0.2s ease-in-out;
}

.card:hover .overlay {
  opacity: 1; /* Muestra la superposición al pasar el ratón */
}

.imagen {
  max-width: 100%;
  height: auto;
}

.btn-primary {
  background-color: #007bff; /* Color del botón */
  border: none;
}

.btn-primary:hover {
  background-color: #0056b3; /* Color del botón al pasar el ratón */
}
</style>