<template>
  <body>  
    <Header />
  <div  class="container-fluid" id="main" >  
    <div class="row row-offcanvas row-offcanvas-left">  
      <Sidebar /> 
      <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2 main">  
        <button v-if = "authResult" @click="Logout" class="center">Logout</button>
        <div class="name" v-if="checkRoles('STUDENT')">
      <h1> Hello User</h1>
      <h3>  Fetched from protected endpoint "../api/auth/user": {{UserViewContent}} </h3>
    </div>
        </div>
    </div>  
  </div>  
  </body>
  <Footer />
  </template>
  
  <script>


  import Header from '../views/Header.vue'
  import Footer from '../views/Footer.vue'
  import Sidebar from '../views/Sidebar.vue'
  import auth from "../auth";
  export default {
   
  
    name: 'mainpage',
    components: {
      Header,
      Footer,
      Sidebar
    },
  data: function() {
    return {
    authResult: auth.authenticated(),
    AdminViewContent:'',
    UserViewContent:''
  }
  },
  methods: {
  Logout() {
            auth.logout();
            location.assign("/");
    },

  checkRoles: function(roles) {
      return auth.hasAnyOf(roles);
    }
  },  
  mounted() {
  if (this.checkRoles('TEACHER')){
      const token = localStorage.getItem('jwtToken');
      // Set the Authorization header with the token value
      const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}` 
      };
      fetch("http://localhost:8083/api/admin", { headers })
        .then(response => response.text())
        .then(body => {
        this.AdminViewContent = body;
        })
        .catch(err => console.log(err.message)) 
    }

  if (this.checkRoles('STUDENT')){
      const token = localStorage.getItem('jwtToken');
      // Set the Authorization header with the token value
      const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}` 
      };
      fetch("http://localhost:8083/api/user", { headers })
        .then(response => response.text())
        .then(body => {
        this.UserViewContent = body;
        })
        .catch(err => console.log(err.message)) 
    }
}
  }
  </script>


  <style type="text/css">  
    body,  
    html {  
      height: 100%; 
      overflow-x: hidden 
    }  
    body.modal-open {  
      padding-right: 0 !important;  
    }  
    #sidebar {  
      padding-left: 0;  
      position: relative
   }  
   .bg-primary {  
    background-color: #363a3e!important;  
}  
  
.bg-success {  
    background-color: #8d899d!important;  
}  
.bg-warning {  
    background-color: #262523!important;  
}  
    @media screen and (max-width: 48em) {  
      .row-offcanvas {  
        position: relative;  
        -webkit-transition: all 0.25s ease-out;  
        -moz-transition: all 0.25s ease-out;  
        transition: all 0.25s ease-out;  
      }  
      .row-offcanvas-left .sidebar-offcanvas {  
        left: -33%;  
      }  
      .row-offcanvas-left.active {  
        left: 33%;  
        margin-left: -6px;  
      }  
      .sidebar-offcanvas {  
        position: absolute;  
        top: 0;  
        width: 33%;  
        height: 100%;  
      }  
    }  
    button {    
  display: inline-block;    
  background-color: #FF9800;    
  width: 50px;    
  height: 50px;    
  text-align: center;    
  border-radius: 4px;    
  position: fixed;    
  bottom: 30px;    
  right: 30px;    
  transition: background-color .3s,     
    opacity .5s, visibility .5s;    
  opacity: 0;    
  visibility: hidden;    
  z-index: 1000;    
}    
button::after {    
  content: "\f077";    
  font-family: FontAwesome;    
  font-weight: normal;    
  font-style: normal;    
  font-size: 2em;    
  line-height: 50px;    
  color: #fff;    
}    
button:hover {    
  cursor: pointer;    
  background-color: #333;    
}    
button:active {    
  background-color: #555;    
}    
a {    
color: red;  
    display: inline-block;    
    padding: 10px 20px;    
    text-decoration: none;    
    font-size: 20px;    
  }    
  a:hover {    
  text-decoration: none;    
  color: red;    
  }    
button.show {    
  opacity: 1;    
  visibility: visible;    
}    
    @media screen and (max-width: 34em) {  
      .row-offcanvas-left .sidebar-offcanvas {  
        left: -45%;  
      }  
      .row-offcanvas-left.active {  
        left: 45%;  
        margin-left: -6px;  
      }  
      .sidebar-offcanvas {  
        width: 45%;  
      }  
    }  
    .card {  
      overflow: hidden;  
    }  
    .card-block .rotate {  
      z-index: 8;  
      float: right;  
      height: 100%;  
    }  
    .card-block .rotate i {  
      color: rgba(20, 20, 20, 0.15);  
      position: absolute;  
      left: 0;  
      left: auto;  
      right: -10px;  
      bottom: 0;  
      display: block;  
      -webkit-transform: rotate(-44deg);  
      -moz-transform: rotate(-44deg);  
      -o-transform: rotate(-44deg);  
      -ms-transform: rotate(-44deg);  
      transform: rotate(-44deg);  
    }  

    #sidebar {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: 100;
    padding-top: 60px;
    overflow-x: hidden;
    overflow-y: auto;
    background-color: #f5f5f5;
    border-right: 1px solid #ddd;
    
    
  }
  
  #main {
    margin-left: 0px; /* Adjust the margin according to the width of the sidebar */
  
  }
  
  @media (max-width: 767px) {
    #sidebar {
      padding-top: 15px;
    }
  
    #main {
      margin-left: 0;
    }
  }
  @media screen and (max-width: 48em) {  
  /* existing CSS rules */
}

/* Add new CSS rules here */
.sidebar-offcanvas .nav-link {
  color: black;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 19px 0;
  transition: color 0.3s;
  text-align: center
}

.sidebar-offcanvas .nav-link:hover {
  color: #007bff;
}


  </style>
  