package com.onemap.controller.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.UploadImage;
import com.onemap.domain.UserVehicle;
import com.onemap.service.UploadImageService;
import com.onemap.service.UserVehicleService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
@RequestMapping("/api/upload")
public class UploadPhotoAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(UploadPhotoAuthenticationController.class);

	// location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "upload";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@Autowired
	private UploadImageService service;

	@Autowired
	private UserVehicleService vehicleService;

	/**
	 * Upload single file using Spring Controller
	 * 
	 * * Upon receiving file upload submission, parses the request to read upload
	 * data and saves the file on disk. //public @ResponseBody
	 */
	@RequestMapping(value = "auth/username/{username}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public APIResponseBaseObject uploadFileHandler(@RequestParam("file") MultipartFile file,
			@PathVariable("username") String username, @RequestParam("type") String type) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		logger.info("username:" + username + ",type:" + type);
		if (!file.isEmpty()) {
			try {
				String originalFilename = file.getOriginalFilename();
				if (originalFilename.length() > 10) {
					originalFilename = originalFilename.substring(originalFilename.length() - 10);
				}
				String name = UUID.randomUUID().toString() + originalFilename;
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + "uploadimages");
				if (!dir.exists())
					dir.mkdirs();
				//
				// // Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());
				List<UploadImage> existingImage = service.getByUsernameAndType(username, type);
				if (existingImage != null && existingImage.size() > 0) {
					for (UploadImage img : existingImage) {
						service.delete(img);
						File existingImageFile = new File(dir.getAbsolutePath() + File.separator + img.getImage());
						if (existingImageFile.exists()) {
							existingImageFile.delete();
						}
					}
				}

				System.out.println("You successfully uploaded file." + serverFile.getAbsolutePath());
				UploadImage uploadImage = new UploadImage();
				uploadImage.setUsername(username);
				uploadImage.setImage(name);
				uploadImage.setType(type);
				service.save(uploadImage);

				result.setInfo("OK");
				result.setStatus(1);
			} catch (Exception e) {
				System.out.println("You failed to upload " + " => " + e.getMessage());
				result.setStatus(0);
				result.setInfo("You failed to upload " + " => " + e.getMessage());
			}
		} else {
			System.out.println("You failed to upload " + " because the file was empty.");
			result.setStatus(0);
			result.setInfo("You failed to upload " + " because the file was empty.");
		}

		return result;
	}

	@RequestMapping(value = "vehicle/{id}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public APIResponseBaseObject uploadVehicleImage(@RequestParam("file") MultipartFile file,
			@PathVariable("id") Integer id) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		logger.info("vehicle/id:" + id);

		UserVehicle userVehicle = null;
		try {
			userVehicle = vehicleService.get(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (userVehicle == null) {
			result.setInfo("This vehicle does not exist.");
			result.setStatus(-1);
			return result;
		}

		if (!file.isEmpty()) {
			try {
				String oldcertimage = userVehicle.getCertimage();

				String name = UUID.randomUUID().toString() + file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles"  + File.separator + "uploadimages" + File.separator + "vehicle");
				if (!dir.exists())
					dir.mkdirs();
				//
				// // Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());
				if (oldcertimage != null) {
					File existingImageFile = new File(dir.getAbsolutePath() + File.separator + oldcertimage);
					if (existingImageFile.exists()) {
						existingImageFile.delete();
					}
				}
				System.out.println("You successfully uploaded file." + serverFile.getAbsolutePath());

				userVehicle.setCertimage(name);
				vehicleService.update(userVehicle);

				result.setInfo("OK");
				result.setStatus(1);
			} catch (Exception e) {
				System.out.println("You failed to upload " + " => " + e.getMessage());
				result.setStatus(0);
				result.setInfo("You failed to upload " + " => " + e.getMessage());
			}
		} else {
			System.out.println("You failed to upload " + " because the file was empty.");
			result.setStatus(0);
			result.setInfo("You failed to upload " + " because the file was empty.");
		}

		return result;
	}
}