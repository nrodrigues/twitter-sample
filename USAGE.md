# Twitter Sample REST Api #

Base Url: http://twitter-sample.cloudfoundry.com/rest

All resources, except the Login Api, require an API token to be passed in 
as a query parameter, example:

	http://twitter-sample.cloudfoundry.com/rest/users?token=7de7a22c-1e90-471b-8b55-d0c70ff05df3

## Login Api ##

* Login user
		
		POST /login/{username}

		
## Users Api ##

* List all users

		GET /users

* Create user

		POST /users
		
	* Body example (Content-type: application/json):

			{ "username": "value" }
	
* Retrieve user by username

		GET /users/{username}
	
* List followers

		GET /users/{username}/followers
	
* List following

		GET /users/{username}/following

* Follow user

		POST /users/{username}/following
		POST /users/{username}/followers
	
	* Body example (Content-type: application/json):

			{ "username": "username to follow" }
		
* Unfollow user

		DELETE /users/{username}/following
		DELETE /users/{username}/followers
	
	* Body example (Content-type: application/json):

			{ "username": "username to unfollow" }
		
## Tweets Api ##

* Post tweet
		
		POST /tweets/{username}
		
	* Body example (Content-type: text/plain):
	
			Some tweet
			
* List tweets

		GET /tweets/{username}[?search=keyword]
		