// Sincere Banks

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.lang.*;

public class SneakyQueens
{
    public static int baseConversion(int[] letterNum)
    {
      int count = 0;
      int sum = 0;

      // Base conversion math

      for (int i = letterNum.length - 1; i >= 0; i--)
      {
        int base = (int)Math.pow(26, i);
        letterNum[count] = base * letterNum[count];
        sum = sum + letterNum[count];
        count++;
      }
      return sum;
    }

    public static int[] splitInput(String input)
    {

      // Split into String -> CharArray and int for numerical values

      String[] stringy = input.split("(?<=\\D)(?=\\d)");
      char[] letters = stringy[0].toCharArray();
      int[] letterNum = new int[letters.length];
      int numbers = Integer.parseInt(stringy[1]);

      // Numerical value associated with letter

      for (int i = 0; i < letters.length; i++)
      {
        int position = letters[i] - 'a' + 1;
        letterNum[i] = position;
      }

      // Numerical value associated with alphabetical string base 26

        int baseTwoSixSum = baseConversion(letterNum);
        int[] coordinates = new int[2];

        coordinates[0] = baseTwoSixSum;
        coordinates[1] = numbers;

        // Returns array with two values x and y position

        return coordinates;
    }

    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {

      int[][] chessboard = new int[2][boardSize + 1];
      int[] yintercept = new int[boardSize * 2];
      int size = coordinateStrings.size(), trueOrFalse = 1, flag = 1;

      for (int i = 0; i < size; i++)
      {
        int[] coordinates = splitInput(coordinateStrings.get(i));

        // Coordinate[0] is letter value (horizontial axis/columns)
        // Coordinate[1] is numerical value (vertical axis/rows)

        // chessboard[0][60000] is columns
        // chessBoard[1][60000] is rows

        for (int j = 0; j < 2; j++)
        {
          if ((chessboard[j][coordinates[j]] == 0) && (chessboard[j][coordinates[j]] == 0))
            chessboard[j][coordinates[j]] = flag;
          else
            trueOrFalse = 0;
        }

        // y=mx+b --> y=x+b --> b=y-x | Checks to see if y intercept is already filled

        if (yintercept[coordinates[1] - coordinates[0] + boardSize] == 0)
          yintercept[coordinates[1] - coordinates[0] + boardSize] = flag;
        else
          trueOrFalse = 0;
      }

      if (trueOrFalse == 1)
        return true;
      else
        return false;
    }

    public static double difficultyRating()
    {
      double difficulty = 3;
      return difficulty;
    }

    public static double hoursSpent()
    {
      double hours = 9;
      return hours;
    }

    public static void main (String[] args)
    {

      int boardSize = 60000;
      ArrayList<String> coordinateStrings = new ArrayList<String>();

      File file = new File("TestCase06-input.txt");

      try
      {
        Scanner sc = new Scanner(file);

        while (sc.hasNext())
          coordinateStrings.add(sc.next());
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }

      //coordinateStrings.add("abc123");
      //coordinateStrings.add("abd124");
      //coordinateStrings.add("abe125");

      boolean torf = allTheQueensAreSafe(coordinateStrings, boardSize);

      if (torf == true)
        System.out.println("Queens are safe!");
      else if (torf == false)
        System.out.println("Queens are NOT safe!");
    }
}
