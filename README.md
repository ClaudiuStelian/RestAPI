# Summary
This RestAPI provides Video Streaming services like sports, movies etc., to their Customers. Before subscribing to their services customers can register for 15 days free trail account. Before or after completing 15 days free trail they can make a payment for Annual/Monthly Subscription. It also fulfills two services (users and payments).

# Requirements    
Running the solution requires the following tools:  
Note: those requirements are for Windows operating system.  
Prerequisites:    
1. Eclipse IDE for Java EE Developers  
(https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-eedevelopers)  
2. Java JDK (Java Development Kit) – version 8  
3. Tomcat Server (e.g., version 10.0.22)  
4. Postman (https://www.postman.com/) – for testing purposes (Note: any other HTTP Client is
acceptable: e.g., cURL)  

# Running steps
1. Download the repo contempt.  
2. Extract archive to a desired location  
3. Open Eclipse IDE (set workspace directory)    
4. Access File menu item -> Import -> extend Maven, and select Existing Maven Projects  
5. Browse the location of the extracted archive file and change directory to “api” directory    
6. Click Finish and wait until the application is loaded in Eclipse IDE  
7. Click on “play green icon” to run the application. (Make sure the tooltip displays: Run  
8. RestAPIApplication, otherwise select RestAPIApplication from the dropdown existing near the button)  

# Manual Testing evidence  
Once the application is started, the testing steps includes requests sent from Postman:  
Testing register user flow: will register the user with its own credentials (username, password, email, dob, cardNo)  
Testing get users flow returns all users when filter is missing  
Returns users with card number when filter is yes (http://localhost:8080/users/yes)  
Returns users without card number when filter is no (http://localhost:8080/users/no)  
Return empty where not a valid filter (http://localhost:8080/users/invalid)  
Testing payments flow introducing the cardNo and amount (http://localhost:8080/payments)  
