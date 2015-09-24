Products
========

Create a new product
--------------------
tags: admin, product, create

* Create a product 
     |Title         |Description         |Author        |Price|
     |--------------|--------------------|--------------|-----|
     |Go Programming|ISBN: 978-1453636671|John P. Baugh |25.00|
     |The Way to Go |ISBN: 978-1469769165|Ivo Balbaert  |20.00|
     |Go In Action  |ISBN: 9781617291784 |Brian Ketelsen|30.00|
     |Learning Go   |ebook               |Miek Gieben   |0.00 |

Search for a product
--------------------
tags: admin, product, search
"Find and Open product page for" is a concept

* Find and Open product page for "Go Programming"
* Verify product "author" as "John P. Baugh"

Search and edit and existing product
------------------------------------
tags: admin, product, edit
"Find and store productId for" and "Open product edit page for stored productId" uses the sceanrio datastore

* Open product edit page for stored productId
* Update product specifier to new value <table:resources/product_data.csv>
* Check product specifier has new value <table:resources/product_data.csv>

Delete a product
----------------
tags: admin, product, delete
The below concept is an example of nested concept, check out the definition.

* Delete product "Learning Go"
