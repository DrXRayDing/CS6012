package assignment06;

import java.util.Arrays;
import java.util.List;

public class TreeDemo {

  public static void main(String[] args) {

    ExpressionTree t;

    // Expressions must have spaces seperating all all operands and operators

    // t = new ExpressionTree("3 + 5");
    // t = new ExpressionTree("1 + 2 * 3");
    // t = new ExpressionTree("2 * 5 + 4");
    // t = new ExpressionTree("2 * 5 + 4 / 3");
    t = new ExpressionTree("5 - 4 / 12 * 2 + 4");

//    System.out.println(t.evaluate());

//    t.writeDot("expressionTree.dot");

    // On a CADE machine, use the following command to convert expressionTree.dot to
    // a gif

    // First change directory to your project directory, where expressionTree.dot
    // resides, then run:
    // dot -Tgif expressionTree.dot -o expression.gif

    // Or in OSX, install the graphviz tool, then just double click on the .dot file

    BinarySearchTree<String> bst = new BinarySearchTree<>(); // Test of string
    List<String> str = Arrays.asList("xia", "er", "7", "xin", "Ray", "Raymond", "8", "Oh");
    bst.addAll(str);
    bst.writeDot("strBSTTest.dot");

    BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
    bst2.addAll(Arrays.asList(5, 3, 7, 10, 1, -55, 63, -1, 0, 4, 9));
    bst2.writeDot("intBSTTest.dot");
  }

}
