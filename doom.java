package midExamPrep;

import java.util.*;
import java.util.stream.Collectors;

public class doom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String toolsInput = scanner.nextLine(); //take the first tool
        String substancesInput = scanner.nextLine(); //the last substance
        //Multiply the values and check the result.
        String challengesInput = scanner.nextLine();

        Stack<Integer> substances = new Stack<>();
        Arrays.stream(substancesInput.split("\\s+"))
                .map(Integer::parseInt).forEach(substances::push);

        Queue<Integer> tools = Arrays.stream(toolsInput.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Queue<Integer> challenges = Arrays.stream(challengesInput.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (!substances.isEmpty() && !tools.isEmpty()) {
            int currentSubs = substances.peek();
            int currentTool = tools.peek();
            int total = currentTool * currentSubs;

            if (challenges.contains(total)) {
                challenges.remove(total);
                substances.pop();
                tools.poll();
            } else {
                int newTool = currentTool + 1;
                tools.poll();
                tools.offer(newTool);

                int newSubs = currentSubs - 1;
                if (newSubs <= 0) {
                    substances.pop();
                } else {
                    substances.pop();
                    substances.push(newSubs);
                }
            }
        }
        if (challenges.isEmpty()) {
            System.out.println("Harry found an ostracon, which is dated to the 6th century BCE.");
        } else if (substances.isEmpty() || tools.isEmpty()) {
            System.out.println("Harry is lost in the temple. Oblivion awaits him.");
        }

        if (!tools.isEmpty()) {
            System.out.println("Tools: " + tools.toString().replace("[", "").replace("]", ""));
        }
        if (!substances.isEmpty()) {
            System.out.println("Substances: " + substances.toString().replace("[", "").replace("]", ""));
        }
        if (!challenges.isEmpty()) {
            System.out.println("Challenges: " + challenges.toString().replace("[", "").replace("]", ""));
        }
    }
}
