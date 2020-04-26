package com.divyendu.covidbatchapp.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.divyendu.covidbatchapp.models.GlobalDataEntity;

@Component
public interface GlobalDataRepository extends JpaRepository<GlobalDataEntity, Date> {

	List<GlobalDataEntity> findByDataReceiveDateBetween(@Temporal(TemporalType.DATE) @Param("startDate") Date startDate,
			@Temporal(TemporalType.DATE) @Param("endDate") Date endDate);

}
