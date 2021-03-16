// --== CS400 File Header Information ==--
// Name: Teddy Arasavelli
// Email: arasavelli@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

public interface SortedCollectionInterface<T extends Comparable<T>> extends Iterable<T> {

  public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

  public boolean contains(T data);

  public int size();

  public boolean isEmpty();
}
