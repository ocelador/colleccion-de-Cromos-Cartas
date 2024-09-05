import { createRouter, createWebHistory } from "vue-router";
import Inicio from "../views/Inicio.vue";
import Collecciones from "../views/Collecciones.vue";
import Cromos from "../views/Cromos.vue";
import Albumes from "../views/Albumes.vue";
import ConfirmModal from "../components/ConfirmModal.vue"; // Importa el modal

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
  {
    path: "/albumes/:albumId/cromos",
    name: "Cromos",
    component: Cromos,
    props: true,
  },
  {
    path: "/albumes",
    name: "Albumes",
    component: Albumes,
  },
  {
    path: "/confirm-modal",
    name: "ConfirmModal",
    component: ConfirmModal,
    props: route => ({
      visible: true,
      title: route.query.title,
      message: route.query.message,
      confirmRoute: route.query.confirmRoute,
      cancelRoute: route.query.cancelRoute
    })
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;