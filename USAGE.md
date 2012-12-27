Twitter Sample REST Api
=======================

Base Url: http://twitter-sample.cloudfoundry.com/rest


Users Api
---------

* List all users
	`GET /users`

* Create user
	`POST /users`
		
	* Body example:
		`{ "username": "value" }`
	
* Retrieve user by id
	`GET /users/{id}`
	
* List followers
	`GET /users/{id}/followers`
	
* List following
	`GET /users/{id}/following`
	
* Follow user
	`POST /users/{id}/following
	POST /users/{id}/followers`
	
	* Body example:
		`{ "id": "595f05c7-b932-47ba-a427-83c146426837" }`
		
* Unfollow user
	`DELETE /users/{id}/following
	DELETE /users/{id}/followers`
	
	* Body example:
		`{ "id": "595f05c7-b932-47ba-a427-83c146426837" }`
		
