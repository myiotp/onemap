package com.onemap.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.alternativevision.gpx.beans.Waypoint;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onemap.domain.BlockXY;
import com.onemap.domain.MachineryXY;
import com.onemap.service.BlockXYService;
import com.onemap.service.MachineryXYService;
import com.onemap.utl.common.GPXImportUtil;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {
	@Autowired
	private BlockXYService service;
	@Autowired
	private MachineryXYService machineryXYservice;

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	// location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "upload";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	/**
	 * Upload single file using Spring Controller
	 * 
	 * * Upon receiving file upload submission, parses the request to read
	 * upload data and saves the file on disk. //public @ResponseBody
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file,
			@RequestParam("page") String page,
			@RequestParam("blockId") Integer blockId) {

		System.out.println("blockId:" + blockId);
		if (!file.isEmpty()) {
			try {
				String name = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				BlockXY deletedRecord = new BlockXY();
				deletedRecord.setBlockId(blockId);
				service.delete(deletedRecord);

				if (file.getName().endsWith(".csv")) {
					Reader in = new InputStreamReader(file.getInputStream());
					Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
					int index = 1;
					for (CSVRecord record : records) {
						Double positionX = Double.parseDouble(record.get(0));
						Double positionY = Double.parseDouble(record.get(1));
						index++;
						BlockXY t = new BlockXY();
						t.setBlockId(blockId);
						t.setPositionSequence(index);
						t.setPositionX(positionX);
						t.setPositionY(positionY);
						try {
							service.save(t);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					List<Waypoint> points = GPXImportUtil.getTracks(
							file.getInputStream(), "" + blockId);
					int index = 1;
					for (Iterator<Waypoint> iterator = points.iterator(); iterator
							.hasNext();) {
						Waypoint waypoint = (Waypoint) iterator.next();
						// System.out.println(waypoint.getLongitude()+","+waypoint.getLatitude());
						index++;
						BlockXY t = new BlockXY();
						t.setBlockId(blockId);
						t.setPositionSequence(index);
						t.setPositionX(waypoint.getLongitude());
						t.setPositionY(waypoint.getLatitude());
						try {
							service.save(t);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				// File dir = new File(rootPath + File.separator + "tmpFiles");
				// if (!dir.exists())
				// dir.mkdirs();
				//
				// // Create the file on server
				// File serverFile = new File(dir.getAbsolutePath()
				// + File.separator + name);
				// BufferedOutputStream stream = new BufferedOutputStream(
				// new FileOutputStream(serverFile));
				// stream.write(bytes);
				// stream.close();
				//
				// logger.info("Server File Location="
				// + serverFile.getAbsolutePath());
				//
				// System.out.println("You successfully uploaded file." +
				// serverFile.getAbsolutePath());
			} catch (Exception e) {
				System.out.println("You failed to upload " + " => "
						+ e.getMessage());
			}
		} else {
			System.out.println("You failed to upload "
					+ " because the file was empty.");
		}

		// redirects client to message page

		int p = 1;
		if (page != null) {
			try {
				p = Integer.parseInt(page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// service.delete(blockNumber);
		// response.sendRedirect("./landblock/editlist?page="+p);
		return "redirect:./landblock/editlist?page=" + p;
	}

	/**
	 * Upload single file using Spring Controller
	 * 
	 * * Upon receiving file upload submission, parses the request to read
	 * upload data and saves the file on disk. //public @ResponseBody
	 */
	@RequestMapping(value = "/uploadFile2", method = RequestMethod.POST)
	public String uploadFileHandler2(@RequestParam("file") MultipartFile file,
			@RequestParam("page") String page,
			@RequestParam("machineryOperationId") Integer machineryOperationId) {

		System.out.println("machineryOperationId:" + machineryOperationId);
		if (!file.isEmpty()) {
			try {
				String name = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				MachineryXY deletedRecord = new MachineryXY();
				deletedRecord.setMachineryOperationId(machineryOperationId);
				machineryXYservice.delete(deletedRecord);

				Reader in = new InputStreamReader(file.getInputStream());
				Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
				int index = 1;
				for (CSVRecord record : records) {
					Double positionX = Double.parseDouble(record.get(0));
					Double positionY = Double.parseDouble(record.get(1));
					index++;
					MachineryXY t = new MachineryXY();
					t.setMachineryOperationId(machineryOperationId);
					t.setPositionSequence(index);
					t.setPositionX(positionX);
					t.setPositionY(positionY);
					try {
						machineryXYservice.save(t);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// // Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				// File dir = new File(rootPath + File.separator + "tmpFiles");
				// if (!dir.exists())
				// dir.mkdirs();
				//
				// // Create the file on server
				// File serverFile = new File(dir.getAbsolutePath()
				// + File.separator + name);
				// BufferedOutputStream stream = new BufferedOutputStream(
				// new FileOutputStream(serverFile));
				// stream.write(bytes);
				// stream.close();
				//
				// logger.info("Server File Location="
				// + serverFile.getAbsolutePath());
				//
				// System.out.println("You successfully uploaded file." +
				// serverFile.getAbsolutePath());
			} catch (Exception e) {
				System.out.println("You failed to upload " + " => "
						+ e.getMessage());
			}
		} else {
			System.out.println("You failed to upload "
					+ " because the file was empty.");
		}

		// redirects client to message page

		int p = 1;
		if (page != null) {
			try {
				p = Integer.parseInt(page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// service.delete(blockNumber);
		// response.sendRedirect("./landblock/editlist?page="+p);
		return "redirect:./farmmachinerygps/editlist?page=" + p;
	}
}