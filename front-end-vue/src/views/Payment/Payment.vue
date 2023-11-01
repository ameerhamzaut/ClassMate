<template>
    <div class="payment">
        <Header />

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 col-lg-2">
                    <Sidebar />
                </div>
                <section class="" style="margin-top: 30px;">
                    <div class="col-md-9 col-lg-10 offset-md-3 offset-lg-2" style="margin-top: 50px;">
                        <h1>Payment</h1>

                        <div class="card">
                            <div class="card-content">
                                <div class="course-details">
                                    <p><strong>Course Language:</strong> {{ course.courselanguage }}</p>
                                    <p><strong>Course Fee:</strong> {{ course.coursefees }}</p>
                                </div>
                            </div>
                        </div>



                        <form class="container" style="background-color: #eee;margin-top: 30px;width: 70%;border-radius: 10px;">
                            <h2 class="text-center">Payment form</h2>
                            <div class="form-group">
                                <label class="form-label" for="">Account NO: </label>
                                <input name="" type="float" id="" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">CVV NO: </label>
                                <input name="" type="float" id="" class="form-control" required />
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">Expiry date: </label>
                                <input name="" type="date" id="" class="form-control" required />
                            </div>

                            <div class="form-group">
                                <b-button class="btn btn-primary" style="padding: 10px;width: 100px;margin-top: 20px;margin-left: 40%;margin-bottom: 10px;">Pay</b-button>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>

        
    </div>
</template>
  
<script>
import Header from '../Header.vue'
import Sidebar from '../Sidebar.vue'


export default {
    name: "Payment",
    data() {
        return {
            course: {
                courselanguage: "",
                coursefees: ""
            },
        };
    },
    methods: {
        fetchCourse(id) {
            // Fetch one course with the specified id
            fetch(`http://localhost:8081/api/courses/${id}`)
                .then((response) => response.json())
                .then((data) => {
                    this.course.courselanguage = data.courselanguage;
                    this.course.coursefees = data.coursefees;
                })
                .catch((err) => console.log(err.message));
        },
    },Pay
    mounted() {
        this.fetchCourse(this.$route.params.id);
    },
    components: {
        Header,
        Sidebar,
        
    },
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  overflow: auto;
  
}
</style>
  