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
    <button @click="resetCromo" class="btn btn-danger">Limpiar</button>
    <button @click="goToCollecciones" class="btn btn-secondary">Volver</button>

    <h2>Lista de Cromos</h2>
    <div class="form-group special-input">
      <label for="filtroNombre">Filtrar por Nombre:</label>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">
            <img src="@/assets/lupa.png" alt="Lupa" style="width: 25px; height: 25px;">
          </span>
        </div>
        <input type="text" v-model="filtroNombre" class="form-control" id="filtroNombre" placeholder="Buscar...">
      </div>
    </div>
    <div class="form-group special-input">
      <label for="filtroCriterio">Ordenar por:</label>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon2">
            <img src="@/assets/desplegable.png" alt="desplegable" style="width: 25px; height: 25px;">
          </span>
        </div>
        <select v-model="filtroCriterio" class="form-control" id="filtroCriterio">
          <option value="">Ninguno</option>
          <option value="nombre">Nombre</option>
          <option value="valor">Valor</option>
          <option value="rareza">Rareza</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label>
        <input type="checkbox" v-model="mostrarNoAdquiridos">
        Mostrar cromos no adquiridos
      </label>
    </div>
    <ul class="list-group">
      <li v-for="cromo in filteredCromos" :key="cromo.id" :class="{'bg-gray': !cromo.adquirido}" class="list-group-item">
        {{ cromo.nombre }} - {{ cromo.descripcion }} - {{ cromo.anio }} - {{ cromo.valor }} - {{ cromo.rareza }} - {{ cromo.adquirido ? 'Adquirido' : 'No Adquirido' }}
        <div>
          <button @click="selectCromo(cromo)" class="btn btn-info btn-sm">{{ cromo.adquirido ? 'Seleccionar' : 'Agregar' }}</button>
          <button @click="markAsNotAcquired(cromo)" class="btn btn-secondary btn-sm" v-if="cromo.adquirido">No Adquirido</button>
          <button @click="confirmDeleteCromo(cromo.id)" class="btn btn-danger btn-sm">Eliminar</button>
        </div>
      </li>
    </ul>
    <ConfirmModal
      v-if="cromoIdToDelete !== null"
      :visible="cromoIdToDelete !== null"
      title="Confirmar Eliminación"
      message="¿Estás seguro de que deseas eliminar este cromo?"
      :confirmRoute="`/api/cromos/${cromoIdToDelete}`"
      :cancelRoute="$route.fullPath"
      :returnRoute="$route.fullPath"
      @update-cromos="getAllCromos"
      @close-modal="closeModal"
    />
  </div>
</template>

<script>
import axios from 'axios';
import ConfirmModal from '@/components/ConfirmModal.vue';

export default {
  components: {
    ConfirmModal
  },
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
      },
      filtroNombre: '', // Añadir propiedad reactiva para el filtro
      filtroCriterio: '', // Añadir propiedad reactiva para el criterio de filtrado
      mostrarNoAdquiridos: false, // Añadir propiedad reactiva para el checkbox
      cromoIdToDelete: null // Añadir propiedad reactiva para almacenar el ID del cromo a eliminar
    };
  },
  computed: {
    filteredCromos() {
      let filtered = this.cromos.filter(cromo => {
        return cromo.nombre.toLowerCase().includes(this.filtroNombre.toLowerCase());
      });

      if (!this.mostrarNoAdquiridos) {
        filtered = filtered.filter(cromo => cromo.adquirido);
      }

      if (this.filtroCriterio === 'valor') {
        filtered.sort((a, b) => a.valor - b.valor);
      } else if (this.filtroCriterio === 'rareza') {
        filtered.sort((a, b) => a.rareza.localeCompare(b.rareza));
      } else if (this.filtroCriterio === 'nombre') {
        filtered.sort((a, b) => a.nombre.localeCompare(b.nombre));
      }

      return filtered;
    }
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
    deleteCromoById(id, returnRoute) {
      axios.delete(`/api/cromos/${id}`, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.getAllCromos(); // Actualiza la lista de cromos
          this.$router.push(returnRoute); // Navega de vuelta a la ruta anterior
        })
        .catch(error => {
          console.error(error);
        });
    },
    confirmDeleteCromo(id) {
      this.cromoIdToDelete = id;
    },
    closeModal() {
      this.cromoIdToDelete = null;
    },
    selectCromo(cromo) {
      this.cromo = { ...cromo };
    },
    markAsNotAcquired(cromo) {
      cromo.adquirido = false; // Marcar el cromo como no adquirido
      this.updateCromo(cromo); // Actualizar el cromo en el servidor
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

.special-input {
  background-color: #f8f9fa;
  border: 1px solid #ced4da;
  border-radius: 5px;
  padding: 10px;
}

.special-input .input-group-text {
  background-color: #e9ecef;
  border: none;
}

.special-input .form-control {
  border: none;
  box-shadow: none;
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

.bg-gray {
  background-color: #f0f0f0;
}
</style>