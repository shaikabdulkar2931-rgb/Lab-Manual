class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double calculateArea() {
        return length * width;
    }
}

class Triangle {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public double calculateArea() {
        return 0.5 * base * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(3, 8);

        System.out.println("----- Shape Area Calculator -----");

        System.out.println("\nCircle:");
        System.out.println("Radius = " + circle.getRadius());
        System.out.println("Area of Circle = " + circle.calculateArea());

        System.out.println("\nRectangle:");
        System.out.println("Length = " + rectangle.getLength() + ", Width = " + rectangle.getWidth());
        System.out.println("Area of Rectangle = " + rectangle.calculateArea());

        System.out.println("\nTriangle:");
        System.out.println("Base = " + triangle.getBase() + ", Height = " + triangle.getHeight());
        System.out.println("Area of Triangle = " + triangle.calculateArea());
    }
}
