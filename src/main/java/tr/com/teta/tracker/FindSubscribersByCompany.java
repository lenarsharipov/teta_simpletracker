package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

import java.util.List;

public class FindSubscribersByCompany implements UserAction {
    private final Output out;

    public FindSubscribersByCompany(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find all subscribers of company.";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find all subscribers of company ===");
        if (tracker.findAllSubscribers().size() > 0) {
            int id = input.askInt("Enter company id: ");
            List<Subscriber> subscribers = tracker.findSubscriberByCompany(id);
            if (subscribers.size() > 0) {
                for (Subscriber subscriber : subscribers) {
                    out.println(subscriber);
                }
            } else {
                out.println("Company with id = " + id + " has no any subscriber");
            }
        } else {
           out.println("No subscriber has been added yet.");
        }
        return true;
    }
}
