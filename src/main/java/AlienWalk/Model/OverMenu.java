package AlienWalk.Model;

public class OverMenu {
    public enum Option {
        Restart, Quit;

        public static Option next(Option current) {
            return switch (current) {
                case Restart -> Quit;
                case Quit -> Restart;
            };
        }

        public static Option previous(Option current) {
            return switch (current) {
                case Restart -> Quit;
                case Quit -> Restart;
            };
        }
    }

    Option current;

    public OverMenu() {
        current = Option.Restart;  // default option is Restart
    }

    public void setCurrent(Option option) {
        current = option;
    }

    public Option getCurrent() {
        return current;
    }
}

