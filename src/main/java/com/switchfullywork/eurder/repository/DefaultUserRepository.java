package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultUserRepository implements UserRepository {

    private final Map<UUID, User> userByIdRepository;

    public DefaultUserRepository() {
        this.userByIdRepository = new ConcurrentHashMap<>();
        User admin = new User("Admin", "Admin", "Admin@admin.be",
                new Address("AdminStreet", "1000", "Admin", 1),
                "0411111111", Role.ADMIN);
        userByIdRepository.put(admin.getUserId(), admin);
    }

    public User findById(UUID userId){
        return userByIdRepository.get(userId);
    }

    public boolean contains(User user){
        return userByIdRepository.containsValue(user);
    }

    public void registerCustomer(User customer){
        userByIdRepository.put(customer.getUserId(), customer);
    }

    public List<User> getAllCustomers(UUID userId){

        return userByIdRepository.values().stream()
                .filter(user -> user.getRole() == Role.CUSTOMER)
                .filter(user -> (userId == null || user.getUserId().equals(userId)))
                .toList();
    }



}
