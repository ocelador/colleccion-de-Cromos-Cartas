<template>
    <div class="container d-flex flex-column align-items-center vh-100">
      <!-- Contenedor principal para el título y las tarjetas -->
      <div class="collecciones-container p-4 rounded shadow text-center">
        <h1>Colecciones</h1>
        <!-- Fila que contiene las tarjetas -->
        <div class="row justify-content-center mt-4">
          <div v-for="album in albums" :key="album.id" class="imagen-container">
            <p>este</p>
            <img :src="getImageUrl(album.imagen)" :alt="album.nombre" class="imagen img-fluid m-2" @error="onImageError(album)" />
          </div> <!-- Aquí se cierra el div -->
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
    if (!imagePath) {
        console.error("Invalid imagePath:", imagePath);
        return ''; // O una imagen predeterminada si lo prefieres
    }

    // Si la imagen está en la carpeta public, no necesitas hacer ninguna manipulación especial
    // Solo necesitas usar la ruta relativa desde /public
    const timestamp = new Date().getTime();
    const imageUrl = `${imagePath}?t=${timestamp}`;
    console.log(`Image URL: ${imageUrl}`);
    return imageUrl;
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
    background-color: #f8f9fa;
  }
  
  .imagen-container {
    display: flex;
    flex-direction: column;
    align-items: center;
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
  