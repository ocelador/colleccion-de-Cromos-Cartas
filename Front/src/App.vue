<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main>
    <nav>
      <RouterLink active-class="active" to="/">Inicio</RouterLink>
      <RouterLink active-class="active" to="/collecciones">Collecciones</RouterLink>
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
}

a {
  text-decoration: none;
  color: #333;
  padding: 8px 16px;
  border-radius: 4px;
  transition: background-color 0.3s;
  margin-right: 25px;
  display: inline-block;
  font-weight: bold; 
  color: lightblue;
}

a:hover {
  background-color: rgba(33, 245, 245, 0.5);
}

nav {
  border-bottom: 1px solid black;
  text-align: center;
}

.active {
  font-weight: bold;
  color: red;
}

.active:hover {
  background-color: #ffe6e6;
}
</style>