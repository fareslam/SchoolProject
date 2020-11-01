package com.SchoolProject.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SchoolProject.Entity.Enseignement;

@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement,Long>{

}
