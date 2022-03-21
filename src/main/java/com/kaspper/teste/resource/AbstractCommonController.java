package com.kaspper.teste.resource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractCommonController {

    protected void generateFile(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
}
