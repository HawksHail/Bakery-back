# Blissful Bakery Backend

![Java CI](https://github.com/GenC-EESept21/zion-mantey-project/actions/workflows/mavenTest.yml/badge.svg)

## About

This is the backend of my Academy Enablement capstone project. The frontend can be
found [here](https://github.com/GenC-EESept21/zion-mantey-frontend).

This is an e-commerce website for an imaginary bakery. The backend is protected with spring security and users must
authorize with Auth0 frontend to generate a Bearer Token.

## Endpoints

The endpoints for this application are:

* `/supplier`
* `/category`
* `/product`
* `/customer`
* `/cart`
* `/order`
* `/`

### Sub-routes

Each route has sub-routes for various actions

#### Supplier

* POST `/supplier` - Creates a supplier from JSON body. Must have `create:supplier` permission.
* GET `/supplier` - Returns a list of all suppliers.
* GET `/supplier/<id>` - Returns a supplier with the id.
* PUT `/suplier` - Updates a supplier from JSON body. Must have `update:supplier` permission.
* DELETE `/supplier/<id>` - Deletes a supplier with specified id. Must have `delete:supplier` permission.

#### Category

* POST `/category` - Creates a category from JSON body. Must have `create:category` permission.
* GET `/category` - Returns a list of all categories.
* GET `/category/<id>` - Returns a category with the id.
* PUT `/category` - Updates a category from JSON body. Must have `update:category` permission.
* DELETE `/category/<id>` - Deletes a category with specified id. Must have `delete:category` permission.

#### Product

* POST `/product` - Creates a product from JSON body. Must have `create:product` permission.
* GET `/product` - Returns a list of all products.
* GET `/product/<id>` - Returns a product with the id.
* GET `/product/featured` - Returns a list of 5 random products.
* PUT `/product` - Updates a product from JSON body. Must have `update:product` permission.
* DELETE `/product/<id>` - Deletes a product with specified id. Must have `delete:product` permission.

#### Customer

* POST `/customer` - Creates a customer from JSON body. Must be authenticated, or have `create:customer` permission.
* POST `/customer/sub` - Returns a customer with the sub id in JSON body. Must be yourself or have `view:customer`
  permission.
* GET `/customer` - Returns a list of all customers. Must have `view:customer` permission.
* GET `/customer/<id>` - Returns a customer with the id. Must be yourself or have `view:customer` permission.
* PUT `/customer` - Updates a customer from JSON body. Must be yourself or have `update:customer` permission.
* DELETE `/customer/<id>` - Deletes a customer with specified id. Must have `delete:customer` permission.

#### Cart

* POST `/cart/<customer id>/<product id>` - Adds a product to a customer's cart. Must be yourself or
  have `view:customer` permission.
* POST `/cart/<customer id>` - Checks out a customer. Creates new order and adds all cart items to it, then clears the
  cart. Must be yourself or have `view:customer` permission.
* GET `/cart/<id>` - Returns a cart with the customer id. Must be yourself or have `view:customer` permission.
* DELETE `/cart/<customer id>/<product id>` - Deletes a product from customer's cart with specified ids. Must be
  yourself or have `view:customer` permission.
* DELETE `/cart/<customer id>` - Deletes all products from customer's cart. Must be yourself or have `view:customer`
  permission.

#### Order

* POST `/order` - Creates an order from JSON body. Must be authenticated.
* GET `/order` - Returns a list of all orders. Must have `view:order` permission.
* GET `/order/<id>` - Returns an order with the id. Must be owned by yourself or have `view:order` permission.
* GET `/order/customer/<customer id>` - Returns a list of all orders for a customer. Must be owned by yourself or
  have `view:order` permission.
* PUT `/order` - Updates an order from JSON body. Must have `update:order` permission.
* DELETE `/order/<id>` - Deletes an order with specified id. Must have `delete:order` permission.

##### Order Details
* POST `/order/details` - Adds list of `OrderDetails` to an order from JSON body. Must be authenticated.
* GET `/order/details` - Returns a list of all order details. Must have `view:order` permission.
* GET `/order/details/<order id>/<product id>` - Returns an order detail from an order for a product.
* PUT `/order/details` - Updates an `OrderDetails` from JSON body. Must have `update:order` permission.
* DELETE `/order/<order id>>/<product id>` - Deletes a product from an order. Must have `delete:order` permission.

#### /

* GET `/` - Always returns 200.
