package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

import java.util.List;

public class FindByPlateAction implements UserAction {
    private final Output out;

    public FindByPlateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find subscriber by vehicle plate";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find subscribers by vehicle plate ===");
        if (tracker.findAllSubscribers().size() > 0) {
            String plate = input.askStr("Enter vehicle plate number: ");
            List<Subscriber> subscribers = tracker.findByPlate(plate);
            if (subscribers.size() > 0) {
                for (Subscriber subscriber : subscribers) {
                    out.println(subscriber);
                }
            } else {
                out.println(String.format("Subscriber with plate number : %s not found", plate));
            }
        } else {
            out.println("No subscriber found in the base");
        }
        return true;
    }
}
