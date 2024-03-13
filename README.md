BASIC SETUP

Backend:
1)Open nikola-cenakovic-BE project with Eclipts or in other environment
2)Got to /nikola-cenakovic-BE/src/main/resources, open the application.properties file and check if line 
15 spring.sql.init.mode=always is commented if not comment it
3)Right click on the project and go to Run as/Maven clean and after Run as/Maven install
4)Run project right click on project and go to Run as/Spring boot app
5)Stop the priject and go again to /nikola-cenakovic-BE/src/main/resources and open application.properties
now remove comment form line 15
6)Run project again right click on project and go to Run as/Spring boot app

Frontend:
1)Open nikola-cenakovic-FE project in VSC
2)Open the terminal and type npm install
3)After type ng serve