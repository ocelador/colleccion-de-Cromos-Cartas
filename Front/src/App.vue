<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <main>
    <nav>
      
      <RouterLink active-class="active" to="/">Inicio</RouterLink>
       
    </nav>
    <RouterView />
  </main>
</template>

<style scoped>
.app-container {
    padding: 20px;
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.app-nav {
    display: flex;
    gap: 20px;
    margin-bottom: 20px;
}

.app-nav a {
    text-decoration: none;
    color: #333;
    padding: 8px 16px;
    border-radius: 4px;
    transition: background-color 0.3s;
}

.app-nav a:hover {
    background-color: #f1f1f1;
}

nav{
  border-bottom: 1px solid black;
  text-align: center;
}

.active {
    font-weight: bold;
    color: red;
}

a {
  margin-right: 25px;
}

.active:hover {
    background-color: #ffe6e6;
}

@media (max-width: 768px) {
    .app-nav {
        flex-direction: column;
        gap: 10px;
    }

    .app-nav a {
        padding: 10px;
        font-size: 18px;
    }

    .active {
        margin-right: 0;
    }
}

@media (max-width: 480px) {
    .app-nav a {
        padding: 8px;
        font-size: 16px;
    }
}
</style>