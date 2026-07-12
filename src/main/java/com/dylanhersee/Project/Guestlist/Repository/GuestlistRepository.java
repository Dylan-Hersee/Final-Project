package com.dylanhersee.Project.Guestlist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.dylanhersee.Project.Guestlist.model.Guestlist;

public interface  GuestlistRepository extends JpaRepository<Guestlist, Long>{
    
    List<Guestlist> findByUsername(String username);
}
