# Created by lfbrienze at 12/09/22
@ConfirmOffer
Feature: Confirm offer feature
  Offer should be generated and confirmed within time

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
  Scenario: Confirm buy btc offer success
    Given the client has 10000.0 "brl" balance
    When an offer is created to "buy" 0.001 "btc"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "0.001"
    And the field "data.quoteAmount" should be equal "100.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 9900.0
    And the client "btc" balance should be equal 0.001

  @BuyBtcSuccessExactAmount
  Scenario: Confirm buy btc offer success exact amount balance
    Given the client has 100000.0 "brl" balance
    When an offer is created to "buy" 1.0 "btc"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "1.0"
    And the field "data.quoteAmount" should be equal "100000.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 0.0
    And the client "btc" balance should be equal 1.0

  @SellBtcSuccess
  Scenario: Confirm sell btc offer success
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 0.001 "btc"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "0.001"
    And the field "data.quoteAmount" should be equal "99.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 99.0
    And the client "btc" balance should be equal 0.999

  @SellBtcSuccessExactAmount
  Scenario: Confirm sell btc offer success exact amount
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 1.0 "btc"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BTC"
    And the field "data.quote" should be equal "BRL"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be false
    And the field "data.baseAmount" should be equal "1.0"
    And the field "data.quoteAmount" should be equal "99000.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 99000.0
    And the client "btc" balance should be equal 0.0

  @BuyBrlSuccess
  Scenario: Confirm buy brl offer success
    Given the client has 1.0 "btc" balance
    When an offer is created to "buy" 10000.0 "brl"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "10000.0"
    And the field "data.quoteAmount" should be equal "0.1010101"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 10000.0
    And the client "btc" balance should be equal 0.89898990

  @BuyBrlSuccessExactAmount
  Scenario: Confirm buy brl offer success exact amount
    Given the client has 1.0 "btc" balance
    When an offer is created to "buy" 99000.0 "brl"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "buy"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "99000.0"
    And the field "data.quoteAmount" should be equal "1.0"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 99000.0
    And the client "btc" balance should be equal 0.0

  @SellBrlSuccess
  Scenario: Confirm sell brl offer success
    Given the client has 10000.0 "brl" balance
    When an offer is created to "sell" 1000.0 "brl"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "1000.0"
    And the field "data.quoteAmount" should be equal "0.01"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 9000.0
    And the client "btc" balance should be equal 0.01

  @SellBrlSuccessExactAmount
  Scenario: Confirm sell brl offer success exact amount
    Given the client has 10000.0 "brl" balance
    When an offer is created to "sell" 10000.0 "brl"
    And is confirmed within time
    Then the return status code should be 200
    And the return body should not be null
    And the field "message" should be empty
    And the field "data.offerId" should not be empty
    And the field "data.base" should be equal "BRL"
    And the field "data.quote" should be equal "BTC"
    And the field "data.op" should be equal "sell"
    And the field "data.isQuote" should be true
    And the field "data.baseAmount" should be equal "10000.0"
    And the field "data.quoteAmount" should be equal "0.1"
    And the field "data.createdAt" should not be empty
    And the field "data.expiresAt" should be null
    And the field "data.confirmedAt" should not be empty
    And the field "data.apiKeyId" should be equal "api_key_test"
    And there should be an offer with the same id in the db
    And the client "brl" balance should be equal 0.0
    And the client "btc" balance should be equal 0.1

  @ConfirmationFailShortOnBrlBalance
  Scenario: Confirm failure short on brl balance
    Given the client has 10000.0 "brl" balance
    When an offer is created to "sell" 1000.0 "brl"
    And it's id is saved as "firstId"
    And an offer is created to "sell" 10000.0 "brl"
    And it's id is saved as "secondId"
    And "firstId" is confirmed within time
    And "secondId" is confirmed within time
    Then the return status code should be 400
    And the error message should be "Brl balance cannot be less than transaction value."
    And the client "brl" balance should be equal 9000.0
    And the client "btc" balance should be equal 0.01

  @ConfirmationFailShortOnBtcBalance
  Scenario: Confirm failure short on btc balance
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 0.1 "btc"
    And it's id is saved as "firstId"
    And an offer is created to "sell" 1.0 "btc"
    And it's id is saved as "secondId"
    And "firstId" is confirmed within time
    And "secondId" is confirmed within time
    Then the return status code should be 400
    And the error message should be "Bitcoin balance cannot be less than transaction value."
    And the client "brl" balance should be equal 9900.0
    And the client "btc" balance should be equal 0.9

  @ConfirmationFailOfferExpired
  Scenario: Confirm failure offer expired
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 0.1 "btc"
    And is not confirmed within time
    Then the return status code should be 400
    And the error message should be "Offer expired."
    And the client "brl" balance should be equal 0.0
    And the client "btc" balance should be equal 1.0


  @ConfirmationOfferAuthorizationFailure
  Scenario: Confirm offer authorization failure
    Given the client has 1.0 "btc" balance
    When an offer is created to "sell" 0.1 "btc"
    And is confirmed within time without token
    Then the return status code should be 403
    And the error message should be "Unauthorized."