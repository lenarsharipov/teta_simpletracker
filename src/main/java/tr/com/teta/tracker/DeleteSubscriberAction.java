package tr.com.teta.tracker;

public class DeleteSubscriberAction implements UserAction {
    private final Output out;

    public DeleteSubscriberAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete subscriber";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete subscriber ===");
        String subscriberNumber = input.askStr("Enter subscriber number: ");
        if (tracker.deleteSubscriber(subscriberNumber)) {
            out.println("Subscriber deleted.");
        } else {
            out.println("Deletion error.");
        }
        return true;
    }
}
