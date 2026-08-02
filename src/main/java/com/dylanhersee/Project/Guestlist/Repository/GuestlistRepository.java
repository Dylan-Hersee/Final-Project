package com.dylanhersee.Project.Guestlist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dylanhersee.Project.Guestlist.model.Guestlist;

@Repository
public interface  GuestlistRepository extends JpaRepository<Guestlist, Long>{
    
    List<Guestlist> findByUsername(String username);
    List<Guestlist> findByUsernameAndEventName(String username, String eventName);

    void deleteByUsername(String username);
}


