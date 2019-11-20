package Huffman_Java;

public class Node
{
    private int aux = 2, num;
    private Node right = null, left = null;
    private String binarie = "", letter;

    public int getAux() {return aux;}
    public Node getLeft() {return left;}
    public Node getRight() {return right;}
    public String getBinarie() {return binarie;}
    public int getNum() {return num;}
    public String getLetter() {return letter;}

    public void setNum(int num) {this.num = num;}
    public void setLetter(String letter) {this.letter = letter;}
    public void setBinarie(String binarie) {this.binarie = binarie;}
    public void setLeft(Node left) {this.left = left;}
    public void setRight(Node right) {this.right = right;}
    public void setAux(int aux) {this.aux = aux;}

}
