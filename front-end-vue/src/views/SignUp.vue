<template>
    <body>
    <nav v-if="showNavbar" class="navbar navbar-expand-lg fixed-top">
      <a class="navbar-brand" href="/" style="color: white;">ClassMate</a>
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
        <div class="ml-auto d-flex float-right ">
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
      <div class="row justify-content-center  align-items-center h-100 ">
        <div class="col-lg-12 col-xl-11">
          <div class="card text-black p-md-5" style="border-radius: 25px;">
            <div class="card-body p-md-5">
              <div class="row justify-content-center">
                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1 ">

                  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                  <form @submit="signUp" class="mx-1 mx-md-4">
                    <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>
                    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="name">First Name*: </label>
                        <input name="name" type="text" id="name" class="form-control" required
                          v-model="signup.name" />

                      </div>

                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="lastname">Last Name*: </label>
                        <input name="lastname" type="text" id="lastname" class="form-control" required
                          v-model="signup.lastname" />

                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="personal_code">Personal Code*: </label>
                        <input name="personal_code" type="number" id="personal_code" class="form-control" required
                          v-model="signup.personal_code" />

                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="email">Email*: </label>
                        <input name="email" type="email" id="email" class="form-control" required
                          v-model="signup.email" />

                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="phone_no">Phone No*: </label>
                        <input name="phone_no" type="number" id="phone_no" class="form-control" required
                          v-model="signup.phone_no" />

                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="dob">Birthday*: </label>
                        <input name="dob" type="date" id="dob" class="form-control" required v-model="signup.dob" />

                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="password">Password*: </label>
                        <input name="password" type="password" id="password" class="form-control" required
                          v-model="signup.password" />

                      </div>
                    </div>

                    <div class="text-center">
                      <b-button @click="signUp" type="submit" class="btn btn-primary addPost" variant="primary">Sign
                        Up</b-button>
                      <!-- <router-link @click="signUp" class="btn btn-primary" to="/">Sign Up</router-link> -->
                    </div>

                 
                  </form>

                </div>
                <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                  <img src="@/assets/SMS.jpg" class="img-fluid" alt="Sample image" style="border-radius: 20px;">


                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <Footer />
</body>
</template>


<script>
import 'bootstrap/dist/js/bootstrap.js';
import Footer from '../views/Footer.vue';
import jwt_decode from 'jwt-decode';

export default {
  name: "SignUp",
  data() {
    return {
      signup: {
        name: "",
        lastname: "",
        personal_code: 0,
        email: "",
        phone_no: 0,
        dob: new Date("1990-01-01"),
        password: "",
        roles: "STUDENT",
        token: '',
        decodedToken: '',

      },
    };
  }, Footer: '/signup',
  components: {
    Footer
  },
  methods: {
    async signUp() {
      if (!this.signup.name || !this.signup.lastname || !this.signup.personal_code || !this.signup.email || !this.signup.phone_no || !this.signup.dob || !this.signup.password) {
        this.errorMessage = 'Please fill in all required fields.';
        return
      }
      var data = {
        name: this.signup.name,
        lastname: this.signup.lastname,
        personal_code: this.signup.personal_code,
        email: this.signup.email,
        phone_no: this.signup.phone_no,
        dob: this.signup.dob,
        password: this.signup.password,
      };
      // using Fetch - post method - send an HTTP post request to the specified URI with the defined body
      await fetch("http://localhost:8083/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: 'include',
        body: JSON.stringify(data),
      })
      .then(response => response.text())
        .then(response => {
          console.log(response.data);
          console.log(response);


          this.token = response;
          if (this.token.startsWith("ey")) {
            //decoding the jwt and save it in the decodedToken variable
            this.decodedToken = jwt_decode(this.token);
            // saving the returned user role into the roles variable
            this.roles = this.decodedToken.roles
            console.log(this.decodedToken.roles);
            // saving the token into the windows local storage 
            localStorage.setItem('jwtToken', this.token);
            console.log(localStorage.getItem('jwtToken'));

            this.successMessage = 'Registered Successfully.'
            this.$router.push("/");
          }
        })
        .catch((e) => {
          console.log(e);
          this.errorMessage = 'error'
          console.log("error");
        });
    },
  },

  computed: {
    showNavbar() {
      return this.$route.path === '/signup'
    },
    //   // showJumbotron() {
    //   //   return this.$route.path === '/'
    //   // },


  },


}

</script>


<style>
body {
  margin: 0;
  padding: 0;
  overflow: auto;
}

.navbar {
  
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  position: fixed;
  top: 0;
  width: 100%;
}


.card {
  margin-top: 60px;
  position: relative;
  z-index: 2;
}
#signup-button {
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
.navbar .nav-item .nav1{
    color: white;
  }
</style>

