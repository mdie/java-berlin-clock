Story: Hello World

Meta:
@scope interview

Narrative:
    I want to say Hello World (including day of the week)

Scenario: Monday
When the date is 01-02-2016
Then the message should look like
Hello World on Monday

Scenario: Tuesday
When the date is 26-01-2016
Then the message should look like
Hello World on Tuesday

Scenario: Wednesday
When the date is 03-02-2016
Then the message should look like
Hello World on Wednesday

Scenario: Thursday
When the date is 28-01-2016
Then the message should look like
Hello World on Thursday

Scenario: Friday
When the date is 05-02-2016
Then the message should look like
Hello World on Friday

Scenario: Saturday
When the date is 23-01-2016
Then the message should look like
Hello World on Saturday

Scenario: Sunday
When the date is 24-01-2016
Then the message should look like
Hello World on Sunday
