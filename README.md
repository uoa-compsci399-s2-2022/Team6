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

Our front-end uses the Vue architecture, so we first need to download Nodejs, configure the environment on the computer, then install dependencies (npm), and then open the front-end folder (choose-course-ui) cmd, input npm run serve, you can run the front-end part.

(nodejs--https://nodejs.org/en/)

(The tutorial of install and run vue --https://softauthor.com/up-and-running-with-vuejs3-project/)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/1.png)

In the previous part, we used a UI-library developed by a Chinese Internet company, which could cooperate well with vue to complete the development of the project.-(element-ui)

(install element-ui---- https://element.eleme.io/\#/zh-CN)

In the back-end part, we use springboot architecture, so we use IntelliJ IDEA(2022) to run the back-end code. The back-end code is in the choose-course-api folder. First, we use IntelliJ IDEA(2022) to open the back-end folder, and then we need to download java8, maven(3.8.6), Configure jdk1.8 and maven into IntelliJ IDEA, and then you can run the backend code properly.

(install Intellij IDEA --- <https://www.jetbrains.com/idea/)>

(install maven ----https://phoenixnap.com/kb/install-maven-windows)

(install java8 ----https://www.codejava.net/java-se/download-and-install-java-8-on-windows)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/2.png)

(open choose-course-api with IntelliJ IDEA(2022) )

(Configure maven into IntelliJ IDEA)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/3.png)


(Configure jdk1.8 into IntelliJ IDEA)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/4.png)

(install navicat -- <https://www.navicat.com/en/products/navicat-premium)>

(install mysql -- <https://www.mysql.com/)>

The database part we use mysql language, put sql code into Navicat Premium 16 to run.

Create a database which called ‘student_choose_course’, then run the sql file.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/5.png)

When you've configured all the environments, open the BusinessApiApplication.java.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/6.png)

Then the local password for the database is set in application-dev.yml(if your database password is not ‘123456’, set it to your real password in the .yml file)

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/7.png)

Then run the BusinessApiApplication.java by the SpringBoot.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/8.png)

After a few seconds, if it runs it successfully, then open CMD as the administrator, cd to the choose-course-ui folder.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/9.png)

If you do not install the ‘npm’, just input ‘npm install’, otherwise, just input ‘npm run serve’, after a few seconds, the website will be opened.

![image](https://github.com/uoa-compsci399-s2-2022/Team6-Degree-Planner/blob/main/ReadmeImage/10.png)

URL of the website where the project has been deployed: We do not deployed the project on the server.

Future Plan: We will add the function that can allow the administrator to add or delete courses.

Acknowledgements (if any) - You can list tutorials used, projects referred to, people consulted etc:

We used an open source architecture on the Web-(Everyone framework)

To build the project, I used some online learning resources to help complete the development of the project, but this learning material is in Chinese version. I'll put the link below.

(http://t.csdn.cn/56seU)

