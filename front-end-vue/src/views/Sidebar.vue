<template>
  
      
      <div class="col-md-3 col-lg-2 sidebar-offcanvas" id="sidebar" role="navigation" style="border: 1px solid black;">  
        <ul class="nav flex-column pl-1">  
       
          <li class="nav-item">  
            
            <ul class="list-unstyled flex-column pl-3 collapse" id="submenu1" aria-expanded="false">  
              <li class="nav-item"> <a class="nav-link" href=""> Sub item </a> </li>  
              <li class="nav-item"> <a class="nav-link" href=""> Sub item </a> </li>  
            </ul>  
          </li>  
          <li><router-link class="nav-link" to="/course">Courses</router-link></li>
          <li><router-link class="nav-link" to="/grades">Grades</router-link></li>
          <li><router-link class="nav-link" to="/timetable">Timetable</router-link></li>
           
        </ul>  
      </div>  
        
   
 
    
</template>


<script>

  

  
  
</script>

<style>
 
    #sidebar {
        height: 1240px;
        background-color: #F8F9FA !important;
        position: relative;
    
        
      }
</style>