# Templates Branch
in this repository we are focusing on creating code snippets and templates to help  us develop our services faster
There are many concepts we can cover some of which are listed below:


Once completing we check it out so the checklist above shows all you can do with this template.

## Getting Started

Before you start, you need to set up the development environment, you do this by:

- Installing Java JDK - [Refer Here](https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.msi)
- Installing an IDE, VS Code [Download Here](https://code.visualstudio.com/), IntelliJ IDEA [Download Here](https://www.jetbrains.com/idea/)


I Prefer using VS Code but it needs a bit of configuration follow these steps to:
- Extension Pack for Java.
- Spring Boot Extension Pack.

Once you install these extensions now you can clone this repository

Clone this template branch from our repository

    git clone --single-branch -b template https://github.com/DidIrb/backend-springboot-reference.git

Run the application and play around with the code

### Using VS Code to initialize/start from scratch

If you need to initialize a new spring boot application you can do so by following these steps
- Ctrl Shift P. - this opens up a list of options you Search for spring initializer and Select maven.
- Select snapshot version 3.0.8. - Language is Java.
- Group id - com.nameOfChoice.
- ArtifactId - hello.
- Packaging type - Jar. Java version. - 17

Add dependencies - there are multiple dependencies.
In our application we used these dependencies - you can check them in pom.xml file
- Spring JPA
- MYSQL
- Spring Web