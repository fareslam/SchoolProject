package com.SchoolProject.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.ClassModule;
import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.Module;
import com.SchoolProject.Entity.Student;
@Repository
public interface ClassModuleRepository extends JpaRepository<ClassModule,Long>{

Optional<List<ClassModule>> findByModule(Module m);
Optional<ClassModule> findByModuleAndClasse(Module m,Classe c);
}
