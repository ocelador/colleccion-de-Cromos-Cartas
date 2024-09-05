<template>
  <div v-if="visible" class="modal-overlay">
    <div class="modal-container">
      <div class="modal-header">
        <h3>{{ title }}</h3>
      </div>
      <div class="modal-body">
        <p>{{ message }}</p>
      </div>
      <div class="modal-footer">
        <button @click="confirm" class="btn btn-primary">Confirmar</button>
        <button @click="cancel" class="btn btn-secondary">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    title: {
      type: String,
      required: true
    },
    message: {
      type: String,
      required: true
    },
    confirmRoute: {
      type: String,
      required: true
    },
    cancelRoute: {
      type: String,
      required: true
    },
    returnRoute: {
      type: String,
      required: true
    }
  },
  methods: {
    confirm() {
      axios.delete(this.confirmRoute, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.$emit('update-cromos'); // Emitir evento para actualizar la lista de cromos
          this.$emit('close-modal'); // Emitir evento para cerrar el modal
          this.$router.push(this.returnRoute); // Navegar de vuelta a la ruta anterior
        })
        .catch(error => {
          console.error(error);
          this.$emit('close-modal'); // Emitir evento para cerrar el modal incluso en caso de error
          this.$router.push(this.returnRoute); // Navegar de vuelta a la ruta anterior
        });
    },
    cancel() {
      this.$emit('close-modal'); // Emitir evento para cerrar el modal
      this.$router.push(this.returnRoute); // Navegar de vuelta a la ruta anterior
    }
  }
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-container {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  width: 100%;
}

.modal-header {
  margin-bottom: 20px;
}

.modal-body {
  margin-bottom: 20px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>