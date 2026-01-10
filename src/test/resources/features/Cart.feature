Feature: Cart

  Background:
    Given user load data to
      | productDataTest.conf |

  @cart01
  Scenario: Add Product To Cart
    When user add product to cart
      | productName         | productColor | quantity |
      | Sauce Labs Backpack | Blue         | 6        |
    And user click on @homePage.iconCart
    Then user verify product add to cart
      | productName         | productColor | quantity |
      | Sauce Labs Backpack | Blue         | 6        |

  @cart02
  Scenario: Add Products To Cart
    When user add product to cart
      | productName                  | productColor | quantity |
      | Sauce Labs Backpack          | Blue         | 6        |
      | Sauce Labs Backpack (orange) | Unknown      | 1        |
    And user click on @homePage.iconCart