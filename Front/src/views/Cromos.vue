<template>
  <div class="container">
    <h1>Gestión de Cromos</h1>
    <div class="form-group">
      <label for="nombre">Nombre:</label>
      <input type="text" v-model="cromo.nombre" class="form-control" id="nombre">
    </div>
    <div class="form-group">
      <label for="descripcion">Descripción:</label>
      <input type="text" v-model="cromo.descripcion" class="form-control" id="descripcion">
    </div>
    <div class="form-group">
      <label for="anio">Año:</label>
      <input type="number" v-model="cromo.anio" class="form-control" id="anio">
    </div>
    <div class="form-group">
      <label for="valor">Valor:</label>
      <input type="number" v-model="cromo.valor" class="form-control" id="valor">
    </div>
    <div class="form-group">
      <label for="rareza">Rareza:</label>
      <input type="text" v-model="cromo.rareza" class="form-control" id="rareza">
    </div>
    <button @click="createCromo" class="btn btn-primary">Agregar Cromo</button>
    <button @click="updateCromo" class="btn btn-warning">Actualizar Cromo</button>
    <button @click="goToCollecciones" class="btn btn-secondary">Volver</button>

    <h2>Lista de Cromos</h2>
    <ul class="list-group">
      <li v-for="cromo in cromos" :key="cromo.id" class="list-group-item">
        {{ cromo.nombre }} - {{ cromo.descripcion }} - {{ cromo.anio }} - {{ cromo.valor }} - {{ cromo.rareza }}
        <div>
          <button @click="selectCromo(cromo)" class="btn btn-info btn-sm">Seleccionar</button>
          <button @click="deleteCromoById(cromo.id)" class="btn btn-danger btn-sm">Eliminar</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

export default {
  data() {
    return {
      cromos: [],
      cromo: {
        id: null,
        nombre: '',
        descripcion: '',
        anio: null,
        valor: null,
        rareza: '',
        album: null // Añadir album al objeto cromo
      }
    };
  },
  methods: {
    getAllCromos() {
      const albumId = this.$route.params.albumId;
      axios.get(`/api/albums/${albumId}/cromos`, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.cromos = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    createCromo() {
      const albumId = this.$route.params.albumId;
      this.cromo.album = { id: albumId }; // Asignar album al cromo
      axios.post('/api/cromos', this.cromo, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.getAllCromos(); // Actualiza la lista de cromos
          this.resetCromo();
        })
        .catch(error => {
          console.error('Error al crear el cromo:', error);
        });
    },
    updateCromo() {
      const albumId = this.$route.params.albumId;
      this.cromo.album = { id: albumId }; // Asignar album al cromo
      axios.put(`/api/cromos/${this.cromo.id}`, this.cromo, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.getAllCromos(); // Actualiza la lista de cromos
          this.resetCromo();
        })
        .catch(error => {
          console.error('Error al actualizar el cromo:', error);
        });
    },
    deleteCromo() {
      axios.delete(`/api/cromos/${this.cromo.id}`, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.getAllCromos(); // Actualiza la lista de cromos
          this.resetCromo();
        })
        .catch(error => {
          console.error(error);
        });
    },
    deleteCromoById(id) {
      axios.delete(`/api/cromos/${id}`, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.getAllCromos(); // Actualiza la lista de cromos
        })
        .catch(error => {
          console.error(error);
        });
    },
    selectCromo(cromo) {
      this.cromo = { ...cromo };
    },
    resetCromo() {
      this.cromo = {
        id: null,
        nombre: '',
        descripcion: '',
        anio: null,
        valor: null,
        rareza: '',
        album: null // Reiniciar album
      };
    },
    goToCollecciones() {
      this.$router.push({ name: 'Collecciones' });
    }
  },
  mounted() {
    this.getAllCromos();
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.btn {
  margin-right: 10px;
  margin-top: 10px;
}

.list-group-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.list-group-item > div {
  display: flex;
  gap: 10px;
}
</style>