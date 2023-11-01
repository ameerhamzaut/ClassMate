<template>
  <div class="grades">

  </div>

  <Header />
  <h1>courses</h1>

  <div class="container-fluid">
    <div class="row">
      <div class="col-md-3 col-lg-2">
        <Sidebar />
      </div>
      <section class="vh-200" >
      <div class="vh-200 col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 10px;">
        <h1>Grades</h1>
      </div>
      </section>
    </div>
  </div>

  <Footer />
</template>

<script>

import Header from '../Header.vue'
import Sidebar from '../Sidebar.vue'

import Footer from '../Footer.vue'
export default {


  name: 'grades',
  components: {
    Header,
    Sidebar,
    Footer
  }
}
</script>
