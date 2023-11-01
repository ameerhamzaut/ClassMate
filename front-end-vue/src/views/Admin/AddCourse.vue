<template>
  <div class="addcourse">
  </div>

  <AdminHeader />

  <div class="container-fluid">
    <div class="row">
      <div class="col-md-3 col-lg-2">
        <AdminSidebar />
      </div>

      <section class="vh-100" >
        <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 80px;">

          <div class="container-fkuid">
            <div class="row justify-content-center">
              <div class="col-lg-6">
                <div class="card mx-auto">
                  <div class="card-header text-center">
                    <h4>Add a new course</h4>
                  </div>
                  <div class="card-body">
                    <form @submit="addCourse">
                      <div class="form-group">
                        <label for="coursename">Course name:</label>
                        <input class="form-control" name="coursename" type="text" id="coursename" required
                          v-model="course.coursename" />
                      </div>
                      <div class="form-group">
                        <label for="courseinfo">Course info:</label>
                        <input class="form-control" name="courseinfo" type="text" id="courseinfo" required
                          v-model="course.courseinfo" />
                      </div>
                      <div class="form-group">
                        <label for="courselanguage">Course language:</label>
                        <input class="form-control" name="courselanguage" type="text" id="courselanguage" required
                          v-model="course.courselanguage" />
                      </div>
                      <div class="form-group">
                        <label for="coursefees">Course fees:</label>
                        <input class="form-control" name="coursefees" type="float" id="coursefees" required
                          v-model="course.coursefees" />
                      </div>
                      <br />
                      <router-link @click="addCourse" class="btn btn-primary d-block mx-auto" style="width: 30%;"
                        to="/admin">Add Course</router-link>
                    </form>
                  </div>
                  <div class="card-footer text-muted text-center">
                    &copy; ClassMate
                  </div>
                </div>
              </div>
            </div>
          </div>




        </div>
      </section>

      <section class="vh-200" style="background-color: #eee; ">
        <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 80px;">
          <h1>Add Course</h1>
          <form @submit="addCourse" class="container">
            <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>
            <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>

            <label for="coursename">Course name: </label>
            <input class="form-control" name="coursename" type="text" id="coursename" required
              v-model="course.coursename" />
            <label for="courseinfo">Course info: </label>
            <input class="form-control" name="courseinfo" type="text" id="courseinfo" required
              v-model="course.courseinfo" />
            <label for="courselanguage">course language: </label>
            <input class="form-control" name="courselanguage" type="text" id="courselanguage" required
              v-model="course.courselanguage" />
            <label for="coursefees">course fees: </label>
            <input class="form-control" name="coursefees" type="number" id="coursefees" required
              v-model="course.coursefees" />
            <br />

            <b-button @click="addCourse"  type="submit" class="btn btn-primary text-center" variant="primary">Add Course</b-button>

          </form>
          <ViewCourses />
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
import ViewCourses from '@/views/Admin/ViewCourses.vue'

export default {
  name: 'addcourse',
  data() {
    return {
      course: {
        userId: 2,
        coursename: "",
        courseinfo: "",
        courselanguage: "",
        coursefees: 0.0
      }
      ,
      errorMessage: ''
    };
  },
  components: {
    AdminHeader,
    AdminSidebar,
    AdminFooter,
    ViewCourses,

  },
  methods: {
    addCourse() {
      if (!this.course.coursename || !this.course.courseinfo || !this.course.courselanguage || !this.course.coursefees) {
        this.errorMessage = 'Please fill in all required fields.'
        return
      }
      var data = {
        userId: 2,
        coursename: this.course.coursename,
        courseinfo: this.course.courseinfo,
        courselanguage: this.course.courselanguage,
        coursefees: this.course.coursefees,
      };

      // using Fetch - post method - send an HTTP post request to the specified URI with the defined body
      fetch("http://localhost:8081/api/courses", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
      .then((response) => {
        console.log(response.data);
        console.log(response);
        this.successMessage = 'Course Added Successfully.'
        this.courses = this.courses.filter(course => course.id == course.id);
        // redirect to /allposts view
        this.$router.push("/addcourse");
      })
        .catch((error) => {
          console.log(error);
          console.log("error");
          this.successMessage = 'Course Added Successfully.'
        });
    }
  }
};
</script>
    

