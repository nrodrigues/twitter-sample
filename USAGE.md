# Twitter Sample REST Api #

Base Url: http://twitter-sample.cloudfoundry.com/rest

All resources, except the Login Api, require an API token to be passed in 
as a query parameter, example:

	http://twitter-sample.cloudfoundry.com/rest/users?token=7de7a22c-1e90-471b-8b55-d0c70ff05df3

## Login Api ##

* Login user
		
		POST /login/{id}

		
## Users Api ##

* List all users

		GET /users

* Create user

		POST /users
		
	* Body example (Content-type: application/json):

			{ "username": "value" }
	
* Retrieve user by id

		GET /users/{id}
	
* List followers

		GET /users/{id}/followers
	
* List following

		GET /users/{id}/following

* Follow user

		POST /users/{id}/following
		POST /users/{id}/followers
	
	* Body example (Content-type: application/json):

			{ "id": "595f05c7-b932-47ba-a427-83c146426837" }
		
* Unfollow user

		DELETE /users/{id}/following
		DELETE /users/{id}/followers
	
	* Body example (Content-type: application/json):

			{ "id": "595f05c7-b932-47ba-a427-83c146426837" }
		
## Tweets Api ##

* Post tweet
		
		POST /tweets/{id}
		
	* Body example (Content-type: text/plain):
	
			Some tweet
			
* List tweets

		GET /tweets/{id}[?search=keyword]
		