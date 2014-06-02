package me.michaelkrauty.MCWrapper.Commands;

import me.michaelkrauty.MCWrapper.Main;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created on 5/27/2014.
 *
 * @author michaelkrauty
 */
public class Backup {

	private static Logger log = Logger.getLogger(Main.class);

	private static int entries = 0;

	public Backup(int serverid, String[] cmd) {
		try {
			log.info("Backing up server " + serverid + "...");
			File serverdir = new File("servers/" + serverid);
			File backupsdir = new File("backups/" + serverid);
			if (!backupsdir.exists()) {
				backupsdir.mkdir();
			}
			ZipOutputStream out = new ZipOutputStream(
					new FileOutputStream("backups/" + serverid + "/" + new SimpleDateFormat("dd-MM-yyyy--hh-mm-ss").format(new Date(System.currentTimeMillis())) + ".zip"));
			zipDir(serverdir, out);
			out.close();
			log.info("Finished backup of server " + serverid + " (zipped " + entries + " items)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void zipDir(File dirObj, ZipOutputStream out) throws IOException {
		File[] files = dirObj.listFiles();
		byte[] tmpBuf = new byte[1024];
		for (int i = 0; i < files.length; i++) {
			entries++;
			if (files[i].isDirectory()) {
				zipDir(files[i], out);
				continue;
			}
			FileInputStream in = new FileInputStream(files[i].getPath());
			out.putNextEntry(new ZipEntry(files[i].getPath()));
			int len;
			while ((len = in.read(tmpBuf)) > 0) {
				out.write(tmpBuf, 0, len);
			}
			out.closeEntry();
			in.close();
		}
	}
}
