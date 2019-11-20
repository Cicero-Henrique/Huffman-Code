package Huffman_Java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class HuffmanCode
{

    public String getMessage() throws FileNotFoundException, IOException
    {
        FileReader fileReader = new FileReader("fileMessage.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String text = "";

        while ((line = bufferedReader.readLine()) != null) {
            text += line;
        }
        bufferedReader.close();
        return text;
    }

    public ArrayList bubbleSortTree(ArrayList<Node> array)                      // Sort the array of nodes
    {
        Node temp;
        for (int i = 0; i < array.size(); i++)
        {
            for (int j = 1; j < (array.size() - i); j++)
            {
                if (array.get(j-1).getNum() < array.get(j).getNum())
                {
                    temp = array.get(j-1);
                    array.set(j-1, array.get(j));
                    array.set(j, temp);
                }
            }
        }
        return array;
    }

    public ArrayList generateHashmap(String key)
    {
        String letter;
        Map<String, Node> map = new HashMap<>();

        for (int i = 0; i < key.length(); i++)
        {
            letter = Character.toString(key.charAt(i));
            if (map.get(letter) == null)                                        // If the letter isn't in the array, it will be inputed 
            {
                Node n = new Node();
                n.setLetter(letter);
                n.setNum(1);
                n.setAux(0);
                map.put(letter, n);
            }
            else
                map.get(letter).setNum(map.get(letter).getNum() + 1);
        }
        Node[] test = map.values().toArray(new Node[map.size()]);
        ArrayList<Node> arrayList = new ArrayList<Node>(Arrays.asList(test));

        return arrayList;
    }

    public ArrayList generateArray() throws IOException
    {
        String message = getMessage();                                          // Getting the message of the file

        ArrayList<Node> bigArray = new ArrayList();
        QuickSort qs = new QuickSort();

        bigArray = generateHashmap(message);                                    // Generate the array using hashmap
        qs.sort(bigArray, 0, bigArray.size() -1);                               // Sort the array of letters

        return bigArray;
    }

    public Node createRoot(Node r, Node l1, Node l2)
    {
        r.setLetter(l1.getLetter() + l2.getLetter());
        r.setNum(l1.getNum() + l2.getNum());
        l1.setAux(0);
        l2.setAux(1);
        r.setLeft(l1);
        r.setRight(l2);
        return r;
    }

    public static void generateBinaries(Node n, String side, String sc)
    {
        if(n!= null)
        {
            n.setBinarie((n.getBinarie() +sc + side).trim());
            generateBinaries(n.getLeft(), "0", n.getBinarie());
            generateBinaries(n.getRight(), "1", n.getBinarie());
        }
    }

    public ArrayList<Node> generateTree(ArrayList<Node> tree)
    {
        Node right = new Node();
        Node left = new Node();
        Node root = new Node();
        left = tree.get(tree.size()-1);                                         //get the Last element of the tree, so the element with less frequency
        right = tree.get(tree.size()-2);                                        //get the second element with less frequency in the tree
        root = createRoot(root, left, right);                                   //Select the two elements with lower frequencies in the tree and generate a new element, a mix between the two.
        tree.remove(tree.size()-1);                                             //remove the two last elements of the tree
        tree.remove(tree.size()-1);
        tree.add(root);                                                         //add the mixed element
        tree = bubbleSortTree(tree);                                            //sort the tree

        return tree;
    }

    public void printArray(Node n)
    {
        if(n == null)
            return;

        printArray(n.getLeft());
        printArray(n.getRight());
        System.out.println(n.getLetter() + " - " + n.getNum() + " - " + n.getBinarie());
    }

    public ArrayList binaryTree(ArrayList<Node> newArray)
    {
        Node root =  new Node();

        while(newArray.size() > 2)
            newArray = generateTree(newArray);                                  //Design the tree with each node like root pointing to node left and right, until left just two nodes in the tree 

        root.setLetter(newArray.get(0).getLetter() +
                newArray.get(1).getLetter());                                   //Create the main node of the tree pointing to  the other two that were already created
        root.setNum(newArray.get(0).getNum() + newArray.get(1).getNum());
        root.setLeft(newArray.get(0));
        root.setRight(newArray.get(1));
        newArray.add(root);
        newArray.remove(0);
        newArray.remove(0);
        generateBinaries(newArray.get(0), "", "");                              //Traverse the tree setting the binary number of each node

        printArray(newArray.get(0));

        return newArray;
    }

    public String getChar(Node n, char letter)                                  //Recursion: stops only when to find the letter in the tree and return your correspondent binary.
    {
        String aux = null;
        if(n != null && aux == null)
        {
            if(n.getLetter().equals(Character.toString(letter)))
            {
                aux =  n.getBinarie();
                return aux;
            }
            else
            {
                aux = getChar(n.getLeft(), letter);
                if(aux == null)
                    aux = getChar(n.getRight(), letter);
            }
        }
        return aux;
    }

    public String writeCodeMessage(ArrayList<Node> array) throws IOException    //Translate the original message to binaries
    {
        String message = getMessage(), codeMessage = "";
        for(int i = 0; i < message.length(); i++)                               //Get char by char of the original message and change to the binary correspondent
            codeMessage = codeMessage + " " +
                    getChar(array.get(0), message.charAt(i));

        System.out.println("code message:" + codeMessage);
        codeMessage = codeMessage.trim();
        return codeMessage;
    }

    public void writeInFile(String codeMessage)                                 //Write the binary message on the file.
            throws FileNotFoundException, IOException
    {
        OutputStream os = new FileOutputStream("codeMessage.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(codeMessage);
        bw.newLine();
        bw.close();
    }

}