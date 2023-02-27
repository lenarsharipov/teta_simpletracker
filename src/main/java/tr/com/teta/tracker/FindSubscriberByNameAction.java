package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

import java.util.Objects;

public class FindSubscriberByNameAction implements UserAction {
    private final Output out;

    public FindSubscriberByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find subscriber by name";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find subscriber by name ===");
        if (tracker.findAllSubscribers().size() > 0) {
            String name = input.askStr("Enter name: ");
            String surname = input.askStr("Enter surname: ");
            Subscriber subscriber = tracker.findByName(name, surname);
            out.println(Objects.requireNonNullElseGet(
                    subscriber, () -> String.format("Subscriber: %s %s not found", name, surname)));
        } else {
            out.println("No subscriber found in the base");
        }
        return true;
    }
}
