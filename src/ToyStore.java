import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

class Toy {
    int id;
    String name;
    int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}

public class ToyStore {

    public static void main(String[] args) {
        // Создаем коллекцию PriorityQueue для хранения игрушек
        PriorityQueue<Toy> toysQueue = new PriorityQueue<>((t1, t2) -> t2.getFrequency() - t1.getFrequency());

        // Добавляем игрушки в очередь
        toysQueue.offer(new Toy(1, "1 2 конструктор", 2));
        toysQueue.offer(new Toy(2, "2 2 робот", 2));
        toysQueue.offer(new Toy(3, "3 6 кукла", 6));

        // Вызываем метод Get 10 раз и записываем результат в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int i = 0; i < 10; i++) {
                int toyId = getToy(toysQueue);
                writer.write(Integer.toString(toyId) + "\n");
            }
            writer.flush();
            System.out.println("Результаты записаны в файл output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения случайной игрушки в соответствии с её весом
    public static int getToy(PriorityQueue<Toy> toysQueue) {
        int totalFrequency = toysQueue.stream().mapToInt(Toy::getFrequency).sum();
        int randomNumber = (int) (Math.random() * totalFrequency) + 1;
        for (Toy toy : toysQueue) {
            if (randomNumber <= toy.getFrequency()) {
                return toy.getId();
            }
            randomNumber -= toy.getFrequency();
        }
        return -1; // Вернуть -1 в случае ошибки
    }
}
