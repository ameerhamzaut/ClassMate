<template>
    <div>
        <Header />
    </div>
    <Sidebar />
    <section class="vh-130">
    <div class="profile2" style=" margin-top: 30px;margin-left: 300px;">

        <div class="container col-md-10 col-lg-6;">
            <div class="row justify-content-center  ">
                <div class="col-sm-2 col-md-4 col-lg-2 col-xl-12">
                    <div class="card text-black" style="border-radius: 25px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 ">

                                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Profile</p>

                                    <form @submit="signUp" class="mx-1 mx-md-20 col-md-12">


                                        <label class="form-label" for="lastname">First Name: </label>
                                        <input name="firstname" type="text" id="firstname" class="form-control" required
                                            v-model="user.firstname" />

                                        <label class="form-label" for="lastname">Last Name: </label>
                                        <input name="lastname" type="text" id="lastname" class="form-control" required
                                            v-model="user.lastname" />

                                        <label class="form-label" for="personal_code">Personal Code: </label>
                                        <input name="personal_code" type="number" id="personal_code" class="form-control"
                                            required v-model="user.personal_code" />

                                        <label class="form-label" for="email">Email: </label>
                                        <input name="email" type="email" id="email" class="form-control" required
                                            v-model="user.email" />

                                        <label class="form-label" for="phone_no">Phone No: </label>
                                        <input name="phone_no" type="number" id="phone_no" class="form-control" required
                                            v-model="user.phone_no" />

                                        <label class="form-label" for="dob">Birthday: </label>
                                        <input name="dob" type="date" id="dob" class="form-control" required
                                            v-model="user.dob" />

                                        <label class="form-label" for="password">Password: </label>
                                        <input name="password" type="password" id="password" class="form-control" required
                                            v-model="user.password" />


                                    </form>
                                    <br/>
                                    <div>

                                        <div class="text-center">
                                            <router-link  @click="updateUser" class="btn btn-primary" to="/">Update</router-link> |
                                            <router-link  @click="deleteUser" class="btn btn-primary" to="/">Delete
                                                Account</router-link>
                                        </div>

                                       
                                        <button @click="updateProduct" class="updateProduct">Update</button>
                                        <button @click="deleteProduct" class="deleteProduct">Delete Account</button>
                                    </div>
                  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  <br/> <br/> <br/>
    </div>
</section>
    <Footer />
</template>


<script>
import Header from '../Header.vue';
import Footer from '../Footer.vue';
import Sidebar from '../Sidebar.vue';

export default {
    name: "profileD",
    data() {
        return {
            user: {
                id:353,
                firstname: "",
                lastname: "",
                personal_code: 0,
                email: "",
                phone_no: 0,
                dob: new Date("1990-01-01"),
                password: "",
            },
        };
    },
    methods: {
        fetchUser(id) {
            // fetch one product with the specified  id (id)
            id = 353,
            fetch(`http://localhost:8083/api/users/${id}`)
                .then((response) => response.json())
                .then((data) => (this.product = data))
                .catch((err) => console.log(err.message));
        },
        updateUser() {
            // using Fetch - put method - updates a specific product based on the passed id and the specified body
            fetch(`http://localhost:8083/api/users/${this.user.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(this.user),
            })
                .then((response) => {
                    console.log(response.data);
                    //this.$router.push("/apost/" + this.product.id);
                    // We are using the router instance of this element to navigate to a different URL location
                    this.$router.push("/api/profile");
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        deleteUser() {
        },
    },
    mounted() {
        // call fetchAPost() when this element mounts, and pass to it a route parameter  (id)
        // Route parameters (this.$route.params.id) are named URL segments that are used to capture the values specified at their 
        // position in the URL. The captured values are populated in the req.params object, with the name 
        // of the route parameter specified in the path as their respective keys
        this.fetchUser(this.$route.params.id);
    },

    name2: '/profile',
    components: {
        Header,
        Footer,
        Sidebar
    }
}
</script>

<style scoped>


button {
    background: rgb(8, 110, 110);
    border: 0;
    padding: 10px 20px;
    margin-top: 20px;
    color: white;
    border-radius: 20px;
}
</style>