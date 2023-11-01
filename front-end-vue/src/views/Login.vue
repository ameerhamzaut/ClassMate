<template>
  <div>
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
        </ul>
      </div>
      <div class="ml-auto d-flex float-right">
        <li class="nav-item btn" id="signup-button">
          <router-link class="nav-link" to="/signup">Sign Up</router-link>
        </li>
        <li class="nav-item btn" id="login-button">
          <router-link class="nav-link" to="/login">Log In</router-link>
        </li>
      </div>

    </nav>

    <section class="vh-100">
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-lg-12 col-xl-11">
            <div class="card text-black" style="border-radius: 25px; margin-top: 0px;">
              <div class="card-body p-md-5">
                <div class="row justify-content-center">
                  <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Log In</p>
                    <form class="mx-1 mx-md-4">
                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="form3Example3c">Username</label>
                          <input type="text" id="name" class="form-control" required v-model="name">
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="form3Example4c">Password</label>
                          <input type="password" id="password" class="form-control" required v-model="password">

                        </div>
                      </div>

                      <div class="text-center">

                        <b-button @click="LogIn" type="submit" class="btn btn-primary addPost" variant="primary">Log
                          In</b-button>

                        <router-link class="btn btn-link" to="/">Skip to Main Page</router-link>
                      </div>
                    </form>
                  </div>


                  <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                    <!-- <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                  class="img-fluid" alt="Sample image"> -->

                    <img src="@/assets/SMS.jpg" class="img-fluid" alt="Sample image" style="border-radius: 20px;">


                  </div>
                </div>
              </div>
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
import jwt_decode from 'jwt-decode';

export default {

  computed: {
    showNavbar() {
      return this.$route.path === '/login'
    },
    // showJumbotron() {
    //   return this.$route.path === '/'
    // },


  },

  name2: 'login',
  components: {
    Footer
  },

  name: "LogIn",
  data: function () {
    return {
      name: '',
      password: '',
    }
  },
  methods: {
    async LogIn() {
      var data = {
        name: this.name,
        password: this.password
      };

      // using Fetch - post method - send an HTTP post request to the specified URI with the defined body
      await fetch("http://localhost:8083/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: 'include',
        body: JSON.stringify(data),
      })
        .then(response => response.text())
        .then(response => {
          // saving the jwt returned in the response into the token variable
          this.token = response;

          //checking if a jwt token is retuned, all jwt tokes start with "ey"
          if (this.token.startsWith("ey")) {
            // decoding the jwt and save it in the decodedToken variable
            this.decodedToken = jwt_decode(this.token);
            // saving the returned user role into the roles variable
            this.roles = this.decodedToken.roles
            // saving the token into the windows local storage 
            localStorage.setItem('jwtToken', this.token);
            console.log(localStorage.getItem('jwtToken'));
            this.$router.push("/");
          }
        })
        .catch((e) => {
          console.log(e);
          console.log("error");
        });
    },
  },

}

</script>

<style>
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



