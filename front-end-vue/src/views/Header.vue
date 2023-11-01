<template>
    <header>
        <!-- Navbar -->
        <div class="container-fluid" >
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top " >
            <!-- Container wrapper -->
            <div class="container-fluid">
                <!-- Toggle button -->
                <button class="navbar-toggler" type="button" data-mdb-toggle="collapse"
                    data-mdb-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>

                <!-- Collapsible wrapper -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Navbar brand -->
                    <h1 class="navbar-brand">ClassMate</h1>
                    <!-- Left links -->

                    <!-- Left links -->
                </div>
                <!-- Collapsible wrapper -->

                <!-- Right elements -->
                <div class="d-flex align-items-center">
                    <ul class="navbar-nav " id="user">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Username</a>
                        </li>

                    </ul>
                   

                    <!-- Notifications -->
                  
                    <div class="dropdown">
                        <a class=" dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-bell"></i>
                            <span class="badge rounded-pill badge-notification bg-danger">1</span>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <a class="dropdown-item" href="#">Notification</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Another news</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </li>
                        </ul>
                    </div>
                    <!-- Avatar -->
                 
                    <div class="dropdown">
                        <a class=" dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://mdbcdn.b-cdn.net/img/new/avatars/2.webp" class="rounded-circle" height="25"
                                alt="Black and White Portrait of a Man" loading="lazy" />
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuLink">
                            <li>
                                <a class="dropdown-item " href="/profile">My profile</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Settings</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- Right elements -->
            </div>
            <!-- Container wrapper -->
        </nav>
        <!-- Navbar -->
    </div>
    </header>


   <!--- <div  class="footer container-fluid footer-bottom">
        <div class="row ">
            <div class="col-6 text-center">
                <p>&copy; 2023 ClasMate. All rights reserved.</p>
            </div>
            <div class="col-6 text-center">
                <ul class="list-inline pull-right" style="color:black;">
                    <li><a href="#" style="color:black; text-decoration:none;">Terms of Service</a></li>
                    <li><a href="#" style="color:black; text-decoration:none;">Privacy Policy</a></li>
                </ul>


            </div>
        </div>
    </div>-->
</template>

<script>



</script>

<style>
#signup-button {
    margin-right: 10px;
}

.footer {
    height: 50px; /* set the height of the footer */
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: #f5f5f5;
    
}

.footer h3 {
    margin-top: 0;
    margin-bottom: 25px;
}

.footer p {
    margin-bottom: 25px;
}

.footer-bottom {
    background-color: #e9ecef;
    padding-top: 10px;
    padding-bottom: 10px;
}

.footer-bottom p {
    margin-bottom: 0;
}

.footer-bottom ul li {
    display: inline-block;
    margin-right: 10px;
    
}
.footer ul li a {
    font-size: 16px;
    margin-top: -10px

}



@media (max-width: 767px) {
    .footer-bottom ul {
        text-align: center;
    }
    .navbar {
        position: fixed;
    }
    .footer-bottom ul li {
        display: block;
        margin-bottom: 10px;
    }

    .list-inline li a {
        color: black;
        text-decoration: none;
    }

    .navbar-brand {
        font-weight: bold;
        font-size: 24px;
    }

    .nav-link {
        color: #333;
    }

    .dropdown-item {
        color: #333;
    }

    .dropdown-menu {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        border-radius: 0;
    }

    .dropdown-menu a:hover {
        background-color: #f8f9fa;
    }

    #user {
        margin-left: 200px;
    }

}
</style>