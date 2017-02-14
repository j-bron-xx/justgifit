package gif.it.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {

	private final static Logger log = LoggerFactory.getLogger(UploadController.class);

	@Value("${spring.http.multipart.location}")
	private String location;

	// curl -F file=@theanimal.mp4 -F start=0 -F end=0 -F speed=1 -F repeat=0 localhost:9000/upload
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.IMAGE_GIF_VALUE)
	public String upload(@RequestPart("file") MultipartFile file,
	                     @RequestParam("start") int start,
	                     @RequestParam("end") int end,
	                     @RequestParam("speed") int speed,
	                     @RequestParam("repeat") boolean repeat) throws IOException {
		File videoFile = new File(location + File.pathSeparator + System.currentTimeMillis() + ".mp4");
		file.transferTo(videoFile);
		log.info("Video file saved to {}", videoFile.getAbsolutePath());

		return "";
	}

}
