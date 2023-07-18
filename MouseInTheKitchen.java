package midExamPrep;

import java.util.Scanner;

public class MouseInTheKitchen {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split(",");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        String[][] matrix = new String[rows][cols];
        int mouseRow = -1;
        int mouseCol = -1;

        for (int row = 0; row < rows; row++) {
            String[] line = scanner.nextLine().split("");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = line[col];
                if (matrix[row][col].equals("M")) {
                    mouseRow = row;
                    mouseCol = col;
                }
            }
        }

        String command = scanner.nextLine();
        while (!command.equals("danger")) {
            int[] newCoordinates = updateMouseCoordinates(command, mouseRow, mouseCol);
            int newRow = newCoordinates[0];
            int newCol = newCoordinates[1];

            if (isOutside(rows, cols, newRow, newCol)) {
                System.out.println("No more cheese for tonight!");
                break;
            }

            String newPosition = matrix[newRow][newCol];

            if (newPosition.equals("C")) {
                matrix[newRow][newCol] = "*";
                boolean cheeseRemaining = false;
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        if (matrix[row][col].equals("C")) {
                            cheeseRemaining = true;
                            break;
                        }
                    }
                    if (cheeseRemaining) {
                        break;
                    }
                }
                if (!cheeseRemaining) {
                    matrix[mouseRow][mouseCol] = "*";
                    matrix[newRow][newCol] = "M";
                    System.out.println("Happy mouse! All the cheese is eaten, good night!");
                    printMatrix(matrix);
                    return;
                }
            } else if (newPosition.equals("T")) {
                matrix[mouseRow][mouseCol] = "*";
                matrix[newRow][newCol] = "M";
                System.out.println("Mouse is trapped!");
                printMatrix(matrix);
                return;
            } else if (newPosition.equals("@")) {
                command = scanner.nextLine();
                continue;
            }

            matrix[mouseRow][mouseCol] = "*";
            matrix[newRow][newCol] = "M";
            mouseRow = newRow;
            mouseCol = newCol;
            command = scanner.nextLine();
        }

        System.out.println("Mouse will come back later!");
        printMatrix(matrix);
    }

    private static boolean isOutside(int rows, int cols, int newRow, int newCol) {
        return newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols;
    }

    private static int[] updateMouseCoordinates(String command, int mouseRow, int mouseCol) {
        int[] coordinates = new int[2];

        switch (command) {
            case "up":
                mouseRow--;
                break;
            case "down":
                mouseRow++;
                break;
            case "right":
                mouseCol++;
                break;
            case "left":
                mouseCol--;
                break;
        }

        coordinates[0] = mouseRow;
        coordinates[1] = mouseCol;

        return coordinates;
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}

