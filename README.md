# 📄Overview 
My name is Jaylen Hayden and I am an app dev student at Year Up United. This application is the backend side of the capstone project that was given to me, in order to demonstrate my understanding of API's, debugging, and working with an existing codebase.
The front end is a clothing store website, located in another repository "https://github.com/Jaylenh47-stack/ecommerce-web" and was relatively untouched by me.

![image_alt](https://github.com/Jaylenh47-stack/ecommerce-api/blob/4e46d3353900197b96be707f4a0782e8dbb2c9af/website.PNG)

A user can login, search by price, category(top,bottom,shoes) and/or color.

# 💻ApplicationStructure
![image_alt](https://github.com/Jaylenh47-stack/ecommerce-api/blob/1008355eeeaf1a5065d07ca88303bc80e4d566cf/APIDiagram.PNG)

Not all classes are included, demonstrates the flow of the application.

# 🐞Debugging
The first bug reported was that the price search was giving incorrect results.

![image_alt](https://github.com/Jaylenh47-stack/ecommerce-api/blob/4e46d3353900197b96be707f4a0782e8dbb2c9af/searchFunction.PNG)

The commented out "String sqlDemo" was my first attempt at fixing the price search bug. This did not work because for the case where a user did not specify a maximum price, max price would be null and be set the value -1.
If a user searched for a min price and left max alone (min = 50, max = -1), the SQL statement would return no matching results and the or statement would never be true since it needs both the max and min to = -1.
The working code "String sql solves this by checking for the min price condition separately from the max price condition, which allows for either the min or the max search specification to be ignored.

The second reported bug was that there was a problem with duplicate products being added, except with a slightly different description or price. From the report of the bug I knew it was in one of two places, the ProductsController or the MySqlProductsDao.

![image_alt](https://github.com/Jaylenh47-stack/ecommerce-api/blob/1008355eeeaf1a5065d07ca88303bc80e4d566cf/update.PNG)

# 🚀New Features
Added functionality so that users can search by category and color. A user who has admin priviledges can insert, update or delete a product. Tested these new features with Insomnia.

![image_alt](https://github.com/Jaylenh47-stack/ecommerce-api/blob/ca2046cf6fcb991f841af582d5a15d83f8cbbbbc/Insomnia%20tests.PNG)

# 🛠️Installation
This application can be run with a text editor such as IntelliJ and JDK17

# 😄Contributions
Much of the code was inspired from previous projects I have made in my repositories, as well as assistance from My instructor Matt Christenson's github  https://github.com/orgs/YearUpFall25-S6/repositories, 
and several of my Year Up classmates.
