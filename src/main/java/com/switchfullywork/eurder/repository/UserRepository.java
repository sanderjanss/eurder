package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.user.Address;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.domain.user.User;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final Map<UUID, User> userByIdRepository;

    public UserRepository() {
        this.userByIdRepository = new ConcurrentHashMap<>();
        User admin = new User("Admin", "Admin", "Admin@admin.be",
                new Address("AdminStreet", "1000", "Admin", 1),
                "0411111111", Role.ADMIN);
        userByIdRepository.put(admin.getUuid(), admin);
    }

    public User findById(UUID id){
        return userByIdRepository.get(id);
    }


    public void registerCustomer(User customer){
        userByIdRepository.put(customer.getUuid(), customer);
    }

    public List<User> getAllCustomers(UUID userIdOrNull){

        return userByIdRepository.values().stream()
                .filter(user -> user.getRole() == Role.CUSTOMER)
                .filter(user -> (userIdOrNull == null || user.getUuid().equals(userIdOrNull)))
                .toList();
    }
//return this.booksById.entrySet().stream()
//                .filter(set -> set.getValue().isShowableToUser())
//            .filter(set -> isbnOrNull == null || WildCardValidator.match(isbnOrNull, set.getValue().getIsbn()))
//            .filter(set -> titleOrNull == null || WildCardValidator.match(titleOrNull, set.getValue().getTitle()))
//            .filter(set -> (authorFirstNameOrNull == null || WildCardValidator.match(authorFirstNameOrNull, set.getValue().getAuthor().getFirstName())))
//            .filter(set -> (authorLastNameOrNull == null || WildCardValidator.match(authorLastNameOrNull, set.getValue().getAuthor().getLastName())))
//            .map(Map.Entry::getValue)
//                .collect(Collectors.toList());


}
