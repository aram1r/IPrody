package phonebook;

import java.util.ArrayList;

public class PhoneBook{
    private ArrayList<Record> records;

    public PhoneBook() {
        records = new ArrayList<>();
    }

    public void add (Record record) {
        if (record!=null && !containsRecord(record)) {
            records.add(record);
        }
    }

    private boolean containsRecord (Record record) {
        for ( int i = 0; i<records.size(); i++ ) {
            if (records.contains(record)) {
                return true;
            }
        }
        return false;
    }

    public Record find (String name) {
        for (Record record : records) {
            if (record.getName().equals(name)) {
                return record;
            }
        }
        return null;
    }

    public ArrayList<Record> findAll (String name) {
        ArrayList<Record> result = new ArrayList<>();
        for (Record record : records) {
            if (record.getName().equals(name)) {
                result.add(record);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Record record : records) {
            sb.append("Name: ").append(record.getName()).append(", phonenumber: ").append(record.getPhone()).append("\n");
        }
        return sb.toString();
    }
}
