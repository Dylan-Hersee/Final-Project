package com.dylanhersee.Project.Checklist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.dylanhersee.Project.Checklist.model.Checklist;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
    List<Checklist> findByUsername (String username);
    List<Checklist> findByUsernameAndEventName(String username, String eventName);
}
