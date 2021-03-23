run: compile
	java Frontend

compile: *.class

Frontend.class: Frontend.java
	javac Frontend.java

Backend.class: Backend.java
	javac Backend.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

DictionaryDataReader.class: DictionaryDataReader.java
	javac DictionaryDataReader.java

Word.class: Word.java
	javac Word.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac CortedCollectionInterface.java

TestBackend.class: TestBackend.java
	javac TestBackend.java

TestFrontend.class: TestFrontend.java
	javac TestFrontend.java

DataWranglerTests.class: DataWranglerTests.java
	javac DataWranglerTests.java


test: testData testBackend testFrontend

testFrontend: compile
	java TestFrontend
	
testBackend: compile
	java TestBackend	

testData: compile
	java DataWranglerTests

clean:
	$(RM) *.class
