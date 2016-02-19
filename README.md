# Online Compiler
Online compiler developing by using Java technologies.
The project is quite simple and easy Rest API. It only takes three parameters such as language name,code and input value. After that, It executes given code and returns output and error(if exist) as JSON data.

Docker
-----------------------------
The project uses compilebox project's Docker setting which is available from https://github.com/remoteinterview/compilebox
On the other hand, It is clear Java equivalent of that project.

Docker Installation
-----------------------------
* Go to the 'Setup' directory.
    - Open the Terminal as root user
    
    - Execute the script **Install_*.sh**, select the file which best suites your Operating System description. This will install the Docker to your system and create an image called 'virtual_machine' inside the Docker. DockerVM may take around 20 to 30 minutes depending on your internet connection.
    
    - Once the Install script executes successfully, copy the folder named 'API' to your desired path and change your path value in com.onlinecompiler.service.DockerParameterConstants according to your desired path.You can also decide how long the execution process should take by setting timeoutSecond field.

NOTE: The project can occur error when it tries to execute command because of permission. To run shell commands in Java, follow these steps on below.

You need  to edit /etc/sudoers file:

sudo gedit /etc/sudoers

and add that lines to this file:

# for user
YOUR_USER_NAME ALL= NOPASSWD: ALL

# for group
YOUR_GROUP_NAME ALL= NOPASSWD: ALL

After that there will no any error about that.
    

Technologies
-----------------------------
- Spring 
- Spring MVC
- Log4j
