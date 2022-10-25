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
Instructions on how to install and setup the project:
<br>
<br>
<h2>The 'choose-course-api' is the back-end, 'choose-course-ui' is the foront-end, and the .spl file 'student_choose_course' is the database.</h2>
<br>
<br>

<h2>Environment Construction:</h2>


</h3>Front end:</h3>


1. Download the node 12. X (tutorial: https://blog.csdn.net/Small_Yogurt/article/details/104968169)

(nodejs--https://nodejs.org/en/)

(The tutorial of install and run vue --https://softauthor.com/up-and-running-with-vuejs3-project/)

2. Go to the root directory of the front-end project and run the 'npm install' command to install the dependencies. After the installation is successful, a 'node_modules' folder will be added to the root directory of the project

3. run 'npm run serve' in the root directory to run the project

4. Enter 'localhost:8001/#/login' in the browser to access the login page. The front-end is successfully set up

In the previous part, we used a UI-library developed by a Chinese Internet company, which could cooperate well with vue to complete the development of the project.-(element-ui)

(install element-ui---- https://element.eleme.io/\#/zh-CN)

<h3>Database:</h3>

1. Install mysql (https://www.runoob.com/mysql/mysql-install.html)

2. Install navicat -- (https://www.navicat.com/en/products/navicat-premium)

The database part we use mysql language, put sql code into Navicat Premium 16 to run.

Create a database which called ‘student_choose_course’, then run the sql file.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/5.png)

<h3>Backend:</h3>

Required Environment:

1. java8 ([https://zhuanlan.zhihu.com/p/116020283](https://www.codejava.net/java-se/download-and-install-java-8-on-windows))

2. maven 3.x (https://maven.apache.org/download.cgi)
  tutorial: (https://phoenixnap.com/kb/install-maven-windows)
  (1). After 'Step 3:Add MAVEN_HOME Directory in PATH Variable', you should create a new folder called 'Maven-Repository' under C:\Program Files\Maven and use it as a local repository for maven.

  (2). Find the settings.xml file under C:\Program Files\Maven\apache-maven-3.8.1\conf

  (3). Fine the node 'localRepository' and add it outside the comment

      

3. Intellij IDEA (https://www.jetbrains.com/idea)
  tutorial: (https://blog.csdn.net/weixin_51521490/article/details/122029830)

Steps for building:


1. Import the back-end project to idea

2. Set the java environment, File -> Project Structrue -> Project, set the path of jdk1.8, and set java8

3. Locate the project's pom.xml and right-click set maven project

4. Go to File -> setting, search for maven, set maven home path to the root directory where maven is installed, and confirm the modification

5. Finally found the system API \ business - API \ SRC \ main \ Java \ com \ BussinessApiApplication under the project directory of the Java, Locate the main method, right click on the triangle in front of the method, and click run/debug BusinessApiApplication. The project runs successfully


(open choose-course-api with IntelliJ IDEA(2022) )

(Configure maven into IntelliJ IDEA)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/3.png)


(Configure jdk1.8 into IntelliJ IDEA)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/4.png)



When you've configured all the environments, open the BusinessApiApplication.java.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/6.png)

Then the local password for the database is set in application-dev.yml(if your database password is not ‘123456’, set it to your real password in the .yml file)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/7.png)

Then run the BusinessApiApplication.java by the SpringBoot.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/8.png)

After a few seconds, if it runs it successfully, then open CMD as the administrator, cd to the choose-course-ui folder.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/9.png)

If you do not install the ‘npm’, just input ‘npm install’, otherwise, just input ‘npm run serve’, after a few seconds, the website will be opened.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/master/ReadmeImage/10.png)

URL of the website where the project has been deployed: We do not deployed the project on the server.

Future Plan: We will add the function that can allow the administrator to add or delete courses.

Acknowledgements (if any) - You can list tutorials used, projects referred to, people consulted etc:

We used an open source architecture on the Web-(Everyone framework)

To build the project, I used some online learning resources to help complete the development of the project, but this learning material is in Chinese version. I'll put the link below.

(http://t.csdn.cn/56seU)

