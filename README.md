**Project Title - Online store cart & order pipeline**
**Team Members -**
- Aanurag Chakraborty - 2620030684
- Amlan Prusty - 2620040026

**STATUS -**
- CO1, CO2, CO3 completed.




**Approach**
- First I made a card authenticator, using the Luhn's algorithm, which will check number of digits of the card details, if the no. of digits is 15, then the card will be flagged as an American express card using the AMEX network, if the number is 16, then the card will be flagged as either a visa, a mastercard or a rupay card.
(the reason for only these 4 is that these are the most common types of card issued in india, there are other networks like dinersclub international which i have not taken into account because of its tiny userbase.)

- Second, I will make a data base of all the products in a warehouse(inventory file) and the user can choose any of them depending on their bank balance. 

- Third, I will make a function where you can input your bank balance inside the code itself, which will be checked into the main processing function

- Fourth, I will take inputs on payment methods, card number/UPI ID, CVV, expiration date, choice of items etc.

**UPDATE** 
- Will not be implementing a database as this project has to be converted into java and to implement a database in java we have to mess around with JDBC which i hold no knowledge in, for now its just going to be a function with an array holding the inventory in the format [item_number, item_name, price] and if it works out, i will be adding a quantity variable as well.
