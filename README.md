# README

## Contents
This project contains a CLI implementation of a POS system for a tool rental company.

When running it, the program will ask you for inputs regarding the type of tool you want to rent, as well as for how long, if you want to apply a discount, and what the date you're checking out the tool is.

The program will use those inputs to create a rental agreement that if you decide to print out, will give you the information you gave the program as well as details about the tool itself, when it is due, how many days you are actually being charged for the tool, the amount deducted from your total due to your discount, and what the final charge is after applying the discount you enter will be.
This program has error handling, and will either ask you to re-enter the information if it is in the incorrect format, or give you a custom exception if you give bad data.
