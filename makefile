default: run

run: compile
	java Frontend

compile: Frontend.class Backend.class BackendInterface.class DictionaryDataReader.class SortedCollectionInterface.class Word.class RedBlackTree.class TestBackend.class TestFrontend.class DataWranglerTests.class

Frontend.class: Frontend.java
	javac Frontend.java

Backend.class: Backend.java
	javac Backend.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

DictionaryDataReader.class: DictionaryDataReader.java
	javac DictionaryDataReader.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac CortedCollectionInterface.java

Word.class: Word.java
	javac Word.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

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
