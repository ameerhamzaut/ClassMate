import { createRouter, createWebHistory } from 'vue-router'
import About from '../views/About.vue'
import Contact from '../views/Contact.vue'
import SignUp from '../views/SignUp.vue'
import Login from '../views/Login.vue'
import MainPage from '../views/MainPage.vue'
import Course from '../views/Course/Course.vue'
import TimeTable from '../views/Timetable/TimeTable.vue'
import Grades from '../views/Grade/Grades.vue'
import Header from '../views/Header.vue'
import Sidebar from '../views/Sidebar.vue'
import Admin from '../views/Admin/Admin.vue'
import AdminHeader from '../views/Admin/components/AdminHeader.vue'
import AdminSidebar from '../views/Admin/components/AdminSidebar.vue'
import AddTimetable from '../views/Admin/AddTimetable.vue'
import AddCourse from '../views/Admin/AddCourse.vue'
import ViewCourses from '../views/Admin/ViewCourses.vue'
import Profile from '../views/User/Profile.vue'
import Coursedetails from '../views/Course/Coursedetails.vue'
import AddGrades from '../views/Admin/AddGrades.vue'
import AdminFooter from '../views/Admin/components/AdminFooter.vue'
import Payment from '../views/Payment/Payment.vue'







const routes = [
  
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/mainpage',
    name: 'MainPage',
    component: MainPage
  },

  {
    path: '/course',
    name: 'Course',
    component: Course
  },
  {
    path: '/timetable',
    name: 'TimeTable',
    component: TimeTable
  },
  {
    path: '/grades',
    name: 'Grades',
    component: Grades
  },
  {
    path: '/header',
    name: 'Header',
    component: Header
  },
  {
    path: '/sidebar',
    name: 'Sidebar',
    component: Sidebar
  },

  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/adminheader',
    name: 'AdminHeader',
    component: AdminHeader
  },
  {
    path: '/adminfooter',
    name: 'AdminFooter',
    component: AdminFooter
  },
  {
    path: '/adminsidebar',
    name: 'AdminSidebar',
    component: AdminSidebar
  },
  {
    path: '/addtimetable',
    name: 'AddTimetable',
    component: AddTimetable
  },
  {
    path: '/addcourse',
    name: 'AddCourse',
    component: AddCourse
  },
  {
    path: '/course/:id',
    name: 'Coursedetails',
    component: Coursedetails
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/addgrades',
    name: 'AddGrades',
    component: AddGrades
  },
  {
    path: '/viewCourses',
    name: 'ViewCourses',
    component: ViewCourses
  },
  {
    path: '/payment',
    name: 'Payment',
    component: Payment
  }



]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router
