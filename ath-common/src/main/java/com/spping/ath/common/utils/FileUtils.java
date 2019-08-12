package com.spping.ath.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
@Service
public class FileUtils {
	private  static  final Logger  logger = LogManager.getLogger(FileUtils.class);
	/**
	 * 根据url拿取file
	 * 
	 * @param url
	 * @param suffix
	 *            文件后缀名
	 */
	public static File createFileByUrl(String url, String suffix) {
		byte[] byteFile = getImageFromNetByUrl(url);
		if (byteFile != null) {
			File file = getFileFromBytes(byteFile, suffix);
			return file;
		} else {
			return null;
		}
	}

	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	private static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	private static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	// 创建临时文件
	private static File getFileFromBytes(byte[] b, String suffix) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = File.createTempFile("pattern", "." + suffix);
			System.out.println("临时文件位置：" + file.getCanonicalPath());
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	public static MultipartFile createImg(String url) {
		try {
			// File转换成MutipartFile
			File file = FileUtils.createFileByUrl(url, "jpg");
			FileInputStream inputStream = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
			return multipartFile;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static MultipartFile fileToMultipart(String filePath) {
		try {
			// File转换成MutipartFile
			File file = new File(filePath);
			FileInputStream inputStream = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile(file.getName(), "png", "image/png", inputStream);
			return multipartFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// WebFileUtils.createFileByUrl("http://122.152.205.72:88/group1/M00/00/01/CpoxxFr7oIaAZ0rOAAC0d3GKDio580.png",
		// "png");
		// WebFileUtils.createImg("http://122.152.205.72:88/group1/M00/00/01/CpoxxFr7oIaAZ0rOAAC0d3GKDio580.png");
	}

	public static boolean base64ToFile(String filePath, String base64Data)  throws Exception {
		String dataPrix = "";
        String data = "";
        
        if(base64Data == null || "".equals(base64Data)){
            return false;
        }else{
            String [] d = base64Data.split("base64,");
            if(d != null && d.length == 1){
               // dataPrix = d[00];
                data = d[0];
            }else{
                return false;
            }
        }

        // 因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
        byte[] bs = Base64Utils.decodeFromString(data);
        // 使用apache提供的工具类操作流
        org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(filePath), bs);
        
        return true;
	}
	//文件上传使用
	public static String generatePath(String uploadFolder, String identifier,String fileName,int chunkNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append(uploadFolder).append("/").append(identifier);
		//判断uploadFolder/identifier 路径是否存在，不存在则创建
		if (!Files.isWritable(Paths.get(sb.toString()))) {
			logger.info("path not exist,create path: {}", sb.toString());
			try {
				Files.createDirectories(Paths.get(sb.toString()));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return sb.append("/")
				.append(fileName)
				.append("-")
				.append(chunkNumber).toString();
	}

	/**
	 * 文件合并
	 *
	 * @param targetFile
	 * @param folder
	 */
	public static void merge(String targetFile, String folder, String filename) {
		try {
			Files.createFile(Paths.get(targetFile));
			Files.list(Paths.get(folder))
					.filter(path -> !path.getFileName().toString().equals(filename))
					.sorted((o1, o2) -> {
						String p1 = o1.getFileName().toString();
						String p2 = o2.getFileName().toString();
						int i1 = p1.lastIndexOf("-");
						int i2 = p2.lastIndexOf("-");
						return Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring(i1)));
					})
					.forEach(path -> {
						try {
							//以追加的形式写入文件
							Files.write(Paths.get(targetFile), Files.readAllBytes(path), StandardOpenOption.APPEND);
							//合并后删除该块
							Files.delete(path);
						} catch (IOException e) {
							logger.error(e.getMessage(), e);
						}
					});
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
