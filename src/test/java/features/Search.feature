@Search
Feature: Search
  Scenario Outline: search and add to bag and verify count changing
  When i search as '<Searchitem>'
  Then i click on random product
  Then i select size and add to bag
  Then i copy my cart count
  Then i select another size and add to bag
  Then i verify cart count should not be same
    Examples:
      | Searchitem |
      | shirts     |