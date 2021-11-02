# CodeFellowShip

## Run the App:
- change the application.properties file and add you database info 

- ./gradlew bootRun

## Localhost
localhost:8080













## in this project:
 ### (lab16+17);
  we created a codefellow website that secured using spring framework. the website has the following features:
- ability to create account (/signup route)

- authontecation is necessary before access the other web page features, so if you have account you can register at (/login) route

- the data will be saved at the database for each user.

- the user can find the user data at the /profile

- the last feature is the add post (/addpost) route, on which the user can find all posts at the profile page.

- finally to clone the project, check the [repo](https://github.com/Maiada-Ibrahim/codefellowship)


- after you clone this repo make sure to edit application.properties.
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/your database name
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create



# Routes:
- ("/"): to get home page
- ("/login"): to login
- ("/signup"): to signup
- ("/profile"): to see your profile and posts.




## (lab18+19); we build on the work made at the last labs on which two pages were added:
- (/suggest) : this  will list all of the users so the user can add any one at the list to his following list
 to create this feature many to many relation were created in the Application users (following/ follower)

-  the (/feed) path list all the people in the following list so that you can see there post beside that the user can access to there profile.

- the application is checked to be immune against sql and html injection.

- Thymeleaf fragment were used to improve the performance plus insure a unique header and footer for all pages.
- three pages (/signup,/ login,/home)  were tested using MockMvc, and the result is pass.