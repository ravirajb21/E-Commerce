## Table per class
1 table for each class. Each class's table to have all the attributes,
including the attributes inherited from parent

## Single class
Create only 1 table. Will have attributes across all of the entities

## Joined Table
1 Table for each class. Each class's table to have only its own attributes,
attributes of parent are accessed via a foreign key to the parent class.

## Mapped SuperClass

Like Table Per class. Only difference is -> No table for parent class.
This solution is valid only when parent class is abstract
