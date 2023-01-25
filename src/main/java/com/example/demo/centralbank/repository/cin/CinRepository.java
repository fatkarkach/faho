package com.example.demo.centralbank.repository.cin;
import com.example.demo.centralbank.models.Cin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinRepository extends JpaRepository<Cin, Long> {
}
