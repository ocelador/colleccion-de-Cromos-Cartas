<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container">
        <RouterLink class="navbar-brand" to="/">MundoCromo</RouterLink>
        <button 
          class="navbar-toggler" 
          type="button" 
          data-bs-toggle="collapse" 
          data-bs-target="#navbarNav" 
          aria-controls="navbarNav" 
          aria-expanded="false" 
          aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <RouterLink class="nav-link" active-class="active" to="/">Inicio</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" active-class="active" to="/collecciones">Colecciones</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" active-class="active" to="/albumes">Álbumes</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" active-class="active" to="/cromos">Cromos</RouterLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
    <!-- Contenido dinámico -->
    <RouterView />
  </main>
</template>

<style scoped>
html, body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}

main {
  padding: 20px;
}

.navbar-brand {
  font-weight: bold;
  color: #007bff; /* Color personalizado para la marca */
}

.navbar-nav .nav-link {
  color: #333;
  font-weight: 500;
  transition: color 0.3s ease-in-out;
}

.navbar-nav .nav-link:hover {
  color: #0056b3; /* Cambia el color del enlace al pasar el ratón */
}

.navbar-nav .nav-link.active {
  color: #007bff; /* Color del enlace activo */
  font-weight: bold;
}

.navbar-toggler {
  border: none;
}

.navbar-toggler-icon {
  background-color: #007bff; /* Color personalizado del icono */
}

.navbar-light .navbar-toggler:focus {
  box-shadow: none;
}
</style>

