Feature: Customers
  Test functionality of orders in the customer and titles tabs
  Note: This feature is meant to be run with an empty database

  Scenario: Create, Edit, and Delete a Customer Order
    When I click on Customers tab
    When I add customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    Then I should see customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    When I click on Titles tab
    When I add titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    Then I should see titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    When I click on Customers tab
    When I add requests for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 2         |
    Then I should see orders for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 2         |
    When I click on Titles tab
    Then I should see orders for title: "Superman"
      | Last Name   | First Name    | Quantity  | Issue |
      | Cart        | Zach          | 2         |       |
    When I click on Customers tab
    When I edit requests for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 3         |
    Then I should see orders for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 3         |
    When I delete requests for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 3         |
    Then I should see orders for last name: "Cart"
      | Title     | Issue   | Quantity  |
    When I delete customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    Then I should see customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
    When I click on Titles tab
    When I delete titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    Then I should see titles
      | Title     | Product Id  | Price   | Notes     |

  Scenario: Delete customer with active orders
    When I click on Customers tab
    When I add customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    Then I should see customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    When I click on Titles tab
    When I add titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    Then I should see titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    When I click on Customers tab
    When I add requests for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 2         |
    Then I should see orders for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 2         |
    When I click on Titles tab
    Then I should see orders for title: "Superman"
      | Last Name   | First Name    | Quantity  | Issue |
      | Cart        | Zach          | 2         |       |
    When I click on Customers tab
    When I delete customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    Then I should see customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
    When I click on Titles tab
    Then I should see orders for title: "Superman"
      | Last Name   | First Name    | Quantity  | Issue |
    When I delete titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    Then I should see titles
      | Title     | Product Id  | Price   | Notes     |

  Scenario: Delete title with active orders
    When I click on Customers tab
    When I add customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    Then I should see customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    When I click on Titles tab
    When I add titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    Then I should see titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    When I click on Customers tab
    When I add requests for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 2         |
    Then I should see orders for last name: "Cart"
      | Title     | Issue   | Quantity  |
      | Superman  |         | 2         |
    When I click on Titles tab
    Then I should see orders for title: "Superman"
      | Last Name   | First Name    | Quantity  | Issue |
      | Cart        | Zach          | 2         |       |
    When I delete titles
      | Title     | Product Id  | Price      | Notes     |
      | Superman  | 10          | 12.00      |           |
    Then I should see titles
      | Title     | Product Id  | Price   | Notes     |
    When I click on Customers tab
    Then I should see orders for last name: "Cart"
      | Title     | Issue   | Quantity  |
    When I delete customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |
      | Zach         | Cart        | 581-237-9918 | zachCart@max.com  | Weirdo   |
    Then I should see customers
      |  firstName   |  lastName   |   phone      |  email            |  notes   |

