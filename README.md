# InventoryDatabase
A Spring Web MVC and MySQL application of a simple inventory database with add, view, edit, and delete features.
This project was developed using: Spring MVC, MySQL, Apache Tomcat, Eclipse IDE.

<div>
<h3>Home Page</h3>
<img src="https://user-images.githubusercontent.com/29102307/28386201-62cf19ac-6cfd-11e7-893c-fbf2efaea7a0.png" width="430">
<p>The Home Page give the user access to all features right away.</p>
</div>

<div>
<h3>Home Page</h3>
<img src="https://user-images.githubusercontent.com/29102307/28386196-62b8189c-6cfd-11e7-9750-9076d68891b9.png" width="430">
<img src="https://user-images.githubusercontent.com/29102307/28386200-62c0181c-6cfd-11e7-83f0-379c58f947cd.png" width="430">
<p>Add and Search fields are displayed using the same .jsp page. From the Home Page, clicking on either the Add or Search button calls on the same method in the controller class, which returns the desired .jsp page.</p>
<p>Clicking on the Search button however, <b>adds a query string</b> to the URL. The .jsp page searches the redirect URL for the attached query string, to know whether it displays the Add or Search input fields.</p>
</div>

<div>
<h3>Adding Items</h3>
<img src="https://user-images.githubusercontent.com/29102307/28387033-1be61d8a-6d00-11e7-91ce-6f7266b2fc2e.png" width="430">
<img src="https://user-images.githubusercontent.com/29102307/28386199-62bce728-6cfd-11e7-978c-a79ff57afcec.png" width="430">
<p>Users may not add items with the same ID. A <b>custom validation class</b> is implemented to check whether an item with the same ID already exists in the database. An error message is displayed if a match is found.</p>
<p>If no match is found, the new item is added to the database, and the user is redirected back to the updated List Page.</p>
</div>

<div>
<h3>Searching Items</h3>
<img src="https://user-images.githubusercontent.com/29102307/28386198-62bc8594-6cfd-11e7-9380-382cac01719c.png" width="430">
<img src="https://user-images.githubusercontent.com/29102307/28387487-e92d9d4e-6d01-11e7-8296-a0710c288b3a.png" width="430">
<p>Users may search for items via ID or Name. If no match is found, an error message is displayed. Otherwise, the user is redirected to the item's Edit Page.</p>
</div>

<div>
<h3>Editing Items, Form Validation</h3>
<img src="https://user-images.githubusercontent.com/29102307/28386197-62b9983e-6cfd-11e7-8a00-c74da713af0d.png" width="430">
<p>Users may not edit the item's ID. Users may not leave any field blank as well, when editing an item.</p>
<img src="https://user-images.githubusercontent.com/29102307/28388395-2df65d46-6d05-11e7-9264-f6f188e281d7.png" width="430">
<img src="https://user-images.githubusercontent.com/29102307/28388394-2df054f0-6d05-11e7-84a6-f2c6930805f9.png" width="430">
<p>Only <b>positive numerical values</b> may be entered into the database for the <b>Unit Price</b> and <b>Quantity</b> fields. The custom validation class returns error messages otherwise.</p>
</div>

<div>
<h3>Deleting Items</h3>
<img src="https://user-images.githubusercontent.com/29102307/28387041-233c87ea-6d00-11e7-8361-2bdd646cbb68.png" width="430">
<img src="https://user-images.githubusercontent.com/29102307/28387894-68912848-6d03-11e7-8745-7f2bc663c26b.png" width="430">
<p>A confirmation dialogue is returned when a user clicks on the Delete button. On confirmation, the user is redirected to the updated List Page.</p>
</div>
