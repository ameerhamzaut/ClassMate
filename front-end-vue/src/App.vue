
<template>
  <body>
    <div id="app" class="container-fluid" autoFocus='false'>
      <nav v-if="showNavbar" class="navbar navbar-expand-lg navbar-light  fixed-top" >

        <a class="navbar-brand" href="/" style="color: white;">ClassMate</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">

            <li class="nav-item">
              <router-link class="nav1 nav-link" to="/about">About</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav1 nav-link" to="/contact">Contact</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav1 nav-link" to="/admin">Teacher</router-link>
            </li>
          </ul>

        </div>



        <div class="ml-auto d-flex  ">
          <li class="nav-item btn " id="signup-button">
            <router-link class="nav-link" to="/signup">signup</router-link>
          </li>
          <li class="nav-item btn " id="login-button" style="margin-right: 20px;">
            <router-link class="nav-link" to="/login">Log In</router-link>
          </li>
        </div>
      </nav>

      <div v-if="showJumbotron" class="jumbotron jumbotron-fluid" autoFocus='false' style="margin-top: 200px;">
  <div class="container-fluid">
    <h1 class="jumbotron-text display-1 text-center mb-0" style="font-size: 150px;">ClassMate</h1>
    <p class="lead jumbotron-text text-center mb-0">A School Management System</p>
  </div>
</div>
    </div>








 

    <router-view />
  

  </body>
</template> 

<script>


export default {
 
  computed: {
    showNavbar() {
      return this.$route.path === '/'
    },
    showJumbotron() {
      return this.$route.path === '/'
    },


  },

}




</script>
<style>
body {
  background-image: url('@/assets/SMS2.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}
.jumbotron-text{
  color: white;
  margin-bottom: 0;
  
  
}
#signup-button {
  background-color: #ffffff; /* Replace with your desired background color */
    color: #000000; /* Replace with your desired text color */
    
    padding: 5px 15px; /* Replace with your desired padding */
  }

  #signup-button:hover {
    background-color: #000000; /* Replace with your desired hover background color */
    color: #ffffff; /* Replace with your desired hover text color */
  }
  #login-button {
    background-color: #ffffff; /* Replace with your desired background color */
    color: #000000; /* Replace with your desired text color */
  
    padding: 5px 15px; /* Replace with your desired padding */
  }

  #login-button:hover {
    background-color: #000000; /* Replace with your desired hover background color */
    color: #ffffff; /* Replace with your desired hover text color */
  }
  .navbar .nav-item .nav1{
    color: white;
  }
  



</style>