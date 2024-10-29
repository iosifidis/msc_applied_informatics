# Programming - Software Engineering in Business

Welcome to the repository for the Programming - Software Engineering in Business.

## [Week 2](./weeek2)

In this exercise, we created a simple TicketMachine class that simulates the process of purchasing tickets by inserting coins. Through this example, we covered several core object-oriented programming concepts:

- **Encapsulation**: The fields (price, balance, and total) are declared private, restricting access to them and protecting the machine’s state. Public methods like insertMoney() and printTicket() allow controlled access and interaction.   
- **Constructors**: We use constructors to initialize the object’s fields when creating a TicketMachine instance. This approach ensures that every machine has a predefined ticket price.   
- **Methods (Getters and Setters)**: We use accessor (getter) and mutator (setter) methods to retrieve and modify private data safely. In this case, getPrice() returns the ticket price, while setPrice() modifies it only if the input is valid.   
- **Data Validation**: Methods like insertMoney() and setPrice() validate input to prevent errors (e.g., only accepting valid coin values).   
- **Static Methods and Parameters in Main**: The Main class's main method demonstrates creating objects, calling methods, and managing parameters and return values.   

Overall, this exercise illustrates encapsulation, data validation, constructor initialization, and basic method usage, fundamental to creating secure and reusable object-oriented code.

## [Week 3](./week3)

In this exercise, we implemented a ClockDisplay class to simulate a digital clock with hours and minutes. This involved using a NumberDisplay class to manage time values within fixed boundaries (0-23 for hours and 0-59 for minutes). We explored several programming concepts, including:

- **Encapsulation and Modularity**: Each class (ClockDisplay and NumberDisplay) is responsible for specific functionality, making the code organized and easy to manage.   
- **Constructor Overloading**: We used overloaded constructors in ClockDisplay and NumberDisplay to allow initialization with default or specific time values.
- **Private and Public Methods**: We used private methods, like updateDisplay(), for internal logic, while public methods, like timeTick() and getTime(), provide access to external classes.   
- **Validation of Input Values**: The NumberDisplay class limits values to prevent overflow, which demonstrates input validation.   
- **Method Calls and Incrementing Values**: The timeTick() method demonstrates handling interrelated data, where minutes affect hours.   

Overall, this exercise covers essential object-oriented principles, including encapsulation, method encapsulation, data validation, and constructor usage, enhancing code robustness and readability.
