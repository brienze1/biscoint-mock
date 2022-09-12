# language: en
@GetToken
Feature: Token generation test
	The system should generate an access token.
	Following the rules bellow:
	1-) Should receive 3 headers
		1.1-) NONCE integer number strictly crescent
		1.2-) API_KEY client api key (used to get client api secret from db)
		1.3-) PATH url path where this token is going to be used
	2-) Should receive the payload to be used
	3-) The 3 values should be inlined and turned into a base64 string (PATH+NONCE+PAYLOAD)
	4-) The base64 string should be sha384 hashed using the api secret as key
	5-) Should return the token in json format

	Scenario: Get token with success
		Given the following data exist in credentials db
		  | api_key  		| 	api_secret		|
 		  | api_key_test  	| 	api_secret_test	|
		And the following payload is used
			"""
			{
				"test": "test_payload"
			}
			"""
		And the nonce used is 1234567890
		And the path used is "v1/test"
		When the token is generated
		Then the return status code should be 200
		And the token should be decoded using the secret "api_secret_test"
		And the payload decoded should be
			"""
			{
				"test": "test_payload"
			}
			"""
		And the path decoded should be "v1/test"