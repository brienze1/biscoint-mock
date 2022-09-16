# Created by lfbrienze at 12/09/22
@GetBalance
Feature: Get balance feature
  Should return client current balance for BRL and BTC currencies

  Background:
    Given the context is clean
    And biscoint url is set correctly
    And a client with api_key "api_key_test" and name "luis" exists in clients db
    And the following data exist in credentials db
      | api_key    | api_key_test    |
      | api_secret | api_secret_test |
    And the current bitcoin unitary "sell" value is 99000.00
    And the current bitcoin unitary "buy" value is 100000.00

  @GetBalanceSuccess
  Scenario: Get balance success
    Given the client has 10000.0 "brl" balance
    And the client has 1.0 "btc" balance
    When get balance is called
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.BRL" should be equal "10000.00"
    And the field "data.BTC" should be equal "1.00000000"

  @GetBalanceAuthorizationFailure
  Scenario: Get balance authorization failure
    Given the client has 10000.0 "brl" balance
    And the client has 1.0 "btc" balance
    When get balance is called without token
    Then the return status code should be 403
    And the error message should be "Unauthorized."
