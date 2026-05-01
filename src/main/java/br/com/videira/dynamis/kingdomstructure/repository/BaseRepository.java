package br.com.videira.dynamis.kingdomstructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

@NoRepositoryBean
public interface BaseRepository<MODEL> extends JpaRepository<MODEL, Long> {

    @Modifying
    @Query("UPDATE #{#entityName} m SET m.ativo = false WHERE m.id = :id")
    void inactiveById(@Param("id") Long id);

    @NonNull
    @Query("SELECT m FROM #{#entityName} m WHERE m.ativo = TRUE")
    Page<MODEL> findAll(@NonNull Pageable pageable);
}