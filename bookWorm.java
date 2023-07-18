package midExamPrep;

import java.util.Arrays;
import java.util.Scanner;

public class bookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String startText = scanner.nextLine();
        StringBuilder sb = new StringBuilder(startText);

        int size = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[size][size];
        fillMatrix(matrix, scanner);

        //намиране на местоположението на играча
        int playerRow = -1;
        int playerCol = -1;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col].equals("P")) {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("end")) {
            boolean isOutside = false;

            int startRow = playerRow; // редът от който тръгва
            int startCol = playerCol; // колоната от която трръгва

            //преместване
            switch (command) {
                case "up":
                    playerRow--;
                    //matrix[1,1] -> matrix[0,1]
                    //проверка дали сме отвън

                    if (playerRow < 0) {
                        playerRow++;
                        isOutside = true;
                    }
                    break;
                case "down":
                    playerRow++;
                    //matrix[1,0] -> matrix[2,1]

                    if (playerRow >= size) {
                        playerRow--;
                        isOutside = true;
                    }
                    break;
                case "left":
                    playerCol--;
                    //matrix[1,1] -> matrix[1,0]
                    if (playerCol < 0) {
                        playerCol++;
                        isOutside = true;
                    }
                    break;
                case "right":
                    playerCol++;
                    //matrix[1,1] -> matrix[1,2]
                    if (playerCol >= size) {
                        playerCol--;
                        isOutside = true;
                    }
                    break;
            }

            // извършено движение - вътре или вън
            //проверка какво има на мястото кдето е отишъл
            if (!isOutside) {
                String currentText = matrix[playerRow][playerCol];
                if (!currentText.equals("-")) {
                    sb.append(currentText);
                }
                matrix[playerRow][playerCol] = "P"; // мястото на което се е преместил
                matrix[startRow][startCol] = "-"; // мястото от което си тръгнал

            } else {
                if (sb.length() > 0) {
                    //премахвам последната буква
                    sb.deleteCharAt(sb.length() - 1);
                }
            }

            command = scanner.nextLine();
        }

        System.out.println(sb);
        printMatrix(matrix);

    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("");
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
