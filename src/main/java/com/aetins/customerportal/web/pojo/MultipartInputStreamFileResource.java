package com.aetins.customerportal.web.pojo;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;

/**
 * <i>To handle bytestream resource as pojo</i>
 * @author avinash
 *
 */
public class MultipartInputStreamFileResource extends InputStreamResource{

private final String filename;

	
	public MultipartInputStreamFileResource(InputStream inputStream, String filename) {
        super(inputStream);
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public long contentLength() throws IOException {
        return -1;
    }
}
