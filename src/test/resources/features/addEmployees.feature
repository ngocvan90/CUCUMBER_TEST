Feature: Add employees 
	As an admin of the company
  I want add employees to TINYpulse
  

Background: User logs in to Dashboard page 
	Given User is on Login page 
	When User enters "ngocvan1601901@gmail.com" and "12345678x@X" 
	And User click on Sign In button 
	
Scenario: Add employees 
	Given User clicks on User & Settings icon in top right corner 
	When User clicks on Add People menu 
	And User adds some users into list 
		| First Name  | Last Name   | Email    |
		| User 1 	  | A 			| a@gmail.com |
		| User 2 	  | B 			| b@gmail.com |
	And User clicks on Add People button 
	Then Verify that system displays successful message "Congratulations"
	Then User closes browser 