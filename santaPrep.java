package midExamPrep;

import java.util.*;
import java.util.stream.Collectors;

public class santaPrep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String materialsInput = scanner.nextLine(); //ще ни трябва последният елемент и ползваме stack
        String magicInput = scanner.nextLine();

        Stack<Integer> materials = new Stack<>();
        Arrays.stream(materialsInput.split("\\s+"))
                .map(Integer::parseInt).forEach(materials::push);

        Queue<Integer> magics = Arrays.stream((magicInput.split("\\s+")))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        //играчки
        Map<String, Integer> toys = new TreeMap<>(); // по азбучен ред
        toys.put("Doll", 0);
        toys.put("Wooden train", 0);
        toys.put("Teddy bear", 0);
        toys.put("Bicycle", 0);

        while (!materials.isEmpty() && !magics.isEmpty()) {
            //първият материал // поглеждам без да вадя
            int material = materials.peek();
            int magic = magics.peek();

            int total = material * magic;

            //проверка
            if (total == 150) {
                materials.pop();
                magics.poll();
                toys.put("Doll", toys.get("Doll") + 1);
            } else if (total == 250) {
                materials.pop();
                magics.poll();
                toys.put("Wooden train", toys.get("Wooden train") + 1);
            } else if (total == 300) {
                materials.pop();
                magics.poll();
                toys.put("Teddy bear", toys.get("Teddy bear") + 1);
            } else if (total == 400) {
                materials.pop();
                magics.poll();
                toys.put("Bicycle", toys.get("Bicycle") + 1);
            } else if (total < 0) {
                int sum = material + magic;
                materials.pop();
                magics.poll();
                materials.push(sum);
            } else if (total > 0) {
                magics.poll();
                materials.push(materials.pop() + 15);
            } else {
                if (material == 0) {
                    materials.pop();
                }
                if (magic == 0) {
                    magics.poll();
                }
            }
        }

        boolean isDollAndTrain = toys.get("Doll") > 0 && toys.get("Wooden train") > 0;
        boolean isBearAndBicycle = toys.get("Teddy bear") > 0 && toys.get("Bicycle") > 0;

        if (isDollAndTrain || isBearAndBicycle) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }

        if (!materials.isEmpty()) {
            System.out.print("Materials left: ");
            Collections.reverse(materials);
            System.out.println(materials.toString().replace("[", "").replace("]", ""));
        }

        if (!magics.isEmpty()) {
            System.out.print("Magic left: ");
            System.out.println(magics.toString().replace("[", "").replace("]", ""));
        }

        for (Map.Entry<String, Integer> entry : toys.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
