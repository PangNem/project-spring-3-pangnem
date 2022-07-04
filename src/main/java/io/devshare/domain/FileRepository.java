package io.devshare.domain;

import java.util.List;

public interface FileRepository {
    List<File> findAll();

    File save(File file);
}
