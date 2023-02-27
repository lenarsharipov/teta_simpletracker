package tr.com.teta.tracker;

import tr.com.teta.tracker.model.Company;
import tr.com.teta.tracker.model.Subscriber;

import java.util.List;

public interface Store {
    Company addCompany(Company company);

    boolean updateCompany(int id, Company company);

    boolean deleteCompany(int id);

    List<Company> findAllCompanies();

    Subscriber addSubscriber(Subscriber subscriber);

    boolean updateSubscriber(int id, Subscriber subscriber);

    boolean deleteSubscriber(int id);

    Subscriber findByName(String name, String surname);

    Subscriber findBySubscriberNumber(String subscriberNumber);

    List<Subscriber> findByPlate(String plateNumber);

}