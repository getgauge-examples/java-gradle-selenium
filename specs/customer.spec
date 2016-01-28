Customers
=========
Specifications are defined via H1 tag, you could either use the syntax above or "# Customers"

The below is a context step, gets executed before every scenario. Every unordered list is a step.

* On the customer page

The below is a scenario, defined via H2 tag. You could also use "## Search for a customer"

Search for a customer
---------------------
tags: admin, customer, search

* Search for customer "ScroogeMcduck"
* The customer "ScroogeMcduck" is listed

Verify a bunch of customers
---------------------------
tags: admin, customer, search
Can use a csv datasource!

* Search for customers <table:resources/user.csv>
