package AlienWalk.Model;

public class Menu {
    public enum  Option {
        Start, Settings, Quit;

        public static Option next(Option current) {
            return switch (current) {
                case Start -> Settings;
                case Settings -> Quit;
                case Quit -> Start;
            };
        }

        public static Option previous(Option current) {
            return switch (current) {
                case Start -> Quit;
                case Settings -> Start;
                case Quit -> Settings;
            };
        }
    }
    private Option current;

    public Menu(){
        current = Option.Start;
    }

    public void setCurrent(Option option){
        current = option;
    }
    public Option getCurrent() {
        return current;
    }

    public void nextOption(){
        current = (Menu.Option.next(current));
    }
    public void previousOption(){
        current = (Menu.Option.previous(current));
    }
}
