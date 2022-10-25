# Degree-Planner
Name of project: An online tool to improve degree planning for BSc students.
<br>
<br>
<br>
A short description of what the project is about: Our project is to create an interactive tool to help BSC students complete the course plan, including single major or double major.
<br>
<br>
<br>
Technologies that are used to build the project (include the languages used, the libraries and their versions).We used Element UI, Vue2.x, Axios for the front-end, Java8, Maven 3.8.2, Tomcat, SpringBoot, Mybatis-puls, Shiro, Druid for the back-end, and MySQL for the database.
<br>
<br>
<br>

<h1>The 'choose-course-api' is the back-end, 'choose-course-ui' is the foront-end, and the .spl file 'student_choose_course' is the database.</h1>
<br>
<br>
<br>
<h2>Instructions on how to install and setup the project:</h2>

  <h2>Environment Construction:</h2>

</h3>Front end:</h3>


1. Download the node 12. X (nodejs--https://nodejs.org/en/)

&emsp;tutorial: (https://softauthor.com/up-and-running-with-vuejs3-project/)

&emsp;After the installation is complete, you shold configure environment variables:

&emsp;&emsp;Right-click 'the computer' -- > Advanced System Settings -- > Environment Variables.

&emsp;&emsp;In the System Variables, create a variable called 'NODE_PATH', the value is the 'node_modules folder' under the 'node_global' folder. 

&emsp;&emsp;Such as: 'E:\develop\nodejs\ node_global\ ode_modules'.

&emsp;&emsp;Then change the Path variable in the User Variable to change the default npm path to the new node_global path.


2. Go to the root directory of the front-end project and run the 'npm install' command to install the dependencies. After the installation is successful, a 'node_modules' folder will be added to the root directory of the project

3. run 'npm run serve' in the root directory to run the project
![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/10.png)

4. Enter 'localhost:8001/#/login' in the browser to access the login page. The front-end is successfully set up.

<br>
<br>

<h3>Database:</h3>

1. Install mysql (https://www.runoob.com/mysql/mysql-install.html)

2. Install navicat -- (https://www.navicat.com/en/products/navicat-premium)

The database part we use mysql language, put sql code into Navicat Premium 16 to run.

Create a database which called ‘student_choose_course’, then run the sql file.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/5.png)

<br>
<br>

<h3>Backend:</h3>

Required Environment:

1. java8 

&emsp;tutorial: (https://www.codejava.net/java-se/download-and-install-java-8-on-windows))

2. maven 3.x (https://maven.apache.org/download.cgi)

&emsp;tutorial: (https://phoenixnap.com/kb/install-maven-windows)
  
  (1). After 'Step 3:Add MAVEN_HOME Directory in PATH Variable', you should create a new folder called 'Maven-Repository' under C:\Program Files\Maven and use it as a local repository for maven.

  (2). Find the settings.xml file under C:\Program Files\Maven\apache-maven-3.8.1\conf

  (3). Find the node 'localRepository' and add it outside the comment

   ![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/11.png)

3. Intellij IDEA (https://www.jetbrains.com/idea) (You should download the Ultimate Version of IDEA)

Steps for building:


1. Import the back-end project to IDEA

2. Set the java environment, File -> Project Structrue -> Project, set the path of jdk1.8, and set java8 in IDEA.
![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/4.png)

3. Find the project's file 'pom.xml' in IDEA and right-click to set maven project.

4. Go to File -> setting, search for maven, set maven home path to the root directory where maven is installed, and confirm the modification.
![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/3.png)

5. Finally found the system-api\business-api\src\main\java\com\\project\BussinessApiApplication under the project directory of the Java.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/6.png)

Then the local password for the database is set in application-dev.yml.(if your local database password is not ‘123456’, set it to your real password in the .yml file)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/7.png)

Then run the BusinessApiApplication.java by the SpringBoot.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/8.png)


<br>
<br>
URL of the website where the project has been deployed: We do not deployed the project on the server.

<br>
<br>
Future Plan: We will add the function that can allow the administrator to add or delete courses.

<br>
<br>
Acknowledgements (if any) - You can list tutorials used, projects referred to, people consulted etc:

We used an open source architecture on the Web-(RenRen framework)

To build the project, I used some online learning resources to help complete the development of the project, but this learning material is in Chinese version. I'll put the link below.

(http://t.csdn.cn/56seU)

