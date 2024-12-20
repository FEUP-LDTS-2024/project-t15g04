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
    Option current;

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
        this.setCurrent(Menu.Option.next(this.current));
    }
    public void previousOption(){
        this.setCurrent(Menu.Option.previous(this.current));
    }
}
