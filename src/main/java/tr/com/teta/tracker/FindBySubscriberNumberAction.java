package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

import java.util.Objects;

public class FindBySubscriberNumberAction implements UserAction {
    private final Output out;

    public FindBySubscriberNumberAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find subscriber by subscriber number";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find subscriber by subscriber number ===");
        if (tracker.findAllSubscribers().size() > 0) {
            String plate = input.askStr("Enter subscriber number: ");
            Subscriber subscriber = tracker.findBySubscriberNumber(plate);
            out.println(Objects.requireNonNullElseGet(
                    subscriber, () -> "Subscribers with vehicle plate number consisting " + plate + " number not found"));
        } else {
            out.println("No subscriber found in the base");
        }
        return true;
    }
}
