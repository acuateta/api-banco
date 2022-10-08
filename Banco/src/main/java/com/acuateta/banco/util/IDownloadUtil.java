package com.acuateta.banco.util;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface IDownloadUtil {

    ResponseEntity downloadFile(InputStreamResource resource, String filename);
}
