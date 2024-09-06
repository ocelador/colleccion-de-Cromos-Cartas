<template>
  <div class="container d-flex align-items-center justify-content-center vh-100">
    <div class="inicio-container p-4 rounded flex-column w-100">
      <h1 class="my-4">{{ message }}</h1>
      <div id="carouselExampleRide" class="carousel slide" data-bs-ride="carousel" data-bs-interval="2000">
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
const images = ref([liga2, pokemon, magic, futbol]);
</script>

<style scoped>
.inicio-container {
  text-align: center;
  margin-top: -10vh; /* Sube el contenido hacia arriba */
}

h1 {
  margin-bottom: 40px; /* Incrementa el espaciado entre el título y el carrusel */
}

.img-custom {
  width: 100%;
  height: 400px; /* Ajusta la altura de las imágenes */
  object-fit: cover; /* Ajusta la imagen para llenar el contenedor sin distorsión */
}

/* Estilos para pantallas más pequeñas */
@media (max-width: 768px) {
  .inicio-container {
    padding-bottom: 15vh;
    margin-top: -5vh; /* Sube un poco en pantallas pequeñas */
  }

  .inicio-container h1 {
    font-size: 20px;
  }

  .img-custom {
    width: 100%;
    height: auto; /* Mantiene la relación de aspecto de la imagen */
    object-fit: cover;
  }
}
</style>
