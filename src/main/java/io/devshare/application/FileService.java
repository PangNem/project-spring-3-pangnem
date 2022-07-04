package io.devshare.application;

import io.devshare.domain.File;
import io.devshare.domain.FileRepository;
import io.devshare.dto.FileResponse;
import io.devshare.dto.FileSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public List<FileResponse> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(File::toFileResponse)
                .collect(Collectors.toList());
    }

    public File create(FileSaveRequest fileSaveRequest) {
        File file = fileSaveRequest.toEntity();

        return fileRepository.save(file);
    }
}
