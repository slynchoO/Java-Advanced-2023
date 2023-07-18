package midExamPrep;

import java.util.*;
import java.util.stream.Collectors;

public class datingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String maleInput = scanner.nextLine(); // last
        String femaleInput = scanner.nextLine(); // first

        Stack<Integer> males = new Stack<>();
        Arrays.stream(maleInput.split("\\s+"))
                .map(Integer::parseInt).forEach(males::push);

        Queue<Integer> females = Arrays.stream(femaleInput.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int matches = 0;

        while (!males.isEmpty() && !females.isEmpty()) {
            int male = males.peek();
            int female = females.peek();

            if (male <= 0) {
                males.pop();
                continue;
            } else if (female <= 0) {
                females.poll();
                continue;
            }

            if (male % 25 == 0) {
                males.pop();
                males.pop();
                continue;
            } else if (female % 25 == 0) {
                females.poll();
                females.poll();
                continue;
            }

            if (male == female) {
                matches++;
                males.pop();
                females.poll();
            } else {
                females.poll();
                males.push(males.pop() - 2);
            }

        }

        System.out.printf("Matches: %d%n", matches);

        if (males.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: ");
            System.out.println(males.toString().replace("[", "").replace("]", ""));
        }

        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: ");
            System.out.println(females.toString().replace("[", "").replace("]", ""));
        }

    }
}
