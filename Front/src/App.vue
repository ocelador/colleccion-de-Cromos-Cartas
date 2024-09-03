<script setup>
import { RouterLink, RouterView } from 'vue-router';
import img from '@/assets/img.jpeg'; // Importando la imagen
</script>

<template>
  <div id="app" class="app-container">
    <!-- Main Content -->
    <div class="container-fluid">
      <div class="row justify-content-center"> <!-- Centra la fila -->
        <div class="col-12 col-md-2"> <!-- Ocupa 2 columnas en pantallas medianas hacia arriba -->
          <div class="card" style="width: 100%; position: relative; overflow: hidden;"> <!-- El ancho de la tarjeta ocupa el 100% de su contenedor -->
            <img class="card-img-top" :src="img" alt="Card image" style="width: 100%; height: auto;">
            <div class="card-img-overlay">
              <h4 class="card-title">John Doe</h4>
              <p class="card-text">Some example text.</p>
              <a href="#" class="btn btn-primary">See Profile</a>
            </div>
          </div>
        </div>
        <div class="col-12 col-md-2"> <!-- Ocupa 2 columnas en pantallas medianas hacia arriba -->
          <div class="card" style="width: 100%; position: relative; overflow: hidden;"> <!-- El ancho de la tarjeta ocupa el 100% de su contenedor -->
            <img class="card-img-top" :src="img" alt="Card image" style="width: 100%; height: auto;">
            <div class="card-img-overlay">
              <h4 class="card-title">John Doe</h4>
              <p class="card-text">Some example text.</p>
              <a href="#" class="btn btn-primary">See Profile</a>
            </div>
          </div>
        </div>
        <div class="col-12 col-md-2"> <!-- Ocupa 6 columnas en pantallas medianas hacia arriba -->
          <div class="card" style="width: 100%; position: relative; overflow: hidden;"> <!-- El ancho de la tarjeta ocupa el 100% de su contenedor -->
            <img class="card-img-top" :src="img" alt="Card image" style="width: 100%; height: auto;">
            <div class="card-img-overlay">
              <h4 class="card-title">John Doe</h4>
              <p class="card-text">Some example text.</p>
              <a href="#" class="btn btn-primary">See Profile</a>
            </div>
          </div>
        </div>
        <div class="col-12 col-md-2"> <!-- Ocupa 6 columnas en pantallas medianas hacia arriba -->
          <div class="card" style="width: 100%; position: relative; overflow: hidden;"> <!-- El ancho de la tarjeta ocupa el 100% de su contenedor -->
            <img class="card-img-top" :src="img" alt="Card image" style="width: 100%; height: auto;">
            <div class="card-img-overlay">
              <h4 class="card-title">John Doe</h4>
              <p class="card-text">Some example text.</p>
              <a href="#" class="btn btn-primary">See Profile</a>
            </div>
          </div>
        </div>
        <div class="col-12 col-md-2"> <!-- Ocupa 6 columnas en pantallas medianas hacia arriba -->
          <div class="card" style="width: 100%; position: relative; overflow: hidden;"> <!-- El ancho de la tarjeta ocupa el 100% de su contenedor -->
            <img class="card-img-top" :src="img" alt="Card image" style="width: 100%; height: auto;">
            <div class="card-img-overlay">
              <h4 class="card-title">John Doe</h4>
              <p class="card-text">Some example text.</p>
              <a href="#" class="btn btn-primary">See Profile</a>
            </div>
          </div>
        </div>
        <div class="col-12 col-md-2"> <!-- Ocupa 6 columnas en pantallas medianas hacia arriba -->
          <div class="card" style="width: 100%; position: relative; overflow: hidden;"> <!-- El ancho de la tarjeta ocupa el 100% de su contenedor -->
            <img class="card-img-top" :src="img" alt="Card image" style="width: 100%; height: auto;">
            <div class="card-img-overlay">
              <h4 class="card-title">John Doe</h4>
              <p class="card-text">Some example text.</p>
              <a href="#" class="btn btn-primary">See Profile</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>



.header-custom {
  display: flex;
  flex-wrap: nowrap;
}

.header-custom .nav-item {
  margin-right: 15px;
}

.header-custom .nav-item:last-child {
  margin-right: 0;
}

.card-img-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5); /* Fondo oscuro con opacidad para mejorar el contraste */
  color: #fff;
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.card:hover .card-img-overlay {
  opacity: 1;
}
#app {
display: block;
}
#app.app-container{
  width: 100% !important; /* Asegura que el contenedor ocupe el 100% del ancho */
  padding: 0 !important; /* Ajusta el padding si es necesario */
  margin: 0 auto !important; /* Centra el contenido si es necesario */
}
</style>


