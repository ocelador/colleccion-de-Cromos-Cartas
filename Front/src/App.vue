<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main>
    <nav>
      <RouterLink active-class="active" to="/">Inicio</RouterLink>
      <RouterLink active-class="active" to="/collecciones">Colecciones</RouterLink>
    </nav>
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
  padding-left: 25px;
  padding-right: 25px;
  padding-top: 20px; /* Añado un padding-top para que no esté pegado al borde superior */
}

nav {
  border-bottom: 1px solid black;
  text-align: center;
  margin-bottom: 20px; /* Añade margen en la parte inferior del nav */
}

a {
  text-decoration: none;
  color: #333;
  padding: 8px 16px;
  border-radius: 4px;
  transition: background-color 0.3s, color 0.3s; /* Añadido color para una transición más suave */
  margin-right: 15px; /* Ajusta el margen para que no sea tan grande */
  display: inline-block;
  font-weight: bold; 
  color: lightblue;
}

a:hover {
  background-color: rgba(33, 245, 245, 0.5);
  color: #000; /* Cambia el color del texto al hacer hover para más contraste */
}

.active {
  font-weight: bold;
  color: red;
}

.active:hover {
  background-color: #ffe6e6;
}
</style>
