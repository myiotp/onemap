package com.onemap.utl.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Track;
import org.alternativevision.gpx.beans.Waypoint;
import org.xml.sax.SAXException;

public class GPXImportUtil {
	public static List<Waypoint> getTracks(InputStream in, String trackName) {
		List<Waypoint> result = new ArrayList<Waypoint>();
		GPXParser p = new GPXParser();
		try {
			GPX gpx = p.parseGPX(in);
			HashSet<Track> tracks = gpx.getTracks();
			if (tracks != null) {

				for (Iterator<Track> iterator = tracks.iterator(); iterator
						.hasNext();) {
					Track track = (Track) iterator.next();
					String name = track.getName();
					if (name != null
							&& trackName != null
							&& name.toLowerCase().equals(
									trackName.toLowerCase())) {
						List<Waypoint> trackPoints = track.getTrackPoints();
						if (trackPoints != null && trackPoints.size() > 0) {
							for (Iterator<Waypoint> iterator2 = trackPoints
									.iterator(); iterator2.hasNext();) {
								Waypoint waypoint = (Waypoint) iterator2.next();
								result.add(waypoint);
							}
						}
						break;
					}
				}
				if (result.size() == 0) {
					for (Iterator<Track> iterator = tracks.iterator(); iterator
							.hasNext();) {
						Track track = (Track) iterator.next();
						List<Waypoint> trackPoints = track.getTrackPoints();
						if (trackPoints != null && trackPoints.size() > 0) {
							for (Iterator<Waypoint> iterator2 = trackPoints
									.iterator(); iterator2.hasNext();) {
								Waypoint waypoint = (Waypoint) iterator2.next();
								result.add(waypoint);
							}
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result.size() > 0){
			result.add(result.get(0));
		}
		return result;
	}
}
