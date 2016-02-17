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
    
    - Once the Install script executes successfully, copy the folder named 'API' to your desired path and change your path value in com.onlinecompiler.service.imp.CompilerServiceImp according to your desired path.You can also decide how long the execution process should take by setting timeoutSecond field.
    

Technologies
-----------------------------
- Spring 
- Spring MVC
- Log4j
