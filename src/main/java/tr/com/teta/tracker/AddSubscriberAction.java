package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

public class AddSubscriberAction implements UserAction {
    private final Output out;

    public AddSubscriberAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Subscriber";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Add new Subscriber");
        if (tracker.findAllCompanies().size() > 0) {
            String name = input.askStr("Enter first name: ");
            String surname = input.askStr("Enter second name: ");
            String subscriberNumber = input.askStr("Enter subscriber number: ");
            String plate = input.askStr("Enter plate number: ");
            int companyId = input.askInt("Enter company id: ");
            Subscriber subscriber = new Subscriber(name, surname, subscriberNumber, plate, companyId);
            tracker.addSubscriber(subscriber);
        } else {
            out.println("PLease add at least 1 company");
        }
        return true;
    }
}