<template>
    <div class="container justify-content-center align-items-center vh-100">
      <div class="inicio-container text-center p-4 rounded w-100">
        <h1 class="my-4">{{ message }}</h1>
        <div id="carouselExampleRide" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
          <div class="carousel-inner">
            <div v-for="(image, index) in images" :key="index" class="carousel-item" :class="{ active: index === 0 }">
              <img :src="image" class="d-block w-100 img-custom" alt="Imagen" />
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </div>
  </template>
  
  
  <script setup>
  import { ref } from 'vue';
  
  // Importa las imágenes
  import liga2 from '@/assets/cromos1.jpeg';
  import pokemon from '@/assets/cromos2.jpeg';
  import magic from '@/assets/cromos3.jpeg';
  import futbol from '@/assets/cromos4.jpeg';
  
  const message = ref('MundoCromo');
  const images = ref([liga2, pokemon, magic,futbol]);
  </script>
  
  <style scoped>
  .inicio-container {
    text-align: center;
    background-color: none;

  }
  
  @media (max-width: 768px) {

    .inicio-container {
      padding-bottom: 15vh;
    }
  
    .inicio-container h1 {
      font-size: 20px;
    }
    .img-custom {
    width: 100%;
    height: auto; /* Mantiene la relación de aspecto de la imagen */
    object-fit: cover; /* Ajusta la imagen para llenar el contenedor sin distorsión */
    }
  }
  </style>
  