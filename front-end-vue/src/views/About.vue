<template>
  
    <nav v-if="showNavbar" class="navbar navbar-expand-lg  fixed-top">
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
      <div class="ml-auto d-flex float-right ">
        <li class="nav-item btn" id="signup-button">
          <router-link class="nav-link" to="/signup">Sign Up</router-link>
        </li>
        <li class="nav-item btn" id="login-button">
          <router-link class="nav-link" to="/login">Log In</router-link>
        </li>
      </div>
    </nav>

  <div class="container" style="margin-top: 80px">
    <div class="row">
      <div class="jumbotron" style="background-color:#eee;border-radius: 10px;">
        <h1 class="display-4">ClassMate</h1>
        <p class="lead">Classmate is an innovative educational platform designed with a distributed
          architecture for scalability and reliability. It comprises several deployable compone
          nts, such as Course, Grades, Payment, and Notification. The platform provides a user-fr
          iendly interface for managing academic activities, including course registration, grade
          tracking, and payment processing. With its advanced features and robust infrastructure,
          Classmate is an essential tool for educational institutions seeking to enhance their
          improve the learning experience for students. </p>
        </div>
    </div>
  </div>
  <div class="container-fluid" style="margin-top: 100px;">
    <section>

      <div class="row">
        <h1 style="text-align: center;color: white;">Our Team</h1>
        <hr style="width: 100px;margin: auto;width: 50%;border-width: 3px;color: white;" />
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <img class="card-img-top" src="@/assets/ojus.jpg" alt="Friend 1" style="height: 300px;">
            <div class="card-body">
              <h4 class="card-title">Ojus Tudavekar</h4>
              <h6 class="card-text">Software Engineer</h6>
              <h7 class="card-text">University Of Tartu</h7>
            </div>
            <div class="card-footer">
              <small class="text-muted">&copy; 2023 ClasMate.</small>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img class="card-img-top" src="@/assets/rasinthe.png" alt="Friend 2" style="height: 300px;">
            <div class="card-body">
              <h5 class="card-title">Rasinthe Marasinghe</h5>
              <h6 class="card-text">Software Engineer</h6>
              <h7 class="card-text">University Of Tartu</h7>
            </div>
            <div class="card-footer">
              <small class="text-muted">&copy; 2023 ClasMate.</small>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img class="card-img-top" src="@/assets/deepika.png" alt="Friend 3" style="height: 300px;">
            <div class="card-body">
              <h5 class="card-title">Deepika Sambrekar</h5>
              <h6 class="card-text">Software Engineer</h6>
              <h7 class="card-text">University Of Tartu</h7>
            </div>
            <div class="card-footer">
              <small class="text-muted">&copy; 2023 ClasMate.</small>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img class="card-img-top" src="@/assets/ameer.jpg" alt="Friend 4" style="height: 300px;">
            <div class="card-body">
              <h5 class="card-title">Ameer hamza</h5>
              <h6 class="card-text">Software Engineer</h6>
              <h7 class="card-text">University Of Tartu</h7>
            </div>
            <div class="card-footer">
              <small class="text-muted">&copy; 2023 ClasMate.</small>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  <Footer />
</template>


<script>
import Footer from '../views/Footer.vue';
export default {
  name: 'about',
  components: {
    Footer
  },
  computed: {
    showNavbar() {
      return this.$route.path === '/about'
    }
  }
}
</script>


<style>
body {
  margin: 0;
  padding: 0;
  overflow: auto;
  
}
#signup-button {
  background-color: transparent;
  background-color: #ffffff;
  /* Replace with your desired background color */
  color: #000000;
  /* Replace with your desired text color */
  padding: 5px 15px;
  /* Replace with your desired padding */
}

#signup-button:hover {
  background-color: #000000;
  /* Replace with your desired hover background color */
  color: #ffffff;
  /* Replace with your desired hover text color */
}
#login-button {
  background-color: #ffffff;
  /* Replace with your desired background color */
  color: #000000;
  /* Replace with your desired text color */
  padding: 5px 15px;
  /* Replace with your desired padding */
}
#login-button:hover {
  background-color: #000000;
  /* Replace with your desired hover background color */
  color: #ffffff;
  /* Replace with your desired hover text color */
}
.navbar .nav-item .nav1 {
  color: white;
}
</style>