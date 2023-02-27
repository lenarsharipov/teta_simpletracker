package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Subscriber;

public class UpdateSubscriberAction implements UserAction {
    private final Output out;

    public UpdateSubscriberAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Update subscriber info";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Update subscriber info ===");
        String currentNumber = input.askStr("Enter current subscriber number: ");
        String name = input.askStr("Enter new first name: ");
        String surname = input.askStr("Enter new second name: ");
        String plate = input.askStr("Enter new plate: ");
        int companyId = input.askInt("Enter company id");
        Subscriber subscriber = new Subscriber(name, surname, currentNumber, plate, companyId);
        if (tracker.updateSubscriber(currentNumber, subscriber)) {
            out.println("Subscriber info updated");
        } else {
            out.println("Updating error");
        }
        return true;
    }
}
