package tr.com.teta.tracker;

public class DeleteCompanyAction implements UserAction {
    private final Output out;

    public DeleteCompanyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete company";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete company ===");
        int id = input.askInt("Enter company id: ");
        if (tracker.deleteCompany(id)) {
            out.println("Company deleted");
        } else {
            out.println("Deletion error");
        }
        return true;
    }
}
