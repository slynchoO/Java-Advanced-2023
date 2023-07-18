package midExamPrep;

import java.util.*;

public class RubberDuckDebuggers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] programmersInput = scanner.nextLine().split("\\s+");
        String[] tasksInput = scanner.nextLine().split("\\s+");

        ArrayDeque<Integer> programmers = new ArrayDeque<>();
        Stack<Integer> tasks = new Stack<>();

        Map<String, Integer> ducks = new LinkedHashMap<>();

        ducks.put("Darth Vader Ducky", 0);
        ducks.put("Thor Ducky", 0);
        ducks.put("Big Blue Rubber Ducky", 0);
        ducks.put("Small Yellow Rubber Ducky", 0);

        Arrays.stream(programmersInput)
                .mapToInt(Integer::parseInt)
                .forEach(programmers::offer);

        Arrays.stream(tasksInput)
                .mapToInt(Integer::parseInt)
                .forEach(tasks::push);

        while (!programmers.isEmpty() && !tasks.isEmpty()) {
            int currentPRogramer = programmers.peek();
            int currentTask = tasks.peek();
            int time = currentTask * currentPRogramer;

            if (time >= 0 && time <= 60) {
                ducks.put("Darth Vader Ducky", ducks.get("Darth Vader Ducky") + 1);
                programmers.poll();
                tasks.pop();
            } else if (time > 60 && time <= 120) {
                ducks.put("Thor Ducky", ducks.get("Thor Ducky") + 1);
                programmers.poll();
                tasks.pop();
            } else if (time > 120 && time <= 180) {
                ducks.put("Big Blue Rubber Ducky", ducks.get("Big Blue Rubber Ducky") + 1);
                programmers.poll();
                tasks.pop();
            } else if (time > 180 && time <= 240) {
                ducks.put("Small Yellow Rubber Ducky", ducks.get("Small Yellow Rubber Ducky") + 1);
                programmers.poll();
                tasks.pop();
            } else {
                programmers.offerLast(programmers.poll());
                tasks.push(tasks.pop() - 2);
            }
        }

        System.out.println("Congratulations, all tasks have been completed! Rubber ducks rewarded:");
        for (Map.Entry<String, Integer> entry : ducks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
