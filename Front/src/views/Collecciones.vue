<template>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="collecciones-container text-center p-4 rounded shadow">
            <h1>Collecciones</h1>
            <div class="d-flex justify-content-around flex-wrap">
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" /> 
                <p></p>
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
                <img src="@/assets/liga.png" alt="Colleccion" class="imagen img-fluid m-2" />
            </div>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
.collecciones-container {
    text-align: center;
    background-color: #f8f9fa;
}

.imagen {
    margin: 15px;
    width: 250px; 
    height: auto; 
}

.imagen:hover {
    transform: scale(1.15); 
}

@media (max-width: 768px) {
    .collecciones-container {
        padding-bottom: 15vh;
    }

    .collecciones-container h1 {
        font-size: 20px;
    }

    .imagen {
        width: 100px; 
    }
}
</style>