# Documentation  

### Summary
Used Java Servlet to support all three services.
  
Routes & REST API:
  
/AddTransactions   
POST  
add a new item, request with JSON style, and response "OK" or "Failure"

/SpendPoints   
POST  
calculate spending Points, request with JSON style, and response a list with JSON style

/ReturnBalances  
GET   
display a summary list of every payer, no request parameter, and response a list with JSON style


Jackson for data mapping

### Environment Configure
Tomcat 9.0.41  
Java version 15.0.1  
URL: http://localhost:8080/points