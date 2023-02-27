package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Company;

public class UpdateCompanyAction implements UserAction {
    private final Output out;

    public UpdateCompanyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Update company info";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Update company info ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new company name: ");
        String phone = input.askStr("Enter new company phone: ");
        String address = input.askStr("Enter new company address: ");
        Company company = new Company(name, phone, address);
        if (tracker.updateCompany(id, company)) {
            out.println("Company info updated");
        } else {
            out.println("Updating error");
        }
        return true;
    }
}