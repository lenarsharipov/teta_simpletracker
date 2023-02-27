package tr.com.teta.tracker;

public interface UserAction {
    String name();

    boolean execute(Input input, Store tracker);
}
