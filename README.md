# Simple-Webservice

## Requirements:
	Eclipse, JavaEE, Maven

## How to run the project?
	1. Import the porject as maven project in eclipse.
	2. Right-click on project and choose Maven clean option. "Run As"=>"Maven Clean"
	3. Follow the step 2 again but choose Maven Install this time. (It will take time to download the libraries)
	4. Go to file Application.java (src => waes.assignment) and run it as application. Keep it running
	5. Go to file Client.java  (src => waes.test) and run it as application.
	6. The output will be shown in console.

## Testing via web browser:
	URL to test from browser after running the Application.java file availabe under (src => waes.assignment) package: 
		1. http://localhost:8080/v1/diff/1/left => POST request and send Base64 encoded string in body
		2. http://localhost:8080/v1/diff/1/right => POST request and send Base64 encoded string in body
		3. http://localhost:8080/v1/diff/1 => GET request computes the equality and operation after post data on above urls is done

## Note:
	If Server/Application.java is not working, check for the port
	
	
### About me:
	Ans Riaz
	ansriazch@gmail.com
