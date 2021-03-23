IntegrationManager README for Project Two (CS400 @ UW Madison)
==============================================================

Name of IntegrationManager: Yash Agrawal
@wisc.edu Email of IntegrationManager: yagrawal3@wisc.edu
Group: AB
Team: Red

Complete List of Files:
-----------------------
<List all files that are a part of this project along with the name of the team
 member(s) who were most responsible for implementing the computation within
 each of these files.>

Frontend.java: Nijae King
FrontEndDeveloper_README.txt: Nijae King
FrontEndDeveloperTests.java: Nijae King
Backend.java: Anthony Reis
BackEndDeveloper_README.txt: Anthony Reis
BackendInterface.java: Anthony Reis
TestBackend.java: Anthony Reis
SortedCollectionInterface.java: Theodore Arasavelli
DictionaryDataReader.java: Theodore Arasavelli
DataWrangler_README.txt: Theodore Arasavelli
Word.java: Theodore Arasavelli
RedBlackTree.java: Theodore Arasavelli
DataWranglerTests.java: Theodore Arasavelli
Makefile: Yash Agrawal
IntegrationManager_README.txt: Yash Agrawal
FinalDictionary.txt: Yash Agrawal, Theodore Arasavelli, Anthony Reis, Nijae King
smallDictionary.txt: Theodore Arasavelli

Instructions to Build, Run and Test your Project: 
-------------------------------------------------
<Describe here any instructions that will be needed by course staff to build,
 run, and test this project.>
The Makefile will allow all the compilation and execution needed to be done on the program.
Requires: junit5.jar
make (default): Will compile all files that are not up to date and then execute the program
make run: Same as default 
make test: compiles any needed files and runs all the tests for this program (the execution time for tests can be very long)
make compile: compiles all .java files whose .class file is not up to date
make testFrontend: compiles and runs frontend junit5 tests (the execution time for tests can be very long)
make testBackend: compiles and runs backend junit5 tests (the execution time for tests can be very long)
make testData: compiles and runs datawrangler junit5 tests (the execution time for tests can be very long)
make clean: Removes all .class files

Program: When the program is run we will be greeted with a word of the day along with 4 options:
1) Addition(A or a): When user inputs 'a' the user will be able to input a word. To do so the user will input the word followed by its definition, yes if there is an additional definition no if no additional definition, then finally the origin. If word already exists in dictionary, the program will output an exception that the word is already in the dictionary and exit out of the program.
2) Search(S or s): Allows user to input a word to search and gets its definition. When a word is not found program gives user a few suggestions on close matches to the word inputed. It then asks user whether they want to add the word(that was not found). If yes program switches to addition mode, if no program continues with search mode.
3) Random(R or r): Random mode outputs a random word to the user
4) Exit(E or e): Exits out of the program


Team Member Contributions: N/A
--------------------------
<List the efforts of any members on your team that went above the team's
 expectations for their contributions to this project.  Also identify any
 ways that team members failed to meet the expectations of the group, for
 example: missed deadlines, lack of communication, lack of effort, etc.>

Signature: Yash Agrawal
----------
<Type out your full name here to certify that to the best of your
 understanding, the contents of this submission represent the efforts of your
 team members. If you are aware of any exceptions to this, please describe them here below your name.>