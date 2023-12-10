package com.okbetltd.bets.appuser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends MongoRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

//    @Query("{email:'?0'}")
//    AppUser findByEmail(String email);

    @Query("{username:'?0'}")
    AppUser findUserByUsername(String username);
}

