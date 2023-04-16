@Search
Feature: Search
  @searchandverifycount
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


@Sendmail
  Scenario: tosend mail and create excel file
  When i send mail with messege to someone

@BrokenLinksAllbrand
  Scenario Outline: to check the brokenlink of all abfrl brand and send mail with attatchment
  When i check broken link for '<brandURL>' then i send the mail
  Examples:
    | brandURL                         |  |
    | https://www.thecollective.in/    |  |
    | https://allensolly.abfrl.in/     |  |
    | https://louisphilippe.abfrl.in/  |  |
    | https://peterengland.abfrl.in/   |  |
    | https://vanheusenindia.abfrl.in/ |  |



