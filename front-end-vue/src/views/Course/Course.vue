<template>
  <div class="course">
    <Header />
    <h1>courses</h1>

    <div class="container-fluid">
      <div class="row">
        <div class="col-md-3 col-lg-2">
          <Sidebar />
        </div>
        <section class="vh-180">
          <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 50px;">
            <h1>All Courses</h1>


            <ul>
              <div class="item" v-for="course in courses" :key="course.id"><br />
                <div class="col-md-4">
                  <div class="card">
                   
                    <div class="card-body">
                      <a class="singlecourse" :href="'/course/' + course.id">

                        <!-- <span class="id"><b>Course Id:</b> {{ course.id }}</span><br /> -->
                        <h5 class="card-title"><b> Course Name:</b> {{ course.coursename }}</h5>
                        <h7 class="card-text">  {{ course.courseinfo }}</h7>
                        <p class="card-text"> <b>Course Language:</b> {{ course.courselanguage }}</p>
                        <p class="card-text"> <b>Course Fee:</b> {{ course.coursefees }}</p>
                      </a>
                    </div>
                  </div>
                </div>
              </div><br /><br />
            </ul>
          </div>
        </section>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import Header from '../Header.vue'
import Sidebar from '../Sidebar.vue'
import Footer from '../Footer.vue'

export default {
  name: "Course",
  data() {
    return {
      courses: [],
    };
  },
  methods: {
    fetchCourses() {
      fetch(`http://localhost:8081/api/courses`)
        .then((response) => response.json())
        .then((data) => {
          this.courses = data;
          console.log(this.courses); // check if data is being correctly fetched
        })
        .catch((err) => console.log(err.message));
    },
  },
  mounted() {
    this.fetchCourses();
    console.log("mounted");
  },
  components: {
    Header,
    Sidebar,
    Footer
  },
};
</script>

<style scoped>
h1 {
  font-size: 20px;
}

a {
  text-decoration: none;
  color: black;
  font-size: 15px;
}

a:hover {
  text-decoration:underline;
  color:cadetblue;
}

/* .item {
  background:lightslategray;
  margin-bottom: 5px;
  padding: 3px 5px;
  border-radius: 10px;
} */
/* #post-list {
  background: black;
  box-shadow: 1px 2px 3px rgba(0, 0, 0, 0.2);
  margin-bottom: 30px;
  padding: 10px 20px;
  margin: auto;
  width: 50%;
  border-radius: 20px;
} */

/* #post-list ul {
  padding: 0;
}

#post-list li {
  display: inline-block;
  margin-right: 10px;
  margin-top: 10px;
  padding: 20px;
  background: black;
} */
</style>