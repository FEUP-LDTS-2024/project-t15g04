package AlienWalk.States;

import AlienWalk.Controller.Controller;
import AlienWalk.Game;
import AlienWalk.Viewer.Viewer;

public abstract class State<T> {
    public Controller controller;
    public Viewer viewer;
    public T model;

    public State(T model, Controller controller, Viewer viewer){
        this.model = model;
        this.controller = controller;
        this.viewer = viewer;
    }

    public abstract void step(Game game)
;}
