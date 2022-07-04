package io.devshare.controllers;

import io.devshare.application.FileService;
import io.devshare.dto.FileSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    public String file(Model model) {
        model.addAttribute("Files", fileService.getAllFiles());

        return "file";
    }

    @PostMapping("save")
    public String save(
            @RequestBody @Valid FileSaveRequest fileSaveRequest
    ) {
        fileService.create(fileSaveRequest);

        return "file";
    }
}
