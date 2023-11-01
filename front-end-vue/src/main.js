import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import router from './router'
import 'bootstrap/dist/js/bootstrap.js';



// Make BootstrapVue available throughout your project


const app = createApp(App)
app.use(router)
app.mount('#app')

