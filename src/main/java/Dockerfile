FROM  openjdk
expose 8080
volume /tmp
Add target/opmac-0.0.1-SNAPSHOT.jar app.jar
entrypoint ["java","-jar","/app.jar"]
jdbc:sqlserver://opmacev.database.windows.net:1433;database=Enock;user={your_username_here};password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;authentication=ActiveDirectoryPassword