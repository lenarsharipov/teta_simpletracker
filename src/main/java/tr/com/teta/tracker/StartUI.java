package tr.com.teta.tracker;

import java.util.List;
import tr.com.teta.tracker.store.SqlTracker;

public class StartUI {
    private final Output out;

    public StartUI() {
        this.out = new ConsoleOutput();
    }

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Input input = new ValidateInput(
                new ConsoleInput()
        );
        Output output = new ConsoleOutput();
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = List.of(
                    new AddCompanyAction(output),
                    new UpdateCompanyAction(output),
                    new DeleteCompanyAction(output),
                    new FindAllCompaniesAction(output),
                    new AddSubscriberAction(output),
                    new UpdateSubscriberAction(output),
                    new DeleteSubscriberAction(output),
                    new FindAllSubscribers(output),
                    new FindSubscriberByNameAction(output),
                    new FindBySubscriberNumberAction(output),
                    new FindByPlateAction(output),
                    new ExitAction()
            );
            new StartUI().init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
