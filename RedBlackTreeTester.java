// --== CS400 File Header Information ==--
// Name: Teddy Arasavelli
// Email: arasavelli@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class that verifies the functionality of the RedBlackTree class
 * 
 * @author teddyarasavelli
 *
 */
public class RedBlackTreeTester {

  /**
   * testRedBlackTreeInsert() will test an insert whose parent's sibling is null or black (assumed
   * null nodes are black) and an insert that ONLY should require a left or right rotation. After
   * completing the rotation, will also check that the colors of the nodes are as they should be
   * 
   */
  @Test
  public void testRedBlackTreeInsertBlackSiblingsOneRotation() {

    RedBlackTree<Integer> testTree = new RedBlackTree<Integer>();

    testTree.insert(5);
    testTree.insert(6);
    testTree.insert(7);

    /*
     * Should go from a rightward pointing tree to one that is balanced with 6 as the root
     * 
     * 5, 6, 7 --> 6, 5, 7
     * 
     * The root should be black and its children red, as should be black as well as a right rotation
     * occurs, which will color
     */

    assertEquals(testTree.root.isBlack, true);
    assertEquals(testTree.root.leftChild.isBlack, false);
    assertEquals(testTree.root.rightChild.isBlack, false);

    assertEquals(testTree.root.toString(), "[6, 5, 7]");

    // Creating a similar situation to above except nodes will all be rightly linked

    testTree.root = null;

    testTree.insert(5);
    testTree.insert(4);
    testTree.insert(3);

    // Performing same checks as the other tree

    assertEquals(testTree.root.isBlack, true);
    assertEquals(testTree.root.leftChild.isBlack, false);
    assertEquals(testTree.root.rightChild.isBlack, false);

    assertEquals(testTree.root.toString(), "[4, 3, 5]");
  }

  /**
   * testRedBlackTreeInsertBlackSiblingsTwoRotations() will test an insert whose parent's sibling is
   * null or black (assumed null nodes are black) and an insert that requires both a left and a
   * right rotation. After completing the rotation, will also check that the colors of the nodes are
   * as they should be
   * 
   * 
   */
  @Test
  public void testRedBlackTreeInsertBlackSiblingsTwoRotations() {
    RedBlackTree<Integer> testTree = new RedBlackTree<Integer>();

    testTree.insert(5);
    testTree.insert(7);
    testTree.insert(6);

    // System.out.println(testTree.root.toString());

    // should end up with a tree that is balanced with 6 as the root and 5 and 7 as left and right
    // childen and 6 should be black
    // A right and then left rotation is performed

    assertEquals(testTree.root.isBlack, true);
    assertEquals(testTree.root.leftChild.isBlack, false);
    assertEquals(testTree.root.rightChild.isBlack, false);

    assertEquals(testTree.root.toString(), "[6, 5, 7]");

    // similar situation, but this tree would require a left, then right rotation

    testTree.root = null;

    testTree.insert(5);
    testTree.insert(2);
    testTree.insert(4);

    assertEquals(testTree.root.isBlack, true);
    assertEquals(testTree.root.leftChild.isBlack, false);
    assertEquals(testTree.root.rightChild.isBlack, false);

    assertEquals(testTree.root.toString(), "[4, 2, 5]");
  }

  /**
   * testRedBlackTreeInsertRedSiblings() test an insert whose parents sibling is red, will lead to
   * recolor operations
   * 
   */
  @Test
  public void testRedBlackTreeInsertRedSiblings() {

    RedBlackTree<Integer> testTree = new RedBlackTree<Integer>();
    testTree.insert(5);
    testTree.insert(6);
    testTree.insert(7);

    testTree.insert(8);

    // 8's parent should have a red sibling. To fix, will recolor grandparent node to red, parent
    // and its sibling to black and then recolor grandparent to black due to it being a root

    assertEquals(testTree.root.isBlack, true);
    assertEquals(testTree.root.leftChild.isBlack, true);
    assertEquals(testTree.root.rightChild.isBlack, true);
    assertEquals(testTree.root.rightChild.rightChild.isBlack, false);



    assertEquals(testTree.root.toString(), "[6, 5, 7, 8]");

  }

  /**
   * testRedBlackTreeInsertCascadingFixes() will test an insert whose initial fixes lead to the need
   * of cascading fixes due to the original fixes leading to further property violations
   * 
   */
  @Test
  public void testRedBlackTreeInsertCascadingFixes() {
    RedBlackTree<Integer> testTree = new RedBlackTree<Integer>();

    testTree.insert(7);
    testTree.insert(14);
    testTree.insert(18);
    testTree.insert(23);
    testTree.insert(1);
    testTree.insert(11);
    testTree.insert(20);
    testTree.insert(29);
    testTree.insert(25);
    // next insert of 27 will lead to a cascading fix being needed due a red property violation
    // being created during the fix
    testTree.insert(27);

    // checking relevant nodes to rotation and seeing if their colors are all correct
    assertEquals(testTree.root.isBlack, true);
    assertEquals(testTree.root.rightChild.isBlack, false);
    assertEquals(testTree.root.leftChild.isBlack, false);
    assertEquals(testTree.root.leftChild.rightChild.isBlack, true);
    assertEquals(testTree.root.rightChild.leftChild.isBlack, true);
    assertEquals(testTree.root.rightChild.rightChild.isBlack, true);
    assertEquals(testTree.root.rightChild.rightChild.leftChild.isBlack, false);

    assertEquals(testTree.root.toString(), "[20, 14, 25, 7, 18, 23, 29, 1, 11, 27]");

  }



}
