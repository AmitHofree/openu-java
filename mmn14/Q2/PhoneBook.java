import java.io.*;
import java.util.*;

public class PhoneBook {
    private TreeMap<String, String> nameToPhoneNumber;

    public PhoneBook() {
        nameToPhoneNumber = new TreeMap<>();
    }

    public PhoneBook(String[] names, String[] phoneNumbers) {
        nameToPhoneNumber = new TreeMap<>();
        if (names.length != phoneNumbers.length) throw new IllegalArgumentException("Names length isn't equal to phone numbers length");
        for (int i = 0; i < names.length; i++) {
            nameToPhoneNumber.put(names[i], phoneNumbers[i]);
        }
    }

    public boolean add(PhoneBookEntry entry) {
        if (!nameToPhoneNumber.containsKey(entry.getName())) {
            nameToPhoneNumber.put(entry.getName(), entry.getPhoneNumber());
            return true;
        }
        return false;
    }

    public boolean edit(String name, String phoneNumber) {
        if (nameToPhoneNumber.containsKey(name)) {
            nameToPhoneNumber.put(name, phoneNumber);
            return true;
        }
        return false;
    }

    public boolean remove(String name) {
        return nameToPhoneNumber.remove(name) != null;
    }

    public List<PhoneBookEntry> entries() {
        List<PhoneBookEntry> entriesList = new ArrayList<>();
        Set<Map.Entry<String, String>> entries = nameToPhoneNumber.entrySet();
        for (Map.Entry<String, String> entry : entries)
        {
            entriesList.add(new PhoneBookEntry(entry.getKey(), entry.getValue()));
        }
        return entriesList;
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        nameToPhoneNumber = (TreeMap<String, String>) objStream.readObject();
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fileStream = new FileOutputStream(file);
        ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
        objStream.writeObject(nameToPhoneNumber);
    }
}
