package org.jccastro.clip.assesment.transaction.datastore.filesystem;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Service Util to persist data on fileSystem
 * @author JCCastro
 *
 */
@Component("fileSystemPersistence")
public class FileSystemPersistence {

	private static final Logger log = LoggerFactory.getLogger(FileSystemPersistence.class);

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public Stream<String> getLines(String userId) throws URISyntaxException, IOException {
		log.debug("getFile for user " + userId);

		Path path = getPath(userId);

		if (!Files.exists(path))
			return null;

		return Files.lines(path);
	}

	/**
	 * 
	 * @param transaction
	 * @param userId
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public void addLine(String transaction, String userId) throws URISyntaxException, IOException {
		Path path = getPath(userId);
		StandardOpenOption option = StandardOpenOption.APPEND;

		if (!Files.exists(path))
			option = StandardOpenOption.CREATE_NEW;

		Files.write(path, transaction.concat(System.lineSeparator()).getBytes(), option);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws URISyntaxException
	 */
	private Path getPath(String userId) throws URISyntaxException {
		String cwd = System.getProperty("user.dir");
		String path = cwd.concat(File.separator).concat(userId).concat(".tx");

		return Paths.get(path);
	}

	public static void main(String[] args) {
		FileSystemPersistence fileUtil = new FileSystemPersistence();
		try {
			fileUtil.addLine("amount: 15.80, description: Felipe Tacos, date:2018-12-31", "fdseawed");
			fileUtil.getLines("fdseawed").forEach(System.out::println);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
