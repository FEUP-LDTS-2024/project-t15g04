package AlienWalk.States;

import AlienWalk.Controller.Controller;
import AlienWalk.Viewer.Viewer;

public abstract class State<T> {
    private Controller controller;
    private Viewer viewer;
    private T model;

    public abstract void draw();
    public abstract void update();
;}
