import { createRouter, createWebHistory } from 'vue-router';
import Collecciones from '../views/Collecciones.vue';
import Cromos from '../views/Cromos.vue';
import Albumes from '../views/Albumes.vue';

const routes = [
  {
    path: '/',
    name: 'Collecciones',
    component: Collecciones
  },
  {
    path: '/albumes',
    name: 'Albumes',
    component: Albumes
  },
  {
    path: '/albumes/:albumId/cromos',
    name: 'Cromos',
    component: Cromos,
    props: true
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;