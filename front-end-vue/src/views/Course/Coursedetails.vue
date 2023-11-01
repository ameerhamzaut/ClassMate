<template>
  <div class="coursedetail">
    <Header />
  </div>
  <div class="container-fluid" >
    <div class="row">
      <div class="col-md-3 col-lg-2">
        <Sidebar />
      </div>

      <div class="card">
        <div class="card-content">
          <h1 class="course-title">{{ course.coursename }}</h1>
          <p class="course-description">{{ course.courseinfo }}</p>
          <div class="course-details">
            <p><strong>Course Language:</strong> {{ course.courselanguage }}</p>
            <p><strong>Course Fee:</strong> {{ course.coursefees }}</p>
          </div>
          <div class="text-center">
            <router-link @click="enrolltoACourse" class="btn btn-primary" to="/payment">Enroll</router-link>
            <!--change the link once you create the enrollment page -->
          </div>
        </div>
      </div>
    </div>
  </div>

  <Footer />
</template>

<script>
import Header from '../Header.vue'
import Sidebar from '../Sidebar.vue'
import Footer from '../Footer.vue'
//import axios from 'axios';
export default {
  name: "Coursedetails",
  data() {
    return {
      course: {
        // userId: 2,
        coursename: "",
        courseinfo: "",
        courselanguage: "",
        coursefees: ""
      },
    };
  },
  methods: {


   enrolltoACourse() {
    var data = {
        userId: 2,
        courseId: 1,
      };
      if (confirm(`Enroll Successfully`)) {
        

      fetch("http://localhost:8083/api/users/enroll", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          console.log(response.data);
          // redirect to /allposts view
          this.$router.push("/payments");
        })
        .catch((e) => {
          console.log(e);
          console.log("error");
        });
      }
    },


    fetchCourse(id) {
      //fetch one course with the specified  id (id)
      fetch(`http://localhost:8081/api/courses/${id}`)
        .then((response) => response.json())
        .then((data) => (this.course = data))
        .catch((err) => console.log(err.message));
    },
  },
  
  mounted() {
    this.fetchCourse(this.$route.params.id);
  },

  
  components: {
    Header,
    Sidebar,
    Footer
  },
  
};
</script>

<style scoped>
.card {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  /* Set the dimensions and styling */
  width: 700px;
  height: 400px;
  background-color: white;
  border: 1px solid black;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
}

/* Style the card content */
.card-content {
  padding: 20px;
}
</style>
  
  