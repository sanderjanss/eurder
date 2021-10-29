package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class DefaultUserRepository implements UserRepository {

    private final Map<UUID, User> userByIdRepository;
    public final Logger logger = LoggerFactory.getLogger(DefaultUserRepository.class);

    public DefaultUserRepository() {
        this.userByIdRepository = new ConcurrentHashMap<>();
        User admin = new User.UserBuilder()
                .setFirstName("Admin")
                .setLastName("Admin")
                .setEmailAddress("Admin@eurder.com")
                .setPhoneNumber("0411111111")
                .setRole(Role.ADMIN)
                .setAddress(new Address.AddressBuilder()
                        .setStreetName("AdminStreet")
                        .setHouseNumber(1)
                        .setPostalCode("1000")
                        .setCity("Brussels")
                        .build())
                .build();
//        User customer = new User.UserBuilder()
//                .setFirstName("Customer")
//                .setLastName("Customer")
//                .setEmailAddress("Customer@eurder.com")
//                .setPhoneNumber("0422222222")
//                .setRole(Role.CUSTOMER)
//                .setAddress(new Address.AddressBuilder()
//                        .setStreetName("CustomerStreet")
//                        .setHouseNumber(1)
//                        .setPostalCode("1000")
//                        .setCity("Brussels")
//                        .build())
//                .build();
//        userByIdRepository.put(customer.getUserId(), customer);
        userByIdRepository.put(admin.getUserId(), admin);
        logger.info("Admin ID: " + admin.getUserId());
    }

    public User findById(UUID userId) {
        return userByIdRepository.get(userId);
    }

    public boolean contains(User user) {
        return userByIdRepository.containsValue(user);
    }

    public void registerCustomer(User customer) {
        userByIdRepository.put(customer.getUserId(), customer);
    }

    public void registerAdmin(User admin) {
        userByIdRepository.put(admin.getUserId(), admin);
    }

    public List<User> getAllCustomers(UUID userId) {

        return userByIdRepository.values().stream()
                .filter(user -> user.getRole() == Role.CUSTOMER)
                .filter(user -> userId == null || user.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
