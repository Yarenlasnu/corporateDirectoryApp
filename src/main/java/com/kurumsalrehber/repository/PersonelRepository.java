package com.kurumsalrehber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.entity.Personel;

public interface PersonelRepository extends JpaRepository<Personel, Long>{
	List<Personel> findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCase(String ad, String soyad);
	@Query("SELECT new com.kurumsalrehber.dto.PersonelDTO(p.ad, p.soyad, f.ad, b.ad, p.telefon) " +
		       "FROM Personel p " +
		       "JOIN p.bolum b " +
		       "JOIN b.fakulte f " +
		       "WHERE LOWER(p.ad) LIKE LOWER(CONCAT('%', :arama, '%')) " +
		       "   OR LOWER(p.soyad) LIKE LOWER(CONCAT('%', :arama, '%')) " +
		       "   OR LOWER(f.ad) LIKE LOWER(CONCAT('%', :arama, '%')) " +
		       "   OR LOWER(b.ad) LIKE LOWER(CONCAT('%', :arama, '%')) " +
		       "   OR LOWER(p.telefon) LIKE LOWER(CONCAT('%', :arama, '%'))")
		List<PersonelDTO> search(@Param("arama") String arama);

}
