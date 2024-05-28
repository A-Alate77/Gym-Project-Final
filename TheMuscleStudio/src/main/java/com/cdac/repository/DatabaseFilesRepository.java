package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.model.DatabaseFiles;

@Repository
public interface DatabaseFilesRepository extends JpaRepository<DatabaseFiles, Integer> {
	DatabaseFiles	findByFileName (String fileName);
}
