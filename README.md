# CaseStudy_2_ATM

This is a menu driven program.

Handling the input and output is as follows:
1. First the admin is prompted to set a password for the ATM and then the total cash inside it.
2. Then the customer's details are taken as input. Note that the machine doesn't start unless atleast one customer is present.
3. Then the admin is asked to set the ATM in working mode (from where the actual system starts).
4. The functionalities of the admin are
        a. Shutdown the System
        b. Add the cash
        c. Show the current cash
5. Now the account number and pin are taken as input.
6. If the account number is in database and the pin is matched then the functionalities of a customer are shown else the control flows to point 5.
7. The functionalities of the customer are 
        a. view my balance
        b. deposit funds
        c. withdraw amount : If the withdrawl amount is greater than the cash inside the ATM or the balance of the customer then the process is halted with an error message and 
        continued to the next step.
8. Then the customer is prompted to logout or continue.


The program identifies the user account and the ATM as objects and is constructed based on the object oriented concept. The whole program is handled by the ATM class and it
identifies the functionalities of the admin and customer as its own functionalities.
