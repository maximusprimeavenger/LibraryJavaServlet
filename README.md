# 📚 Library Management System (Servlet-based)

## 📌 Overview
This is a simple **Library Management System** built using **Java Servlets** and **JSP**. It allows users to manage books, borrow and return them, and perform basic CRUD operations.

## 🚀 Features
- 📖 **Book Management**: Add, edit, delete, and list books.
- 👤 **User Authentication**: Login/logout functionality for admins and users.
- 📚 **Borrow & Return System**: Track borrowed books and return dates.
- 🗄 **Database Integration**: Uses MSSQL for persistent data storage.
- 🌐 **JSP & Servlets**: Handles dynamic content and user interactions.

## 🛠 Tech Stack
- **Backend**: Java (Servlets, JSP)
- **Database**: MSSQL
- **Web Server**: Apache Tomcat
- **Frontend**: HTML, CSS, Bootstrap
- **ORM**: JDBC

## 📂 Project Structure
```
LibraryJavaServlet/
│── JAVALibrary/
│   ├── .settings/
│   ├── bin/
│   │   ├── net/codejava/sql/
│   │   │   ├── JavaSQLConnection.class
│   ├── src/
│   │   ├── net/codejava/sql/
│   │   │   ├── .classpath
│   │   │   ├── .project
│── JavaSQLServer1/
│   ├── .settings/
│   ├── src/
│   │   ├── main/java/net/codejava/sql/
│   │   │   ├── ContactForm.java
│   │   │   ├── JavaSQLConnection.java
│   │   │   ├── Login.java
│   ├── target/
│   │   ├── classes/
│   ├── .classpath
│   ├── .project
│   ├── pom.xml
│── Servers/
│   ├── .settings/
│   ├── Tomcat v10.1 Server at localhost-config/
│   ├── .project
│── Servlet/
│── build/
│   ├── classes/
│   │   ├── garvit/
│   │   │   ├── BookList.class
│   │   │   ├── BookListUser.class
│   │   │   ├── DeleteServlet.class
│   │   │   ├── EditScreenServlet.class
│   │   │   ├── EditServlet.class
│   │   │   ├── Login.class
│   │   │   ├── RegisterServlet.class
│   │   │   ├── RegisterUsers.class
│   │   │   ├── ServletLibrary.class
│   │   │   ├── User.class
│   │   │   ├── UserAccount.class
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── net/
│   │   │   │   ├── codejava/
│   │   │   │   │   ├── sql/
│   │   │   │   │   │   ├── ContactForm.java
│   │   │   │   │   │   ├── JavaSQLConnection.java
│   │   │   │   │   │   ├── Login.java
│── .settings/
│── .classpath
│── .project
│── .gitignore
│── README.md
│── pom.xml
```

## ⚡ Installation & Setup
### Prerequisites
- Install **Java JDK 11+**
- Install **Apache Tomcat 9+**
- Install **MSSQL** and create a database

### Steps
1. **Clone the repository:**
   ```sh
   git clone https://github.com/maximusprimeavenger/LibraryJavaServlet.git
   cd LibraryJavaServlet
   ```
2. **Set up the database:**
   - Create an MSSQL database: `library_db`
   - Import `database.sql` file.
3. **Configure database credentials:**
   - Update `JavaSQLConnection.java` with your MSSQL credentials.
4. **Build and deploy the project:**
   ```sh
   mvn clean package
   cp target/library.war $TOMCAT_HOME/webapps/
   ```
5. **Start Tomcat and access the app:**
   - URL: `http://localhost:8080/library`
