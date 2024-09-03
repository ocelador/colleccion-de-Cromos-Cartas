<template>
    <div class="container">
      <h1>Gestión de Álbumes</h1>
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" v-model="album.nombre" class="form-control" id="nombre">
      </div>
      <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <input type="text" v-model="album.descripcion" class="form-control" id="descripcion">
      </div>
      <div class="form-group">
        <label for="anio">Año:</label>
        <input type="number" v-model="album.anio" class="form-control" id="anio">
      </div>
      <button @click="createAlbum" class="btn btn-primary">Agregar Álbum</button>
      <button @click="updateAlbum" class="btn btn-warning">Actualizar Álbum</button>
  
      <h2>Lista de Álbumes</h2>
      <ul class="list-group">
        <li v-for="album in albums" :key="album.id" class="list-group-item">
          {{ album.nombre }} - {{ album.descripcion }} - {{ album.anio }}
          <div>
            <button @click="selectAlbum(album)" class="btn btn-info btn-sm">Seleccionar</button>
            <button @click="deleteAlbumById(album.id)" class="btn btn-danger btn-sm">Eliminar</button>
          </div>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        albums: [],
        album: {
          id: null,
          nombre: '',
          descripcion: '',
          anio: null
        }
      };
    },
    methods: {
      getAllAlbums() {
        axios.get('/api/albums', { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.albums = response.data;
          })
          .catch(error => {
            console.error(error);
          });
      },
      createAlbum() {
        axios.post('/api/albums', this.album, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.getAllAlbums();
            this.resetAlbum();
          })
          .catch(error => {
            console.error(error);
          });
      },
      updateAlbum() {
        axios.put(`/api/albums/${this.album.id}`, this.album, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.getAllAlbums();
            this.resetAlbum();
          })
          .catch(error => {
            console.error(error);
          });
      },
      deleteAlbumById(id) {
        axios.delete(`/api/albums/${id}`, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            this.getAllAlbums();
          })
          .catch(error => {
            console.error(error);
          });
      },
      selectAlbum(album) {
        this.album = { ...album };
      },
      resetAlbum() {
        this.album = {
          id: null,
          nombre: '',
          descripcion: '',
          anio: null
        };
      }
    },
    mounted() {
      this.getAllAlbums();
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