<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main class="row d-flex">
    <!-- Barra de navegación -->
    <nav class="d-flex bg-light flex-column col-sm-2">
      <div class="container">
        <RouterLink class="navbar-brand" to="/">MundoCromo</RouterLink>
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <RouterLink class="nav-link" active-class="active" to="/">Inicio</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" active-class="active" to="/collecciones">Colecciones</RouterLink>
          </li>
        </ul>
      </div>
    </nav>
    
    <div class="col-sm-10">
      <!-- Contenido dinámico -->
      <RouterView />
    </div>
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
</style>
