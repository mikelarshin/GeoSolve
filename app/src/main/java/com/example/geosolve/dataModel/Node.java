package com.example.geosolve.dataModel;

public class Node {
    private float x, y;
    private boolean move;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveNode(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean inRadius(float x, float y) {
        boolean xBool = this.x - 25 < x && x < this.x + 25;
        boolean yBool = this.y - 25 < y && y < this.y + 25;
        move = xBool && yBool;
        return move;
    }

    public boolean isMove() {
        return move;
    }

    public boolean stopMove() {
        if (move) {
            move = false;
            return true;
        }else
            return false;
    }
}
