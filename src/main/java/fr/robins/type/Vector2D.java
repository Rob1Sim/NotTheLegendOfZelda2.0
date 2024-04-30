package fr.robins.type;

import java.util.Objects;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D() {
        x = 0;
        y = 0;
    }
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIntX(){
        return (int)x;
    }
    public int getIntY(){
        return (int)y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(x, vector2D.x) == 0 && Double.compare(y, vector2D.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2D cloneVector()  {
        return new Vector2D(x, y);
    }

    public Vector2D add(Vector2D vector2D) {
        return new Vector2D(x + vector2D.x, y + vector2D.y);
    }

    public Vector2D subtract(Vector2D vector2D) {
        return new Vector2D(x - vector2D.x, y - vector2D.y);
    }

    public Vector2D multiply(Vector2D vector2D) {
        return new Vector2D(x * vector2D.x, y * vector2D.y);
    }

    public Vector2D divide(Vector2D vector2D) {
        return new Vector2D(x / vector2D.x, y / vector2D.y);
    }

    public double dot(Vector2D vector2D) {
        return x * vector2D.x + y * vector2D.y;
    }

    public Vector2D normalize() {
        double magnitude = Math.sqrt(x * x + y * y);
        return new Vector2D(x / magnitude, y / magnitude);
    }

    public double distance(Vector2D vector2D) {
        return Math.sqrt((vector2D.x - x)*(vector2D.x - x)+(vector2D.y - y)*(vector2D.y - y));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
