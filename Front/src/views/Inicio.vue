<template>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="inicio-container text-center p-4 rounded shadow">
            <h1>{{ message }}</h1>
            <div class="carousel-container">
                <div class="carousel">
                    <div v-for="(image, index) in images" :key="index" class="carousel-item" :class="{ active: index === activeIndex }">
                        <img :src="image" alt="Imagen" class="carousel-image" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

// Importa las imágenes
import liga2 from '@/assets/liga2.jpg';
import pokemon from '@/assets/pokemon.jpg';
import magic from '@/assets/magic.jpg';

const message = ref('Colección de Cromos/Cartas');
const images = ref([liga2, pokemon, magic]);
const activeIndex = ref(0);

const rotateImages = () => {
    setInterval(() => {
        activeIndex.value = (activeIndex.value + 1) % images.value.length;
    }, 3000); // Cambia de imagen cada 3 segundos
};

onMounted(() => {
    rotateImages();
});
</script>

<style scoped>
.inicio-container {
    text-align: center;
    background-color: #f8f9fa;
}

.carousel-container {
    margin-top: 20px;
    width: 600px;
    height: 500px;
    overflow: hidden;
    position: relative;
    margin-left: 33vw;
}

.carousel {
    display: flex;
    transition: transform 0.5s ease-in-out;
}

.carousel-item {
    min-width: 100%;
    transition: opacity 0.5s ease-in-out;
    opacity: 0;
    position: absolute;
    top: 0;
    left: 0;
}

.carousel-item.active {
    opacity: 1;
    position: relative;
}

.carousel-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

@media (max-width: 768px) {
    .inicio-container {
        padding-bottom: 15vh;
    }

    .inicio-container h1 {
        font-size: 20px;
    }

    .carousel-container {
        width: 250px;
        height: 150px;
    }
}
</style>