package midExamPrep;

import java.util.Scanner;

public class bee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];
        fillTheMatrix(matrix, scanner);

        //намиране на местоположението на играча
        int beeRow = -1;
        int beeCol = -1;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col].equals("B")) {
                    beeRow = row;
                    beeCol = col;
                }
            }
        }

        String command = scanner.nextLine();
        int flowers = 0;

        while (!command.equals("End")) {

            int[] updateCoordinates = updateBeeCordinates(command, beeRow, beeCol);
            int newRow = updateCoordinates[0];
            int newCol = updateCoordinates[1];

            matrix[beeRow][beeCol] = ".";
            //проверяваме дали е излязла от матрицата
            if (beeIsOutside(size, newRow, newCol)) {
                System.out.println("The bee got lost!");
                break;
            }

            //обновявае координатите на пчелата
            beeRow = newRow;
            beeCol = newCol;


            String currentPlace = matrix[beeRow][beeCol];
            if (currentPlace.equals("f")) {
                flowers++;
            } else if (currentPlace.equals("O")) {
                updateCoordinates = updateBeeCordinates(command, beeRow, beeCol);
                matrix[beeRow][beeCol] = ".";

                //обновявае координатите на пчелата
                beeRow = updateCoordinates[0];
                beeCol = updateCoordinates[1];
                currentPlace = matrix[beeRow][beeCol];
                if (currentPlace.equals("f")) {
                    flowers++;
                }
            }
            command = scanner.nextLine();
        }

        if (command.equals("End")) {
            matrix[beeRow][beeCol] = "B";
        }

        if (flowers >= 5) {
            System.out.println("Great job, the bee manage to pollinate " + flowers + " flowers!");
        } else {
            System.out.println("The bee couldn't pollinate the flowers, she needed " + (5 - flowers) + " flowers more");
        }

        printMatrix(matrix);
        System.out.println();
    }

    private static int[] updateBeeCordinates(String command, int beeRow, int beeCol) {
        int[] coordinates = new int[2];

        switch (command) {
            case "up":
                beeRow--;
                break;
            case "down":
                beeRow++;
                break;
            case "left":
                beeCol--;
                break;
            case "right":
                beeCol++;
                break;
        }

        coordinates[0] = beeRow;
        coordinates[1] = beeCol;

        return coordinates;
    }


    private static boolean beeIsOutside(int size, int beeRow, int beeCol) {
        return beeRow < 0 || beeCol < 0 || beeRow >= size || beeCol >= size;
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static void fillTheMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("");
        }
    }
}
