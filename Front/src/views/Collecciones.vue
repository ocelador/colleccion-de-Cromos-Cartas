<template>
    <div class="container d-flex flex-column align-items-center vh-100">
      <div class="collecciones-container p-4 rounded shadow text-center">
        <h1>Colecciones</h1>
        <div class="row justify-content-center mt-4">
          <div v-for="album in albums" :key="album.id" class="imagen-container" @click="goToCromos(album.id)">
            <img :src="getImageUrl(album.imagen)" :alt="album.nombre" class="imagen img-fluid m-2" @error="onImageError(album)" />
          </div>
          <div class="imagen-container">
            <button @click="goToAlbumes" class="btn btn-primary mt-3">Añadir Colección</button>
          </div>
        </div>
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
        console.error(error);
      });
  };
  
  const getImageUrl = (imagePath) => {
    const timestamp = new Date().getTime();
    return `${imagePath}?t=${timestamp}`;
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
    background-color: #f8f9fa;
  }
  
  .imagen-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    cursor: pointer;
  }
  
  .imagen {
    max-width: 100%;
    height: auto;
  }
  
  .btn-primary {
    background-color: #007bff;
    border: none;
  }
  
  .btn-primary:hover {
    background-color: #0056b3;
  }
  </style>