import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public boolean isPhone(String phone) {
        Pattern patternPhone = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcherPhone = patternPhone.matcher(phone);
        return matcherPhone.matches();
    }

    public boolean isEmail(String email) {
        Pattern patternEmail = Pattern.compile("^[A-Za-z\\d+_.-]+@(.+)$");
        Matcher matcherEmail = patternEmail.matcher(email);
        return matcherEmail.matches();
    }


    public void addCustomer(String data) throws CustomerException {

        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;


        String[] components = data.split("\\s+");
        if (components.length > 4) {
            throw new CustomerException(CustomerException.OVER_FOUR);
        } else if (components.length < 4) {
            throw new CustomerException(CustomerException.LESS_FOUR);
        } else if (isEmail(components[2]) == false) {
            throw new CustomerException(CustomerException.THIS_IS_NOT_AN_EMAIL);
        } else if (isPhone(components[3]) == false) {
            throw new CustomerException(CustomerException.THIS_IS_NOT_A_NUMBER);
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}