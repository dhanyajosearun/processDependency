# processDependency
Process Dependency Test

A) PRE-REQUISITE:
java version "1.8.0_202"

B) DESIGN:
System is designed in 3 parts
1.	Access the Processes and Dependencies
2.	Check for Cyclic Dependency
3.	Print Process Order by checking Dependencies

1. Access the Processes and Dependencies

Enter List of Process in the mentioned process format: (a b c d FINISH). Only accepts 50 processes as mentioned by specification. And word “FINISH” to mark as end of Input
Enter List of Dependencies in the mentioned dependency format: a=>b b=>c FINISH). In the event of encountering any dependency with a process that is missing in process list, then need to start over.

2. Check for Cyclic Dependency

If no dependencies the print the process list as it is. If dependency list is not empty then checking the list for any possible cyclic dependency. In the event of cyclic dependency Exception is thrown with Cyclic dependency list. 

3. Print Process Order by checking Dependencies

Assuming Process without any dependencies executing first, process order is printed as per the specification.

C) COMPILE, RUN AND TEST

1.	Copy the dependentTaskOrder.java file.
2.	Open Command Prompt and Navigate to java file location using “cd <filepath>” command.
3.	Compile file using “javac dependentTaskOrder.java”.
4.	Run java file using “java dependentTaskOrder”.
5.	Enter Process list in the mentioned format. Ex:”a b c d FINISH”.
6.	Enter Dependency list in mentioned format: Ex:” a=>b b=>c FINISH”.





