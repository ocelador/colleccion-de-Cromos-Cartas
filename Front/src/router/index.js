import { createRouter, createWebHistory } from "vue-router";
import Inicio from "../views/Inicio.vue";
import Collecciones from "../views/Collecciones.vue";

const routes = [
  {
    path: "/",
    name: "Inicio",
    component: Inicio,
  },
  {
    path: "/collecciones",
    name: "Collecciones",
    component: Collecciones,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;