package tr.com.teta.tracker.store;

import tr.com.teta.tracker.Store;
import tr.com.teta.tracker.model.Company;
import tr.com.teta.tracker.model.Subscriber;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker() {

    }

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }

    public void init() {
        try (InputStream in =
                     SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Company addCompany(Company company) {
        try (PreparedStatement statement =
                cn.prepareStatement("insert into companies(company, phone, address) values(?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, company.getCompany());
            statement.setString(2, company.getPhone());
            statement.setString(3, company.getAddress());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    company.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public boolean updateCompany(int id, Company company) {
        boolean result = false;
        try (PreparedStatement statement =
                cn.prepareStatement("update companies set company = ?, phone = ?, address = ? where id = ?")) {
            statement.setString(1, company.getCompany());
            statement.setString(2, company.getPhone());
            statement.setString(3, company.getAddress());
            statement.setInt(4, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteCompany(int id) {
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement("delete from companies where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        return new Company(
                resultSet.getInt("id"),
                resultSet.getString("company"),
                resultSet.getString("phone"),
                resultSet.getString("address")
        );
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from companies")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    companies.add(createCompany(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }

    @Override
    public Subscriber addSubscriber(Subscriber subscriber) {
        try (PreparedStatement statement =
                cn.prepareStatement(
                        "insert into subscribers(name, surname, subscriberNumber, plateNumber, companyId) values(?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, subscriber.getName());
            statement.setString(2, subscriber.getSurname());
            statement.setString(3, subscriber.getSubscriberNumber());
            statement.setString(4, subscriber.getPlateNumber());
            statement.setInt(5, subscriber.getCompanyId());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subscriber.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriber;
    }

    @Override
    public boolean updateSubscriber(String subscriberNumber, Subscriber subscriber) {
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement(
                        "update subscribers set name = ?, "
                                + "surname = ?, "
                                + "subscriberNumber = ?, "
                                + "plateNumber = ?, "
                                + "id = ? "
                                + "where subscriberNumber = ?"
                                + "values(?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, subscriber.getName());
            statement.setString(2, subscriber.getSurname());
            statement.setString(3, subscriberNumber);
            statement.setString(4, subscriber.getPlateNumber());
            statement.setInt(5, subscriber.getCompanyId());
            statement.setString(6, subscriberNumber);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteSubscriber(String subscriberNumber) {
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement(
                "delete from subscribers where subscriberNumber = ?")) {
            statement.setString(1, subscriberNumber);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Subscriber> findAllSubscribers() {
        List<Subscriber> subscribers = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from subscribers")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subscribers.add(createSubscriber(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    private Subscriber createSubscriber(ResultSet resultSet) throws SQLException {
        return new Subscriber(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("subscriberNumber"),
                resultSet.getString("plateNumber"),
                resultSet.getInt("companyId"));
    }

    @Override
    public Subscriber findByName(String name, String surname) {
        Subscriber subscriber = null;
        try (PreparedStatement statement =
                     cn.prepareStatement(
                             "select * from subscribers where name = ? and surname = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subscriber = createSubscriber(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriber;
    }

    @Override
    public Subscriber findBySubscriberNumber(String subscriberNumber) {
        Subscriber subscriber = null;
        try (PreparedStatement statement =
                cn.prepareStatement("select * from subscribers where subscriberNumber = ?")) {
            statement.setString(1, subscriberNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subscriber = createSubscriber(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriber;
    }

    @Override
    public List<Subscriber> findByPlate(String plateNumber) {
        List<Subscriber> subscribers = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("select * from subscribers where plateNumber like ?")) {
            statement.setString(1, "%" + plateNumber + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subscribers.add(createSubscriber(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscribers;
    }

}