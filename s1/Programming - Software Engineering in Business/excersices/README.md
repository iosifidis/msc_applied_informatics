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

## [Week 4](./week4)

We covered foundational Java concepts and techniques for managing data through classes and collections. These concepts are essential for creating structured and efficient applications. Below is a summary of the key points learned:

1. **Using Classes and Objects in Java:**

- We created classes (Main and Notebook) to define the properties and functionality of objects. For instance, the Notebook class stores notes and provides methods for adding, viewing, and deleting them.   
- Constructors are special methods used to initialize objects. We used the Notebook constructor to set the owner of the notebook.   

2. **ArrayLists and Object Collections:**

- ArrayList is a dynamic data structure that allows storage of objects and automatically expands as new elements are added.   
- We used ArrayList<String> to store notes (as String values) and ArrayList<Integer> to store integers (as Integer objects).   
- ArrayList requires objects for data storage, so primitive types (e.g., int) need to be "wrapped" using corresponding wrapper classes (e.g., Integer).   

3. **Wrapper Classes and Autoboxing/Unboxing:**

- Wrapper classes (such as Integer for int) enable the storage of primitive types in collections, making it easier to use them in environments requiring objects.   
- Autoboxing and Unboxing facilitate automatic conversion between primitives and their corresponding objects. In the programs, we saw how int values are automatically converted to Integer when stored in an ArrayList, and how Integer values are converted back to int upon retrieval.   

4. **Data Management through Methods:**

- Creating and using methods like storeNote, listNotes, showNote, and removeNote helps in organizing and reusing code.   
- Each method serves a specific purpose, e.g., storeNote adds a note, while showNote displays a specific note based on its number.   
- Organizing code into methods simplifies program maintenance and expansion.   

5. **Boundary Checks:**

To avoid errors, we verify that indices are within the bounds of the ArrayList before displaying or deleting notes. This is important to ensure the program does not try to access invalid positions in the list, thus avoiding exceptions such as IndexOutOfBoundsException.

Through these examples, we learned to:

- Create and manage objects.   
- Use dynamic data structures like ArrayList.   
- Apply wrapper classes for collections and automatic type conversion (autoboxing/unboxing).   
- Organize code into smaller, purpose-specific methods for improved functionality management.   
- These techniques and concepts strengthen the ability to write organized, scalable, and efficient code in Java.   


## [Week 5](./week5)

Βιβλιοθήκες - Αλφαριθμητικά - Μετατροπές - Διάβασμα δεδομένων

## [Week 6](./week6)

Κληρονομικότητα, Πολυμορφισμός & Υποσκέλιση

## [Week 7](./week7)

Κληρονομικότητα και Πολυμορφισμός (Χατζηγεωργίου)
