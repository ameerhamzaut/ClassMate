<template>
    
  
    <AdminHeader />

  
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-3 col-lg-2">
          <AdminSidebar />
        </div>  <section class="vh-100" >
        <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 80px;">
          <h1>Main Panel</h1>
        </div>
      </section>
      </div>
    </div>
  
    <AdminFooter />
  </template>
  
  <script>
  
  import AdminHeader from '../Admin/components/AdminHeader.vue'
  import AdminSidebar from '../Admin/components/AdminSidebar.vue'
  import AdminFooter from '../Admin/components/AdminFooter.vue'
  
  
  
  export default {
  
  
    name: 'admin',
    components: {
      AdminHeader,
      AdminSidebar,
      AdminFooter
     
    }
  }
  </script>
  