/**
 * Name: Eric Truong
 * Date: October 14, 2019
 * Description: Given a text file of a grid, find the number of area of o's and the number of o's in each area
 */

import java.io.*;
import java.util.Scanner;

public class GridAreaCounter {

    public static void main (String[] args){

        //create array
        char [][] array = new char[10][10];
        System.out.print("Enter Grid number: ");

        //get name of file
        int gridNumber = CheckInput.getIntRange(1, 4);
        String fileName = "grid"+gridNumber;

        array = readFromFile(array, fileName);

        displayArray(array);

        System.out.println("");

        traverseGrid(array);

    }

    /**
     * Read the selected file and put it into a 10x10 grid
     * @param array     10x10 grid
     * @param fileName  user's selected file
     * @return          return 10x10 grid with characters from the file
     */
    public static char[][] readFromFile(char[][] array, String fileName){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                if (i == 0 || i == 9 || j == 0 || j == 9){
                    array[i][j] = '#';
                }
            }
        }
        int i = 1;

        try {
            Scanner read = new Scanner(new File(fileName + ".txt"));
            do{

                String line = read.nextLine();

                int begin = 0;
                for(int j = 1; j < array.length - 1; j++){
                    array[i][j] = line.charAt(begin);
                    begin++;
                }
                i++;

            }
            while (read.hasNext());

            read.close();

        }

        catch(FileNotFoundException fnf){
            System.out.println("File not found");
        }
        return array;

    }

    /**
     * Displays the array with the characters in a 8x8 grid
     * @param array 10x10 array with characters from grid file
     */
    public static void displayArray(char[][] array){
        for(int i = 1; i < array.length - 1; i++){
            System.out.println("");
            for(int j = 1; j < array.length - 1; j++){
                System.out.print(array[i][j]);
            }
        }
        System.out.println("");
    }

    /**
     * Scan the grid for o's
     * @param array array of the grid
     */
    public static void traverseGrid(char[][] array){
        int countArea = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                if(array[i][j] == 'o'){
                    int count = counter(array, i, j);
                    countArea++;
                    System.out.println("Area "+countArea+": "+count);

                }
            }
        }
    }


    /**
     * Count the number of o's in a area
     * @param array array of the grid
     * @param i     index of row
     * @param j     index of column
     * @return      return number of o's in an array
     */
    public static int counter(char[][] array, int i, int j){
        int count = 1;
        array[i][j] = 'x';

        if (array[i + 1][j] == 'o') {
            count += counter(array, i + 1, j);
        }
        if (array[i][j + 1] == 'o') {
            count += counter(array, i, j + 1);
        }

        if (array[i][j - 1] == 'o') {
            count += counter(array, i, j - 1);
        }
        if (array[i - 1][j] == 'o') {
            count += counter(array, i - 1, j);
        }
        return count;
    }
}
