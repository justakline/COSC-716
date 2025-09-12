# Refactoring Assignment: Applying the Single Responsibility Principle

## Objective

The goal of this exercise is to refactor a single large class into several smaller, more manageable classes.
Apply the _Single Responsibility Principle (SRP)_ to improve the design of the provided Java code.

### The final program must produce the exact same output as the original.

# Initial Setup commands.

#### Note: anywhere that '<ins>999</ins>' appears in the following commands, replace it with your section number.

1. _Make sure your branch is up to date!_

    - `svn merge "^/999/trunk"`

2. _Commit the merged branch before continuing_
    - `svn commit -m "Sync-merge from trunk"`

## The Problem

The `OrderProcessor.java` file contains a class that handles multiple, distinct responsibilities:

1. **Order Management:**

    - It maintains an in-memory list of product orders (`addOrder`, `removeOrder`).

2. **Data Persistence:**

    - It simulates saving the orders to a database (`saveOrdersToDatabase`).

3. **Retail Invoice Generation:**

    - It formats and prints an invoice for retail customers (`printRetailInvoice`).

4. **Wholesale Invoice Generation:**
    - It formats and prints a different invoice for wholesale customers, which
      includes special discount logic (`printWholesalerInvoice`).

A class with so many unrelated responsibilities is difficult to maintain, test, and understand, and should be
refactored to improve its design and maintainability.



## Your Task

Your assignment is to refactor the `OrderProcessor` class and its related components.

1. _Identify Responsibilities:_

    - Carefully review the `OrderProcessor` class and identify the different jobs it
      performs.

2. _Create New Classes:_

    - Create new classes, each dedicated to a single responsibility.
      For example, you might create:
        - A class responsible for managing the collection of orders.
        - A class responsible for handling data persistence (e.g., an `OrderRepository`).
        - A class (or classes) responsible for generating and printing the different types of invoices.

3. _Delegate the Work:_

    - Modify the existing `OrderProcessor` class so that it delegates calls to your new, specialized
      classes instead of doing the work itself.

4. _Update the Testing Class:_

    - The `main` method in `MAIN.java` currently uses the old `OrderProcessor`
      class directly. You must update `MAIN.java` to work with your new, refactored code structure.

5. Create a file called `INFO.txt` (or `INFO.md`) with a short paragraph (just a few sentences) about your thought
   process:

   - Why did you choose the approach you did?
   - What alternatives did you consider?
   - What was the most challenging part of the assignment?

6. Commit your solution back to the repository and indicate in Blackboard the commit ID (revision number) of your
   commit.
    - E.g., Indicate "`Done: rXXX`" (where `XXX` is the revision number of your commit) as your Blackboard submission.

## Other Requirements

- _Commit your code regularly:_

    - You must commit your code regularly to ensure that you have a record of your work.
    - You should have 3-5 separate commits with a clear commit message for each.
    - (You can commit as often as you want, but be sure to have at least 3 separate commits illustrating your progress.)

- _Your code must compile:_

    - Your code must compile without any errors.

- _Behavior must not change:_

    - The output of running `MAIN.java` must be *identical* to the output of the original
      program.

- _Apply the Single Responsibility Principle._

    - Your primary goal is to ensure each class has one, and only one, reason to change.

- _Reduce Redundancy:_

    - For Example: The two `print...Invoice` methods contain very similar logic. Try to eliminate this duplication
      in your refactored code. You might consider using inheritance or composition to create a more general
      invoice-printing solution that can be specialized for retail and wholesale.

### Final Note:

- You do not need to implement any specific design patterns, but your final code should be cleaner, more modular,
      and easier to read.
