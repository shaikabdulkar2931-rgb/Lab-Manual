import java.util.HashMap;
import java.util.Map;

class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class Main {

    public static <T extends Comparable<T>> T findMax(T[] arr) {
        T max = arr[0];
        for (T item : arr) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {


        Box<Integer> intBox = new Box<>(100);
        Box<String> strBox = new Box<>("Hello Generics");

        System.out.println("Integer Box Value: " + intBox.getValue());
        System.out.println("Type of stored item: " + intBox.getValue().getClass().getName());

        System.out.println();

        System.out.println("String Box Value: " + strBox.getValue());
        System.out.println("Type of stored item: " + strBox.getValue().getClass().getName());

        System.out.println();


        Map<String, Integer> studentMarks = new HashMap<>();
        studentMarks.put("Rahul", 88);

        Map<Integer, String> studentDept = new HashMap<>();
        studentDept.put(101, "CSE");

        System.out.println("---- Key-Value Pairs ----");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        for (Map.Entry<Integer, String> entry : studentDept.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println();


        Integer[] numbers = {23, 56, 89};
        String[] names = {"Rahul", "Sneha", "Amit"};
        Double[] marks = {85.6, 92.3, 78.4};

        System.out.println("Maximum Number: " + findMax(numbers));
        System.out.println("Maximum (Alphabetical): " + findMax(names));
        System.out.println("Maximum Marks: " + findMax(marks));
    }
}
