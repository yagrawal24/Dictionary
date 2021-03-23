default: run

run: compile
	java Frontend

compile: Frontend.class Backend.class BackendInterface.class DictionaryDataReader.class SortedCollectionInterface.class Word.class RedBlackTree.class TestBackend.class FrontEndDeveloperTests.class DataWranglerTests.class

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
	javac -cp .:junit5.jar TestBackend.java -Xlint	

FrontEndDeveloperTests.class: FrontEndDeveloperTests.java
	javac -cp .:junit5.jar FrontEndDeveloperTests.java -Xlint	

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

runTests: compile
	java -jar junit5.jar --class-path . --scan-classpath

clean:
	$(RM) *.class
