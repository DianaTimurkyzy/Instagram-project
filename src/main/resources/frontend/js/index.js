import {createApp} from 'vue';
import {createRouter, createWebHistory} from 'vue-router'
import App from "../template/App.vue";
import Archive from "../template/Archive.vue";
import LoginPage from "../template/login-page.vue";


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', component: App},
        {path: '/archive', component: Archive},
        {path: '/:username', component: App, name: 'profile' },
        {path: '/login', component: LoginPage}
    ]
});

const app = createApp({});
app.use(router);
app.mount('#app');
