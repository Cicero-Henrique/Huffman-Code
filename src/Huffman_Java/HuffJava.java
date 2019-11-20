
package Huffman_Java;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Cícero
 */
public class HuffJava
{
    public static void main(String[] args) throws IOException
    {
        HuffmanCode hc = new HuffmanCode();
        ArrayList<Node> array = new ArrayList();

        array = hc.generateArray();                                             // Creating the array of the characters
        array = hc.binaryTree(array);                                           // Converting the array to binary tree
        String message = hc.writeCodeMessage(array);                            // Writing the code message in the file
        hc.writeInFile(message);
    }

}
