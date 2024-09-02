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
      <button @click="deleteCromo" class="btn btn-danger">Eliminar Cromo</button>
  
      <h2>Lista de Cromos</h2>
      <ul class="list-group">
        <li v-for="cromo in cromos" :key="cromo.id" class="list-group-item">
          {{ cromo.nombre }} - {{ cromo.descripcion }} - {{ cromo.anio }} - {{ cromo.valor }} - {{ cromo.rareza }}
          <button @click="selectCromo(cromo)" class="btn btn-info btn-sm">Seleccionar</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
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
          rareza: ''
        }
      };
    },
    methods: {
      getAllCromos() {
        axios.get('/api/cromos', { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.cromos = response.data;
          })
          .catch(error => {
            console.error(error);
          });
      },
      createCromo() {
        axios.post('/api/cromos', this.cromo, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.getAllCromos();
            this.resetCromo();
          })
          .catch(error => {
            console.error(error);
          });
      },
      updateCromo() {
        axios.put(`/api/cromos/${this.cromo.id}`, this.cromo, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.getAllCromos();
            this.resetCromo();
          })
          .catch(error => {
            console.error(error);
          });
      },
      deleteCromo() {
        axios.delete(`/api/cromos/${this.cromo.id}`, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.getAllCromos();
            this.resetCromo();
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
          rareza: ''
        };
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
  </style>