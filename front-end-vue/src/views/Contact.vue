
<template>
  <div class="container-fluid">
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
  </div>


  <div class="container-fluid px-5 my-5">
  <div class="row justify-content-center">
    <div class="col-xl-10">
      <div class="card border-0 rounded-3 shadow-lg overflow-hidden">
        <div class="card-body p-0">
          <div class="row g-0">
            <div class="col-sm-6 d-none d-sm-block bg-image"></div>
            <div class="col-sm-6 p-4">
              <div class="text-center">
                <div class="h3 fw-light">Contact Form</div>
                <p class="mb-4 text-muted">Split layout contact form</p>
              </div>

              <!-- * * * * * * * * * * * * * *
          // * * SB Forms Contact Form * *
          // * * * * * * * * * * * * * * *

          // This form is pre-integrated with SB Forms.
          // To make this form functional, sign up at
          // https://startbootstrap.com/solution/contact-forms
          // to get an API token!
          -->

              <form id="contactForm" data-sb-form-api-token="API_TOKEN">

                <!-- Name Input -->
                <div class="form-floating mb-3">
                  <input class="form-control" id="name" type="text" placeholder="Name" data-sb-validations="required" />
                  <label for="name">Name</label>
                  <div class="invalid-feedback" data-sb-feedback="name:required">Name is required.</div>
                </div>

                <!-- Email Input -->
                <div class="form-floating mb-3">
                  <input class="form-control" id="emailAddress" type="email" placeholder="Email Address" data-sb-validations="required,email" />
                  <label for="emailAddress">Email Address</label>
                  <div class="invalid-feedback" data-sb-feedback="emailAddress:required">Email Address is required.</div>
                  <div class="invalid-feedback" data-sb-feedback="emailAddress:email">Email Address Email is not valid.</div>
                </div>

                <!-- Message Input -->
                <div class="form-floating mb-3">
                  <textarea class="form-control" id="message" type="text" placeholder="Message" style="height: 10rem;" data-sb-validations="required"></textarea>
                  <label for="message">Message</label>
                  <div class="invalid-feedback" data-sb-feedback="message:required">Message is required.</div>
                </div>

                <!-- Submit success message -->
                <div class="d-none" id="submitSuccessMessage">
                  <div class="text-center mb-3">
                    <div class="fw-bolder">Form submission successful!</div>
                    <p>To activate this form, sign up at</p>
                    <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                  </div>
                </div>

                <!-- Submit error message -->
                <div class="d-none" id="submitErrorMessage">
                  <div class="text-center text-danger mb-3">Error sending message!</div>
                </div>

                <!-- Submit button -->
                <div class="d-grid">
                  
                  <div class="text-center">
                    
                    <router-link  class="btn btn-primary" to="/"  id="submitButton" type="submit">Sign Up</router-link>
                  </div>
                
                </div>
              </form>
              <!-- End of contact form -->

            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>




  <Footer />
</template>
   
   
<script>

import Footer from '../views/Footer.vue';
export default {


  name: 'contact',
  components: {
    Footer
  },
  computed: {
    showNavbar() {
      return this.$route.path === '/contact'
    }
  }

}

</script>


<style>
body {
  background: #007bff;
  background: linear-gradient(to right, #0062E6, #33AEFF);
}

.bg-image {
  background-image: url('@/assets/SMS2.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
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

.map-container {
  position: fixed;
  bottom: 53px;
  left: 0;

}</style>