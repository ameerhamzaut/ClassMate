<template>
  <div class="addtimetable">

  </div>

  <AdminHeader />


  <div class="container-fluid">
    <div class="row">
      <div class="col-md-3 col-lg-2">
        <AdminSidebar />
      </div>
      <section class="vh-100" >
        <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 80px;">





          <div class="container-fluid">
            <div class="row justify-content-center">
              <div class="col-lg-6">
                <div class="card mx-auto">
                  <div class="card-header text-center">
                    <h4>Add a new Timetable</h4>
                  </div>
                  <div class="card-body">
                    <form @submit="addtimetable">
                      <div class="form-group">
                        <label for="day">Day:</label>
                        <input class="form-control" name="day" type="date" required v-model="timetable.day" />
                      </div>
                      <div class="form-group">
                        <label for="start_time">Start Time:</label>
                        <input class="form-control" name="start_time" type="time" id="start_time" required
                          v-model="timetable.start_time" />
                      </div>
                      <div class="form-group">
                        <label for="end_time">End Time:</label>
                        <input class="form-control" name="end_time" type="time" id="end_time" required
                          v-model="timetable.end_time" />
                      </div>
                      <div class="form-group">
                        <label for="groupCat">Group Cat:</label>
                        <input class="form-control" name="groupCat" type="text" id="groupCat" required
                          v-model="timetable.groupCat" />
                      </div>
                      <br />
                      <router-link @click="addtimetable" class="btn btn-primary d-block mx-auto" style="width: 30%;"
                        to="/">Add Timetable</router-link>
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
    </div>
  </div>

  <AdminFooter />
</template>
  
<script>

import AdminHeader from '../Admin/components/AdminHeader.vue'
import AdminSidebar from '../Admin/components/AdminSidebar.vue'
import AdminFooter from '../Admin/components/AdminFooter.vue'


export default {


  name: 'AddTimetable',
  data() {
    return {
      timetable: {
        courseId: 3,
        day: "",
        start_time: "",
        end_time: "",
        groupCat: "",

      },
    };
  },
  components: {
    AdminHeader,
    AdminSidebar,
    AdminFooter

  },
  methods: {
    addtimetable() {
      var data = {
        courseId: 3,
        day: this.timetable.day,
        start_time: this.timetable.start_time,
        end_time: this.timetable.end_time,
        groupCat: this.timetable.groupCat

      };
      // using Fetch - post method - send an HTTP post request to the specified URI with the defined body
      fetch("http://localhost:8080/api/timetables", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          console.log(response.data);
          // redirect to /allposts view
          this.$router.push("/admin");
        })
        .catch((e) => {
          console.log(e);
          console.log("error");
        });
    },
  },
};
</script>
  