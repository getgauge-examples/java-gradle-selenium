Customers
=========
Specifications are defined via H1 tag, you could either use the syntax above or "# Customers"

The below is a context step, gets executed before every scenario. Every unordered list is a step.

* On the customer page

The below is a scenario, defined via H2 tag. You could also use "# Search for a customer"

Search for a customer
---------------------
* Search for customer "user"
* The customer "user" is listed

Verify a bunch of customers
---------------------------

Can use a csv datasource!
* Search for customers <table:user.csv>