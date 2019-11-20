package Huffman_Java;

import java.util.ArrayList;

public class QuickSort
{
    int partition(ArrayList<Node> array, int low, int high)
    {
        Node pivot = array.get(high);
        int i = (low - 1);                                                      // index of smaller element
        for (int j = low; j < high; j++)
        {
            if (array.get(j).getNum() < pivot.getNum())
            {
                i++;
                Node temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }

        Node temp = array.get(i + 1);
        array.set(i+1, array.get(high));
        array.set(high, temp);

        return i + 1;
    }

    void sort(ArrayList<Node> array, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(array, low, high);
            sort(array, low, pi - 1);
            sort(array, pi + 1, high);
        }
    }

    public void printArray(ArrayList<Node> arr)
    {
        int n = arr.size();
        for (int i = 0; i < n; ++i)
            System.out.print(arr.get(i).getLetter() + "-" + arr.get(i).getNum() + "  ");

    }
}
