package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Company;

import java.util.List;

public class FindAllCompaniesAction implements UserAction {
    private final Output out;

    public FindAllCompaniesAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all companies";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Show all companies ===");
        List<Company> companies = tracker.findAllCompanies();
        if (companies.size() > 0) {
            for (Company company : companies) {
                out.println(company);
            }
        } else {
            out.println("No company has been added yet.");
        }
        return true;
    }
}
