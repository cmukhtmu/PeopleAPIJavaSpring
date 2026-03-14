package com.example.service;

import com.example.model.AddressHistory;
import com.example.repository.AddressHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AddressHistoryService {

    private static final List<String> STATES = List.of(
    "AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
    "HI","ID","IL","IN","IA","KS","KY","LA","ME","MD",
    "MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
    "NM","NY","NC","ND","OH","OK","OR","PA","RI","SC",
    "SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"
);

    private static final List<String> STREET_DIRECTION = List.of(
    "North ", "East ", "West ", "South ", ""
);

    private static final List<String> STREET_NAMES = List.of(
    "Main St", "Oak St", "Pine St", "Maple Ave", "Cedar Ave",
    "Elm St", "Washington St", "Lakeview Dr", "Sunset Blvd",
    "Highland Rd", "Riverside Dr", "Park Ave", "Broadway",
    "Center St", "Hillcrest Dr", "Madison Ave", "Jefferson St"
);

    private static final List<String> CITIES = List.of(
    "Springfield", "Riverton", "Fairview", "Greenville", "Madison",
    "Georgetown", "Clinton", "Franklin", "Arlington", "Ashland",
    "Milton", "Burlington", "Salem", "Dayton", "Lexington"
);

    private static final List<String> CITIES_AGE = List.of(
    "New ", "Old ", ""
);

    
    @Autowired
    private AddressHistoryRepository addressHistoryRepository;
    
    // Get all addressHistory ordered by ID descending
    public List<AddressHistory> getAllAddressHistory() {
        return addressHistoryRepository.findAll().stream()
            .sorted((a, b) -> b.getId().compareTo(a.getId()))
            .toList();
    }
    
    // Get addressHistory by ID
    public Optional<AddressHistory> getAddressHistoryById(Long id) {
        return addressHistoryRepository.findById(id);
    }
    
    // Create or update addressHistory
    public AddressHistory saveAddressHistory(AddressHistory addressHistory) {
        return addressHistoryRepository.save(addressHistory);
    }
    
    // Delete addressHistory by ID
    public void deleteAddressHistoryById(Long id) {
        addressHistoryRepository.deleteById(id);
    }

    public List<AddressHistory> generateRandomAddresses(int count, List<Long> peopleIds) {
    List<AddressHistory> generated = new ArrayList<>();
    Random random = new Random();

    for (Long peopleId : peopleIds) {
        for (int i = 0; i < count; i++) {

            String streetNumber = String.valueOf(random.nextInt(9000) + 10);
            String streetName = STREET_DIRECTION.get(random.nextInt(STREET_DIRECTION.size())) + STREET_NAMES.get(random.nextInt(STREET_NAMES.size()));
            String city = CITIES_AGE.get(random.nextInt(CITIES_AGE.size())) + CITIES.get(random.nextInt(CITIES.size()));
            String state = STATES.get(random.nextInt(STATES.size()));
            String zip = String.format("%05d", random.nextInt(99999));

            AddressHistory ah = new AddressHistory();
            ah.setPeopleId(peopleId);
            ah.setAddress1(streetNumber + " " + streetName);
            ah.setAddress2("Unit " + (random.nextInt(100) + 1));
            ah.setCity(city);
            ah.setState(state);
            ah.setZip(zip);
            ah.setActive(random.nextBoolean());

            generated.add(ah);
        }
    }

    return addressHistoryRepository.saveAll(generated);
}

}