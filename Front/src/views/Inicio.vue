<script setup>
import { ref } from 'vue';

const message = ref('¡Bienvenido a la página de los coches!');
</script>

<template>
    <div class="main-container">
        <div class="inicio-container">
            <h1>{{ message }}</h1>
            <img src="@/assets/marcas.jpg" alt="Marcas de coches" class="imagen" />
        </div>
    </div>
</template>

<style scoped>
body, html {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
}

.main-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 90vh;
    width: 60vw;
    box-sizing: border-box;
}

.inicio-container {
    padding: 20px;
    border-radius: 8px;
    text-align: center;
    padding-bottom: 45vh;
}

.inicio-container h1 {
    font-size: 24px;
    color: #333;
}

.imagen {
    margin-top: 3vh;
    width: 50%;
    height: auto;
    border-radius: 8px;
}

@media (max-width: 768px) {
    .main-container {
        width: 80vw;
        height: auto;
    }

    .inicio-container {
        padding: 15px;
        padding-bottom: 15vh;
    }

    .inicio-container h1 {
        font-size: 20px;
    }

    .imagen {
        width: 70%;
    }
}

@media (max-width: 480px) {
    .main-container {
        width: 90vw;
        height: auto;
    }

    .inicio-container {
        padding: 10px;
        padding-bottom: 10vh;
    }

    .inicio-container h1 {
        font-size: 18px;
    }

    .imagen {
        width: 90%;
    }
}
</style>