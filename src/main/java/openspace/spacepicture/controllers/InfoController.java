package openspace.spacepicture.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/info")
public class InfoController {

    private final ResourceLoader resourceLoader;

    public InfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/")
    public String showAboutMePage(){
        return "about_me";
    }

    @GetMapping("/showImage")
    public void showImage(HttpServletResponse response) throws IOException {
        Resource resource= resourceLoader.getResource("classpath:static/images/myphoto.JPG");
        InputStream inputStream= resource.getInputStream();

        response.setContentType("image/jpg");

        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/download")
    public void downloadCv(HttpServletResponse response){
        Resource resource= resourceLoader.getResource("classpath:static/pdf/Aleksandar Mitic.pdf");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","attachment; filename="+ resource.getFilename());
        response.setHeader("Content-Transfer-Encoding","binary");

        try{
            BufferedOutputStream bos= new BufferedOutputStream(response.getOutputStream());
            InputStream in= resource.getInputStream();

            int len;
            byte[] data= new byte[1024];
            while ((len= in.read(data))> 0){
                bos.write(data,0,len);
            }

            bos.close();
            in.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
