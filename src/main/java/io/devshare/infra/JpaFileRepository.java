package io.devshare.infra;

import io.devshare.domain.File;
import io.devshare.domain.FileRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaFileRepository extends FileRepository, CrudRepository<File, Long> {
    List<File> findAll();

    File save(File file);
}
