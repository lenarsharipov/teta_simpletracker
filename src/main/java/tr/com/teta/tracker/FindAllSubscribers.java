package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

import java.util.List;

public class FindAllSubscribers implements UserAction {
    private final Output out;

    public FindAllSubscribers(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find all subscribers";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Show all subscribers ===");
        List<Subscriber> subscribers = tracker.findAllSubscribers();
        if (subscribers.size() > 0) {
            for (Subscriber subscriber : subscribers) {
                out.println(subscriber);
            }
        } else {
            out.println("No subscriber has been added yet.");
        }
        return true;
    }
}
