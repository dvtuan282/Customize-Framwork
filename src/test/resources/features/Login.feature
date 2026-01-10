Feature: Login

  Background:
    Given user load data to
      | productDataTest.conf |

  @login_01
  Scenario: Login - user login success
    When user click on @homePage.imgMenu
    And user click on @homePage.lblLogin
    And user login with username "tuandv28" and password "123456"
    And user click on @homePage.imgMenu
    Then user verify status @homePage.lblLogout is "visible"

  @login_02
  Scenario Outline: Login - user login invalid
    When user click on @homePage.imgMenu
    And user click on @homePage.lblLogin
    And user login with username "<username>" and password "<password>"
    Then user verify status <alertErrorEl> is "visible"
    Examples:
      | username | password | alertErrorEl                |
      |          | 123456   | @loginPage.lblErrorUsername |
      | tuandv8  |          | @loginPage.lblErrorPassword |


