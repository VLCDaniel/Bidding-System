# Bidding-System
Bidding system using OOP concepts in Java

### Classes
- Abstract class User
- (Extensions from User) => Bidder, BidderAgent, Seller
- Abstract class Product
- (Extensions from Product) => CollectionProduct, AncientProduct
- Auction 
- (Extensions from Auction) => CharitiAuction

### Supported operations
1. Every User can perform the following operations:
* Register
* Log In
* Log Out
* Delete account
* Display sorted auctions (by date)

2. Seller can perform the following unique operations:
* Add a new product to his account
* Add a new auction
* Display his auctions
* Add a product to an available auction

3. Bidder can perform the following unique operations:
* Register to an available auction
* Rent a Bidder Agent for a comission
