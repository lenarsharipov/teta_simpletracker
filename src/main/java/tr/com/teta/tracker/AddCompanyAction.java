package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Company;
public class AddCompanyAction implements UserAction {
    private final Output out;

    public AddCompanyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new company";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Add new Company ====");
        String name = input.askStr("Enter company name: ");
        String phone = input.askStr("Enter company phone: ");
        String address = input.askStr("Enter company address: ");
        Company company = new Company(name, phone, address);
        tracker.addCompany(company);
        return true;
    }
}
