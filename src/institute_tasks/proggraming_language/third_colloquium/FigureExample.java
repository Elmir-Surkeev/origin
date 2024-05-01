package institute_tasks.proggraming_language.third_colloquium;

import java.util.ArrayList;
import java.lang.Exception;
import java.util.Scanner;
class Shape {
    private double volume;

    public Shape(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }
}

class SolidOfRevolution extends Shape {
    private double radius;

    public SolidOfRevolution(double volume, double radius) {
        super(volume);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

class Ball extends SolidOfRevolution { // конкретный класс
    public Ball(double radius) {
        super(Math.PI * Math.pow(radius, 3) * 4 / 3, radius);
    }
}

class Cylinder extends SolidOfRevolution { // конкретный класс
    private double height;

    public Cylinder(double radius, double height) {
        super(Math.PI * radius * radius * height, radius);
        this.height = height;
    }
}

class Pyramid extends Shape{
    private double height;
    private double s; // площадь основания

    public Pyramid(double height, double s) {
        super(height * s * 4 / 3);
        this.height = height;
        this.s = s;
    }
}


class Box extends Shape {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private double available;

    public Box(double available) {
        super(available);
        this.available = available;
    }

    public boolean add(Shape shape) {
        if (available >= shape.getVolume()) {
            shapes.add(shape);
            available -= shape.getVolume();
            return true;
        } else {
            return false;
        }
    }
}

public class FigureExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите объем коробки: ");
        double boxVolume = scanner.nextDouble();
        Box box = new Box(boxVolume);

        System.out.println("Введите радиус шара: ");
        double ballRadius = scanner.nextDouble();
        Ball ball = new Ball(ballRadius);

        System.out.println("Введите радиус и высоту цилиндра: ");
        double cylinderRadius = scanner.nextDouble();
        double cylinderHeight = scanner.nextDouble();
        Cylinder cylinder = new Cylinder(cylinderRadius, cylinderHeight);

        System.out.println("Введите высоту и площадь основания пирамиды: ");
        double pyramidHeight = scanner.nextDouble();
        double pyramidBaseArea = scanner.nextDouble();
        Pyramid pyramid = new Pyramid(pyramidHeight, pyramidBaseArea);

        if (box.add(ball)) {
            System.out.println("Шар поместился в коробку");
        } else {
            System.out.println("Шар не поместился в коробку");
        }

        if (box.add(cylinder)) {
            System.out.println("Цилиндр поместился в коробку");
        } else {
            System.out.println("Цилиндр не поместился в коробку");
        }

        if (box.add(pyramid)) {
            System.out.println("Пирамида поместилась в коробку");
        } else {
            System.out.println("Пирамида не поместилась в коробку");
        }
    }
}