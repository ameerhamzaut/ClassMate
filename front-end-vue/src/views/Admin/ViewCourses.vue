<template>
    <div class="addcourse">
    </div>

    <AdminHeader />

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3 col-lg-2">
                <AdminSidebar />
            </div>
            <br />

            <table>
                <thead>
                    <tr>
                        <!-- <th>Course Id</th> -->
                        <th>Course Name</th>
                        <th>Description</th>
                        <th>Language</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
                    <tr v-for="course in courses" :key="course.id">
                        <!-- <td>{{ course.id }}</td> -->
                        <td>
                            <!-- <a class="singleproduct" :href="'/addcourse/'"> -->
                            <!-- {{ course.coursename }} -->
                            <input name="coursename" type="text" id="coursename" required v-model="course.coursename" />

                            <!-- </a> -->
                        </td>

                        <td> <!-- {{ course.courseinfo}} -->
                            <textarea input name="courseinfo" type="text" id="courseinfo" required
                                v-model="course.courseinfo"></textarea>
                        </td>
                        <td>
                            <!-- {{ course.courselanguage }} -->
                            <input name="courselanguage" type="text" id="courselanguage" required
                                v-model="course.courselanguage" />
                        </td>
                        <td>
                            <!-- {{ course.coursefees }} -->
                            <input name="coursefees" type="text" id="coursefees" required v-model="course.coursefees" />
                        </td>
                        <td>
                            <b-button @click="updateCourse(course)" type="submit" class="btn btn-success "
                                variant="primary">Update</b-button> |
                            <b-button @click="deleteCourse(course.id)" type="submit" class="btn btn-danger "
                                variant="primary">Delete</b-button>

                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>

    <br />
    <br /> <br />
    <AdminFooter />
</template>


<script>
import AdminHeader from '../Admin/components/AdminHeader.vue'
import AdminSidebar from '../Admin/components/AdminSidebar.vue'
import AdminFooter from '../Admin/components/AdminFooter.vue'

export default {
    name: "Course",
    data() {
        return {
            courses: [],
            course: {
                userId: 2,
                coursename: "",
                courseinfo: "",
                courselanguage: "",
                coursefees: 0.0
            }
            ,
            errorMessage: '',
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
        deleteCourse(courseId) {
            if (confirm(`Are you sure you want to delete the course ${courseId}?`)) {
                fetch(`http://localhost:8081/api/courses/${courseId}`, {
                    method: 'DELETE',
                })
                    .then(response => {
                        if (response.ok) {
                            // Remove the deleted course from the courses list
                            this.courses = this.courses.filter(course => course.id !== courseId);
                            this.successMessage = `Course with id ${courseId} deleted successfully`;
                            console.log(`Course with id ${courseId} deleted successfully`);
                            
                        } else {
                            throw new Error(`Failed to delete course with id ${courseId}`);
                        }
                        setTimeout(() => {
                                this.successMessage = "";
                            }, 10000);
                    })
                    .catch(error => {
                        console.error(error.message);
                        this.errorMessage = `Failed to delete course with id ${courseId}`;
                    });
            }
        },
        updateCourse(course) {
            console.log(course);

            var data = {
                id: course.id,
                userId: 2,
                coursename: course.coursename,
                courseinfo: course.courseinfo,
                courselanguage: course.courselanguage,
                coursefees: course.coursefees,
            };

            // using Fetch - put method - updates a specific product based on the passed id and the specified body
            fetch(`http://localhost:8081/api/courses/${course.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            })
                .then((response) => {
                    console.log(response.data);
                    console.log(response);
                    this.successMessage = 'Course Updated Successfully.'
                    this.courses = this.courses.filter(course => course.id == course.id);
                    //this.$router.push("/apost/" + this.product.id);
                    // We are using the router instance of this element to navigate to a different URL location
                    this.$router.push("/addcourse");
                })
                .catch((e) => {
                    console.log(e);
                });
        },
    },
    mounted() {
        this.fetchCourses();
        console.log("mounted");
        this.fetchCourses();
    },
    components: {
        AdminHeader,
        AdminSidebar,
        AdminFooter
    },
};
</script>

<style scoped>
a {
    text-decoration: none;
    color: black;
    font-size: 15px;
}

a:hover {
    text-decoration: underline;
    color: cadetblue;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th,
td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #f2f2f2;
    font-weight: bold;
    color: #333;
}

textarea {
    width: 100%;
    height: 100px;
    padding: 8px;
    font-size: 14px;
    line-height: 1.4;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}
</style>