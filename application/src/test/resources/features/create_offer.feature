# Created by lfbrienze at 12/09/22
@CreateOffer
Feature: Create offer feature
  Offer should be generated

  Background:
    Given the context is clean
    And biscoint url is set correctly
    And the offer db is empty
    And a client with api_key "api_key_test" and name "luis" exists in clients db
    And the following data exist in credentials db
      | api_key    | api_key_test    |
      | api_secret | api_secret_test |
    And the current bitcoin unitary "sell" value is 99000.00
    And the current bitcoin unitary "buy" value is 100000.00

  @BuyBtcSuccess
  Scenario: Create buy btc offer success
    Given the client has 10000.0 "brl" balance
    When an offer is created to "buy" 0.001 "btc"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "0.001"
    And the field "data.quoteAmount" should be equal "100.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @BuyBtcSuccessExactAmount
  Scenario: Create buy btc offer success exact amount balance
    Given the client has 100000.0 "brl" balance
    When an offer is created to "buy" 1.0 "btc"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "1.0"
    And the field "data.quoteAmount" should be equal "100000.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @SellBtcSuccess
  Scenario: Create sell btc offer success
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 0.001 "btc"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "0.001"
    And the field "data.quoteAmount" should be equal "99.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @SellBtcSuccessExactAmount
  Scenario: Create sell btc offer success exact amount
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 1.0 "btc"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "1.0"
    And the field "data.quoteAmount" should be equal "99000.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @BuyBrlSuccess
  Scenario: Create buy brl offer success
    Given the client has 1.0 "btc" balance
    When an offer is created to "buy" 10000.0 "brl"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "10000.0"
    And the field "data.quoteAmount" should be equal "0.1010101"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @BuyBrlSuccessExactAmount
  Scenario: Create buy brl offer success exact amount
    Given the client has 1.0 "btc" balance
    When an offer is created to "buy" 99000.0 "brl"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "99000.0"
    And the field "data.quoteAmount" should be equal "1.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @SellBrlSuccess
  Scenario: Create sell brl offer success
    Given the client has 10000.0 "brl" balance
    When an offer is created to "sell" 1000.0 "brl"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "1000.0"
    And the field "data.quoteAmount" should be equal "0.01"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @SellBrlSuccessExactAmount
  Scenario: Create sell brl offer success exact amount
    Given the client has 10000.0 "brl" balance
    When an offer is created to "sell" 10000.0 "brl"
    Then the return status code should be 200
    Then the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "10000.0"
    And the field "data.quoteAmount" should be equal "0.1"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should not be empty
    And the field "data.confirmedAt" should be null
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db

  @BuyBtcFailure
  Scenario: Create buy btc offer failure short on brl balance
    Given the client has 1000.0 "brl" balance
    When an offer is created to "buy" 0.011 "btc"
    Then the return status code should be 400
    And the error message should be "Brl balance cannot be less than transaction value."
    And there should not be an offer in the db

  @SellBtcFailure
  Scenario: Create sell btc offer failure short on btc balance
    Given the client has 0.001 "btc" balance
    When an offer is created to "sell" 0.0011 "btc"
    Then the return status code should be 400
    And the error message should be "Bitcoin balance cannot be less than transaction value."
    And there should not be an offer in the db

  @BuyBrlFailure
  Scenario: Create buy brl offer failure short on btc balance
    Given the client has 0.001 "btc" balance
    When an offer is created to "buy" 10000.0 "brl"
    Then the return status code should be 400
    And the error message should be "Bitcoin balance cannot be less than transaction value."
    And there should not be an offer in the db

  @SellBrlFailure
  Scenario: Create sell brl offer failure short on brl balance
    Given the client has 100.00 "brl" balance
    When an offer is created to "sell" 10000.0 "brl"
    Then the return status code should be 400
    And the error message should be "Brl balance cannot be less than transaction value."
    And there should not be an offer in the db