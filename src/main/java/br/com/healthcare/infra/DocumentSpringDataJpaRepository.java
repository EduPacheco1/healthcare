package br.com.healthcare.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.healthcare.domain.Document;

public interface DocumentSpringDataJpaRepository extends JpaRepository<Document, UUID>{


}
